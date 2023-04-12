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

package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.Expression;
import com.google.common.base.Preconditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Collectors;

public class MethodAnalyzerImpl
    implements Analyzer {
    
    private static final Object[] EMPTY_ARRAY = {};
    
    private final Method method;
    private final Object subject;
    private final List<Class<?>> parametersClasses;
    private final Class<?> subjectClass;
    
    public MethodAnalyzerImpl(Object subject, Class<?> subjectClass, Method method) {
        Preconditions.checkNotNull(method, "Method is null!");
        
        final boolean isStatic = Modifier.isStatic(method.getModifiers());
        final Class<?> javaClass = method.getDeclaringClass();
        if (isStatic) {
            if (subject == null || javaClass.isAssignableFrom(subject.getClass())) {
                subject = method.getDeclaringClass();
            } else if (subject != method.getDeclaringClass()) {
                throw new IllegalArgumentException("Subject is not an instance of Class<?> or " + javaClass.getName());
            }
        } else {
            if (subject == null) {
                throw new NullPointerException("Subject is null, but method is not static");
            } else if (!method.getDeclaringClass().isAssignableFrom(subject.getClass())) {
                throw new IllegalArgumentException("Subject is not an instance of " + javaClass.getName());
            }
        }
    
        if (!Expression.class.isAssignableFrom(method.getReturnType())) {
            throw new IllegalArgumentException("Unexpected return type: " + method.getReturnType().getName() + ", expect " + Expression.class.getName());
        }
        
        final java.lang.reflect.Parameter[] parameters = method.getParameters();
        this.parametersClasses = Arrays.stream(parameters)
            .map(Parameter::getType)
            .collect(Collectors.toList());
        
        if (parameters.length == 1) {
            this.subjectClass = parameters[0].getType();
        } else {
            Preconditions.checkNotNull(subjectClass, "Subject class is null!");
            this.subjectClass = subjectClass;
        }
        
        this.method = method;
        this.subject = subject;
    }
    
    @Override
    public Expression analyze(Object subject, Interpreter interpreter) {
        Preconditions.checkNotNull(subject, "Subject are null!");
        Preconditions.checkNotNull(interpreter, "Interpreter are null!");
        
        final Object[] objects;
        final Class<?> subjectClass = subject.getClass();
        if (parametersClasses.isEmpty()) {
            objects = EMPTY_ARRAY;
        } else {
            objects = new Object[parametersClasses.size()];
            for (int i = 0; i < parametersClasses.size(); i++) {
                final Class<?> parameterClass = parametersClasses.get(i);
                
                if (parameterClass.isAssignableFrom(subjectClass)) {
                    objects[i] = subject;
                    continue;
                }
                if (parameterClass.isAssignableFrom(interpreter.getClass())) {
                    objects[i] = interpreter;
                    continue;
                }
    
                return null;
            }
        }
        
        final boolean accessible = method.isAccessible();
        try {
            method.setAccessible(true);
            return (Expression) method.invoke(this.subject, objects);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Can not access java method: " + method);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException("Exception thrown while analyzing " + subject.getClass().getName(), e.getCause());
        } finally {
            method.setAccessible(accessible);
        }
    }
    
    @Override
    public Class<?> getSubjectClass() {
        return subjectClass;
    }
}
