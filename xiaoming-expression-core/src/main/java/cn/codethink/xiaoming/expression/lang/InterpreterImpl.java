package cn.codethink.xiaoming.expression.lang;

import cn.codethink.xiaoming.expression.*;
import cn.codethink.xiaoming.expression.acl.Scanner;
import cn.codethink.xiaoming.expression.acl.parser;
import cn.codethink.xiaoming.expression.annotation.Constructor;
import cn.codethink.xiaoming.expression.formatter.FormattingConfiguration;
import cn.codethink.xiaoming.expression.formatter.FormattingItem;
import cn.codethink.xiaoming.expression.formatter.FormattingSpaceItem;
import cn.codethink.xiaoming.expression.formatter.FormattingTextItem;
import com.google.common.base.Preconditions;
import org.apache.commons.text.StringEscapeUtils;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class InterpreterImpl
    implements Interpreter {
    
    private final InterpreterImpl parent;
    private final List<Analyzer> analyzers = new CopyOnWriteArrayList<>();
    private final List<Function> functions = new CopyOnWriteArrayList<>();
    
    public InterpreterImpl() {
        this.parent = null;
    }
    
    public InterpreterImpl(Interpreter parent) {
        Preconditions.checkNotNull(parent, "Parent is null!");
        Preconditions.checkArgument(parent instanceof InterpreterImpl, "Unexpected parent, expect an instance of " + InterpreterImpl.class.getName());
        
        this.parent = (InterpreterImpl) parent;
    }
    
    @Override
    public Interpreter getParent() {
        return parent;
    }
    
    @Override
    public List<Function> getFunctions(String name, List<Class<?>> parametersClasses) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkNotNull(parametersClasses, "Parameter classes is null!");
        
        final List<Function> functions = new ArrayList<>();
        InterpreterImpl interpreter = this;
        while (interpreter != null) {
            functions.addAll(getFunctions0(name, parametersClasses));
            interpreter = interpreter.parent;
        }
        
        return Collections.unmodifiableList(functions);
    }
    
    private List<Function> getFunctions0(String name, List<Class<?>> parametersClasses) {
        final int size = parametersClasses.size();
        final List<Function> functions = new ArrayList<>();
        
        for (Function function : this.functions) {
            if (!function.getName().equals(name)) {
                continue;
            }
            
            final List<Class<?>> functionParametersClasses = function.getParametersClasses();
            if (size != functionParametersClasses.size()) {
                continue;
            }
    
            boolean matches = true;
            for (int i = 0; i < size; i++) {
                if (!functionParametersClasses.get(i).isAssignableFrom(parametersClasses.get(i))) {
                    matches = false;
                    break;
                }
            }
            if (matches) {
                functions.add(function);
            }
        }
        
        return functions;
    }
    
    @Override
    public Function getFunction(String name, List<Class<?>> parametersClasses) {
        final int size = parametersClasses.size();
        
        InterpreterImpl interpreter = this;
        while (interpreter != null) {
    
            for (Function function : interpreter.functions) {
                if (!function.getName().equals(name)) {
                    continue;
                }
    
                final List<Class<?>> functionParametersClasses = function.getParametersClasses();
                if (size != functionParametersClasses.size()) {
                    continue;
                }
    
                boolean matches = true;
                for (int i = 0; i < size; i++) {
                    if (!functionParametersClasses.get(i).isAssignableFrom(parametersClasses.get(i))) {
                        matches = false;
                        break;
                    }
                }
                if (matches) {
                    return function;
                }
            }
            
            interpreter = interpreter.parent;
        }
        
        return null;
    }
    
    @Override
    public Function getFunctionOrFail(String name, List<Class<?>> paraClasses) {
        final Function function = getFunction(name, paraClasses);
        if (function == null) {
            throw new NoSuchElementException("No such method accepting parameter classes: " + paraClasses);
        }
        return function;
    }
    
    @Override
    public Expression compile(String expression) {
        Preconditions.checkNotNull(expression, "Expression is null!");
        Preconditions.checkArgument(!expression.isEmpty(), "Expression is empty!");
        
        return compile(new StringReader(expression));
    }
    
    @Override
    @SuppressWarnings("all")
    public Expression compile(Reader reader) {
        Preconditions.checkNotNull(reader, "Reader is null!");
        
        try {
            final Scanner scanner = new Scanner(reader);
            final parser parser = new parser(scanner);
            parser.initialize(this);
    
            return (Expression) parser.parse().value;
        } catch (Exception e) {
            final String message = e.getMessage();
            if (message == null || message.isEmpty()) {
                throw new IllegalStateException("Exception thrown while compiling", e);
            } else {
                throw new IllegalStateException(e.getMessage());
            }
        }
    }
    
    @Override
    public Expression analyze(Object subject) {
        return analyze(subject, Collections.emptySet());
    }
    
    @Override
    public Expression analyze(Object subject, Set<Object> properties) {
        if (subject == null) {
            return LiteralExpression.ofNull();
        }
        
        Preconditions.checkNotNull(properties, "Properties are null!");
        final AnalyzingContext context = new AnalyzingContextImpl(subject, properties, this);
        
        InterpreterImpl interpreter = this;
        while (interpreter != null) {
            final Expression expression = interpreter.analyze0(context);
            if (expression != null) {
                return expression;
            }
            interpreter = interpreter.parent;
        }
        
        return null;
    }
    
    private Expression analyze0(AnalyzingContext context) {
        final Class<?> subjectClass = context.getSubject().getClass();
        for (Analyzer analyzer : analyzers) {
            if (analyzer.getSubjectClass().isAssignableFrom(subjectClass)) {
                final Expression expression = analyzer.analyze(context);
                if (expression != null) {
                    return expression;
                }
            }
        }
        return null;
    }
    
    @Override
    public String format(Expression expression) {
        Preconditions.checkNotNull(expression, "Expression is null!");
        
        return format(expression, FormattingConfiguration.getInstance());
    }
    
    @Override
    public String format(Expression expression, FormattingConfiguration configuration) {
        Preconditions.checkNotNull(expression, "Expression is null!");
        Preconditions.checkNotNull(configuration, "Formatting configuration is null!");
        
        // 格式化常量类型
        if (expression instanceof LiteralExpression) {
            final Object constant = ((LiteralExpression) expression).getValue();
            if (constant == null) {
                return "null";
            }
            
            if (constant instanceof Integer) {
                return constant.toString();
            }
            if (constant instanceof Double) {
                return constant.toString();
            }
            if (constant instanceof Boolean) {
                return constant.toString();
            }
            if (constant instanceof Character) {
                return "'" + StringEscapeUtils.escapeJava(String.valueOf(constant)) + "'";
            }
            if (constant instanceof String) {
                return "\"" + StringEscapeUtils.escapeJava((String) constant) + "\"";
            }
            
            throw new IllegalArgumentException("Unexpected value in LiteralExpression: " + constant);
        }
        
        // 格式化元素列表
        final List<FormattingItem> items = new ArrayList<>();
        final FormattingItem comma = new FormattingTextItem(configuration.getCountOfSpacesBeforeComma(), ",", configuration.getCountOfSpacesAfterComma());
        
        if (expression instanceof InvokeExpression) {
            final FormattingItem leftParenthesis = new FormattingTextItem(configuration.getCountOfSpacesBeforeLeftParenthesis(),
                "(", configuration.getCountOfSpacesAfterLeftParenthesis());
            final FormattingItem rightParenthesis = new FormattingTextItem(configuration.getCountOfSpacesBeforeRightParenthesis(),
                ")", configuration.getCountOfSpacesAfterRightParenthesis());
            
            final InvokeExpression invokeExpression = (InvokeExpression) expression;
            final List<Expression> arguments = invokeExpression.getArguments();
            
            items.add(new FormattingTextItem(invokeExpression.getResultClass().getSimpleName()));
            items.add(leftParenthesis);
            
            if (arguments.isEmpty()) {
                items.add(FormattingSpaceItem.of(configuration.getCountOfSpacesInEmptyBraces()));
            } else {
                final int size = arguments.size();
                items.add(FormattingItem.parse(format(arguments.get(0), configuration)));
                for (int i = 1; i < size; i++) {
                    items.add(comma);
                    items.add(FormattingItem.parse(format(arguments.get(i), configuration)));
                }
            }
            
            items.add(rightParenthesis);
            return FormattingItem.toString(items, configuration.isMinimizeSpaces());
        }
        if (expression instanceof ListExpression) {
            final FormattingItem leftBracket = new FormattingTextItem(configuration.getCountOfSpacesBeforeLeftBrackets(),
                "[", configuration.getCountOfSpacesAfterLeftBrackets());
            final FormattingItem rightBracket = new FormattingTextItem(configuration.getCountOfSpacesBeforeRightBrackets(),
                "]", configuration.getCountOfSpacesAfterRightBrackets());
            
            final List<Expression> arguments = ((ListExpression) expression).getExpressions();
            
            items.add(leftBracket);
            
            if (arguments.isEmpty()) {
                items.add(FormattingSpaceItem.of(configuration.getCountOfSpacesInEmptyBrackets()));
            } else {
                final int size = arguments.size();
                items.add(FormattingItem.parse(format(arguments.get(0), configuration)));
                for (int i = 1; i < size; i++) {
                    items.add(comma);
                    items.add(FormattingItem.parse(format(arguments.get(i), configuration)));
                }
            }
            
            items.add(rightBracket);
            return FormattingItem.toString(items, configuration.isMinimizeSpaces());
        }
        if (expression instanceof SetExpression) {
            final FormattingItem leftBrace = new FormattingTextItem(configuration.getCountOfSpacesBeforeLeftBraces(),
                "{", configuration.getCountOfSpacesAfterLeftBraces());
            final FormattingItem rightBrace = new FormattingTextItem(configuration.getCountOfSpacesBeforeRightBraces(),
                "}", configuration.getCountOfSpacesAfterRightBraces());
            
            final List<Expression> arguments = ((SetExpression) expression).getExpressions();
            
            items.add(leftBrace);
            
            if (arguments.isEmpty()) {
                items.add(FormattingSpaceItem.of(configuration.getCountOfSpacesInEmptyBraces()));
            } else {
                final int size = arguments.size();
                items.add(FormattingItem.parse(format(arguments.get(0), configuration)));
                for (int i = 1; i < size; i++) {
                    items.add(comma);
                    items.add(FormattingItem.parse(format(arguments.get(i), configuration)));
                }
            }
            
            items.add(rightBrace);
            return FormattingItem.toString(items, configuration.isMinimizeSpaces());
        }
        
        throw new IllegalArgumentException("Unexpected expression class: " + expression.getClass().getName());
    }
    
    @Override
    public void registerMethods(Object... subjects) {
        Preconditions.checkNotNull(subjects, "Subjects are null!");
        for (Object subject : subjects) {
            registerMethods(subject);
        }
    }
    
    @Override
    public void registerMethods(Object subject) {
        Preconditions.checkNotNull(subject, "Subject is null!");
    
        final Class<?> subjectClass = subject.getClass();
        for (Method declaredMethod : subjectClass.getDeclaredMethods()) {
            final Constructor constructor = declaredMethod.getAnnotation(Constructor.class);
            if (constructor != null) {
                String name = constructor.value();
                if (name.isEmpty()) {
                    name = declaredMethod.getReturnType().getSimpleName();
                }
                
                functions.add(new MethodFunctionImpl(name, subject, declaredMethod));
            }
    
            final cn.codethink.xiaoming.expression.annotation.Function functionAnnotation =
                declaredMethod.getAnnotation(cn.codethink.xiaoming.expression.annotation.Function.class);
            if (functionAnnotation != null) {
                String name = functionAnnotation.value();
                if (name.isEmpty()) {
                    name = declaredMethod.getName();
                }
    
                functions.add(new MethodFunctionImpl(name, subject, declaredMethod));
            }
    
            final cn.codethink.xiaoming.expression.annotation.Analyzer analyzerAnnotation =
                declaredMethod.getAnnotation(cn.codethink.xiaoming.expression.annotation.Analyzer.class);
            if (analyzerAnnotation != null) {
                Class<?> targetClass = analyzerAnnotation.value();
                if (targetClass == Void.class) {
                    targetClass = null;
                }
    
                analyzers.add(new MethodAnalyzerImpl(subject, targetClass, declaredMethod));
            }
        }
    }
}