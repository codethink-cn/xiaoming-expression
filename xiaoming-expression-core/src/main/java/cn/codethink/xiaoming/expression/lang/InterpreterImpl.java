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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class InterpreterImpl
    implements Interpreter {
    
    private final List<Analyzer> analyzers = new CopyOnWriteArrayList<>();
    private final Map<String, Map<Integer, List<Function>>> functions = new ConcurrentHashMap<>();
    
    @Override
    public List<Function> getFunctions(String name, List<Class<?>> parametersClasses) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkNotNull(parametersClasses, "Parameter classes is null!");
        
        final int size = parametersClasses.size();
        final Map<Integer, List<Function>> sameNameFunctions = this.functions.get(name);
        if (sameNameFunctions == null) {
            return Collections.emptyList();
        }
    
        final List<Function> sameParameterFunctions = sameNameFunctions.get(parametersClasses.size());
        if (sameParameterFunctions == null) {
            return Collections.emptyList();
        }
    
        final List<Function> functions = new ArrayList<>();
        for (Function function : sameParameterFunctions) {
            final List<Class<?>> functionParametersClasses = function.getParametersClasses();
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
        return Collections.unmodifiableList(functions);
    }
    
    @Override
    public Function getFunction(String name, List<Class<?>> parametersClasses) {
        final int size = parametersClasses.size();
        final Map<Integer, List<Function>> sameNameFunctions = this.functions.get(name);
        if (sameNameFunctions == null) {
            return null;
        }
    
        final List<Function> sameParameterFunctions = sameNameFunctions.get(parametersClasses.size());
        if (sameParameterFunctions == null) {
            return null;
        }
        
        for (Function function : sameParameterFunctions) {
            final List<Class<?>> functionParametersClasses = function.getParametersClasses();
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
        if (subject == null) {
            return LiteralExpression.ofNull();
        }
        
        final Class<?> subjectClass = subject.getClass();
        for (Analyzer analyzer : analyzers) {
            if (analyzer.getSubjectClass().isAssignableFrom(subjectClass)) {
                final Expression expression = analyzer.analyze(subject, this);
                if (expression != null) {
                    return expression;
                }
            }
        }
        
        throw new IllegalArgumentException("Can not analyze object");
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
    
    public void plusFormatUnits(Formatter formatter, Expression expression) {
        
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
            plusFormatUnits(formatter, parenthesis, comma, arguments);
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
    
                final MethodFunctionImpl function = new MethodFunctionImpl(name, subject, declaredMethod);
                functions.computeIfAbsent(name, n -> new ConcurrentHashMap<>())
                    .computeIfAbsent(function.getParametersClasses().size(), n -> new CopyOnWriteArrayList<>()).add(function);
            }
    
            final cn.codethink.xiaoming.expression.annotation.Function functionAnnotation =
                declaredMethod.getAnnotation(cn.codethink.xiaoming.expression.annotation.Function.class);
            if (functionAnnotation != null) {
                String name = functionAnnotation.value();
                if (name.isEmpty()) {
                    name = declaredMethod.getName();
                }
    
                final MethodFunctionImpl function = new MethodFunctionImpl(name, subject, declaredMethod);
                functions.computeIfAbsent(name, n -> new ConcurrentHashMap<>())
                    .computeIfAbsent(function.getParametersClasses().size(), n -> new CopyOnWriteArrayList<>()).add(function);
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