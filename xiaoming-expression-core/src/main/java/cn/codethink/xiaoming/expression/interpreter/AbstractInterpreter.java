package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.*;
import cn.codethink.xiaoming.expression.acl.Scanner;
import cn.codethink.xiaoming.expression.acl.parser;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingContext;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingException;
import cn.codethink.xiaoming.expression.anlyzer.AnalyzingContextImpl;
import cn.codethink.xiaoming.expression.compiler.CompilingConfiguration;
import cn.codethink.xiaoming.expression.compiler.CompilingException;
import cn.codethink.xiaoming.expression.formatter.FormattingConfiguration;
import cn.codethink.xiaoming.expression.formatter.FormattingException;
import cn.codethink.xiaoming.expression.formatter.FormattingItem;
import cn.codethink.xiaoming.expression.formatter.FormattingTextItem;
import cn.codethink.xiaoming.expression.type.Type;
import com.google.common.base.Preconditions;
import org.apache.commons.text.StringEscapeUtils;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class AbstractInterpreter
    implements Interpreter {
    
    protected final Interpreter parent;
    
    protected AbstractInterpreter(Interpreter parent) {
        this.parent = parent;
    }
    
    @Override
    public Interpreter getParent() {
        return parent;
    }
    
    @Override
    public final Type getType(String name) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
    
        if (parent == null) {
            return getType0(name);
        } else {
            Type type = parent.getType(name);
            if (type == null) {
                type = getType0(name);
            }
            return type;
        }
    }
    
    protected abstract Type getType0(String name);
    
    @Override
    public Type getTypeOrFail(String name) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
    
        final Type type = getType(name);
        if (type == null) {
            throw new NoSuchElementException("No such type: " + name);
        }
        return type;
    }
    
    @Override
    public Expression compile(String expression) throws CompilingException {
        Preconditions.checkNotNull(expression, "Expression is null!");
        Preconditions.checkArgument(!expression.isEmpty(), "Expression is empty!");
        
        return compile(new StringReader(expression), CompilingConfiguration.getInstance());
    }
    
    @Override
    public Expression compile(String expression, CompilingConfiguration configuration) throws CompilingException {
        Preconditions.checkNotNull(expression, "Expression is null!");
        Preconditions.checkNotNull(configuration, "Configuration is null!");
        Preconditions.checkArgument(!expression.isEmpty(), "Expression is empty!");
        
        return compile(new StringReader(expression), CompilingConfiguration.getInstance());
    }
    
    @Override
    public Expression compile(Reader reader) throws CompilingException {
        Preconditions.checkNotNull(reader, "Reader is null!");
    
        return compile(reader, CompilingConfiguration.getInstance());
    }
    
    @Override
    @SuppressWarnings("all")
    public Expression compile(Reader reader, CompilingConfiguration configuration) throws CompilingException {
        Preconditions.checkNotNull(reader, "Reader is null!");
        Preconditions.checkNotNull(configuration, "Configuration is null!");
    
        try {
            final Scanner scanner = new Scanner(reader);
            final parser parser = new parser(scanner);
            parser.initialize(this, configuration);
        
            return (Expression) parser.parse().value;
        } catch (Exception e) {
            throw new CompilingException(e);
        }
    }
    
    @Override
    public Expression analyze(Object subject) throws AnalyzingException {
        return analyze(subject, AnalyzingConfiguration.getInstance());
    }
    
    @Override
    public Expression analyze(Object subject, AnalyzingConfiguration configuration) throws AnalyzingException {
        if (subject == null) {
            return ConstantExpressionImpl.ofNull();
        }
        final AnalyzingContext context = new AnalyzingContextImpl(subject, configuration, this);
        if (parent == null) {
            return analyze0(context);
        } else {
            Expression expression = parent.analyze(context);
            if (expression == null) {
                expression = analyze0(context);
            }
            return expression;
        }
    }
    
    protected abstract Expression analyze0(AnalyzingContext context) throws AnalyzingException;
    
    @Override
    public String format(Expression expression) throws FormattingException {
        Preconditions.checkNotNull(expression, "Expression is null!");
        
        return format(expression, FormattingConfiguration.getInstance());
    }
    
    @Override
    public String format(Expression expression, FormattingConfiguration configuration) throws FormattingException {
        Preconditions.checkNotNull(expression, "Expression is null!");
        Preconditions.checkNotNull(configuration, "Formatting configuration is null!");
    
        // 格式化常量类型
        if (expression instanceof ConstantExpression) {
            final Object constant = ((ConstantExpression) expression).getConstant();
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
            
            throw new IllegalArgumentException("Unexpected value in ConstantExpression: " + constant);
        }
        
        // 格式化元素列表
        final List<FormattingItem> items = new ArrayList<>();
        final FormattingItem comma = new FormattingTextItem(configuration.getCountOfSpacesBeforeComma(), ",", configuration.getCountOfSpacesAfterComma());
        
        if (expression instanceof ConstructExpression) {
            final FormattingItem leftParenthesis = new FormattingTextItem(configuration.getCountOfSpacesBeforeLeftParenthesis(),
                "(", configuration.getCountOfSpacesAfterLeftParenthesis());
            final FormattingItem rightParenthesis = new FormattingTextItem(configuration.getCountOfSpacesBeforeRightParenthesis(),
                ")", configuration.getCountOfSpacesAfterRightParenthesis());
            
            final ConstructExpression constructExpression = (ConstructExpression) expression;
            final List<Expression> arguments = constructExpression.getArguments();
            
            items.add(new FormattingTextItem(constructExpression.getType().getName()));
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
        return null;
    }
}