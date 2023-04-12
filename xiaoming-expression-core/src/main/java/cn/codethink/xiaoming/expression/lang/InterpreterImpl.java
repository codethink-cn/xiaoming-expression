/*
 * Copyright 2023 CodeThink Technologies and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.codethink.xiaoming.expression.lang;

import cn.codethink.xiaoming.expression.*;
import cn.codethink.xiaoming.expression.acl.Scanner;
import cn.codethink.xiaoming.expression.acl.parser;
import cn.codethink.xiaoming.expression.annotation.Constructor;
import cn.codethink.xiaoming.expression.format.*;
import cn.codethink.xiaoming.expression.format.Formatter;
import com.google.common.base.Preconditions;
import org.apache.commons.text.StringEscapeUtils;

import java.io.File;
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
                final Class<?> parameterClass = parametersClasses.get(i);
                if (parameterClass != null && !functionParametersClasses.get(i).isAssignableFrom(parameterClass)) {
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
                    final Class<?> parameterClass = parametersClasses.get(i);
                    if (parameterClass != null && !functionParametersClasses.get(i).isAssignableFrom(parameterClass)) {
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
        
        throw new IllegalArgumentException("Can not analyze object");
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
        
        return format(expression, FormatConfiguration.getInstance());
    }
    
    @Override
    public String format(Expression expression, FormatConfiguration configuration) {
        Preconditions.checkNotNull(expression, "Expression is null!");
        Preconditions.checkNotNull(configuration, "Formatting configuration is null!");
    
        final Formatter formatter = new FormatterImpl(configuration);
        plusFormatUnits(formatter, expression);
        return formatter.toString();
    }
    
    private void plusFormatUnits(Formatter formatter, Expression expression) {
        
        // 格式化常量类型
        if (expression instanceof LiteralExpression) {
            final Object constant = ((LiteralExpression) expression).getValue();
            if (constant == null) {
                formatter.plus("null");
                return;
            }
        
            if (constant instanceof Integer) {
                formatter.plus(constant.toString());
                return;
            }
            if (constant instanceof Double) {
                formatter.plus(constant.toString());
                return;
            }
            if (constant instanceof Boolean) {
                formatter.plus(constant.toString());
                return;
            }
            if (constant instanceof Character) {
                formatter.plus("'" + StringEscapeUtils.escapeJava(String.valueOf(constant)) + "'");
                return;
            }
            if (constant instanceof String) {
                formatter.plus("\"" + StringEscapeUtils.escapeJava((String) constant) + "\"");
                return;
            }
        
            throw new IllegalArgumentException("Unexpected value in LiteralExpression: " + constant);
        }
        final FormatConfiguration configuration = formatter.getConfiguration();
        final FormatUnit comma = configuration.getComma();
    
        // 函数调用表达式
        if (expression instanceof InvokeExpression) {
            final PairedFormatUnit parenthesis = configuration.getParenthesis();
        
            final InvokeExpression invokeExpression = (InvokeExpression) expression;
            final List<Expression> arguments = invokeExpression.getArguments();
        
            formatter.plus(invokeExpression.getConstructor().getName());
            formatter.plus(parenthesis.getLeftUnit());
        
            if (arguments.isEmpty()) {
                formatter.plus(parenthesis.getEmptyUnit());
            } else {
                final int size = arguments.size();
                plusFormatUnits(formatter, arguments.get(0));
                for (int i = 1; i < size; i++) {
                    formatter.plus(comma);
                    plusFormatUnits(formatter, arguments.get(i));
                }
            }
    
            formatter.plus(parenthesis.getRightUnit());
            return;
        }
        
        // 列表表达式
        if (expression instanceof ListExpression) {
            final PairedFormatUnit brackets = configuration.getBrackets();
            final List<Expression> arguments = ((ListExpression) expression).getExpressions();
            plusFormatUnits(formatter, brackets, comma, arguments);
            return;
        }
        
        // 集合表达式
        if (expression instanceof SetExpression) {
            final PairedFormatUnit braces = configuration.getBraces();
            final List<Expression> arguments = ((SetExpression) expression).getExpressions();
            plusFormatUnits(formatter, braces, comma, arguments);
        }
    
        throw new IllegalArgumentException("Unexpected expression class: " + expression.getClass().getName());
    }
    
    private void plusFormatUnits(Formatter formatter, PairedFormatUnit pairedFormatUnit, FormatUnit separator, List<Expression> expressions) {
        formatter.plus(pairedFormatUnit.getLeftUnit());
    
        if (expressions.isEmpty()) {
            formatter.plus(pairedFormatUnit.getEmptyUnit());
        } else {
            final int size = expressions.size();
            plusFormatUnits(formatter, expressions.get(0));
            for (int i = 1; i < size; i++) {
                formatter.plus(separator);
                plusFormatUnits(formatter, expressions.get(i));
            }
        }
    
        formatter.plus(pairedFormatUnit.getRightUnit());
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