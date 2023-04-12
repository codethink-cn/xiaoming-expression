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

import com.google.common.base.Preconditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MethodFunctionImpl
    implements Function {
    
    private final List<Class<?>> parametersClasses;
    private final Method method;
    private final Object subject;
    private final String name;
    
    public MethodFunctionImpl(String name, Object subject, Method method) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
        Preconditions.checkNotNull(method, "Method is null!");
        
        this.name = name;
        this.method = method;
        this.subject = subject;
        this.parametersClasses = Collections.unmodifiableList(Arrays.stream(method.getParameters())
            .map(Parameter::getType)
            .collect(Collectors.toList()));
        
        final boolean isStatic = Modifier.isStatic(method.getModifiers());
        final Class<?> javaClass = method.getReturnType();
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
    }
    
    @Override
    public Object invoke(List<Object> arguments) {
        Preconditions.checkNotNull(arguments, "Arguments are null!");
        Preconditions.checkArgument(arguments.size() == parametersClasses.size(),
            "Count of arguments not equals to count of parameters! Required " + parametersClasses.size() + ", but got " + arguments.size());
    
        final Object[] objects = arguments.toArray(new Object[0]);
        for (int i = 0; i < objects.length; i++) {
            final Object object = objects[i];
            if (object != null) {
                final Class<?> parameterClass = parametersClasses.get(i);
                final Class<?> objectClass = object.getClass();
                if (!parameterClass.isAssignableFrom(objectClass)) {
                    throw new IllegalArgumentException("Unexpected argument type: " + parameterClass + " (at argument " + (i + 1) + ")");
                }
            }
        }
    
        final boolean accessible = method.isAccessible();
        try {
            method.setAccessible(true);
            return method.invoke(subject, objects);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Can not access java method: " + method, e);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException("Exception thrown while constructing", e.getCause());
        } finally {
            method.setAccessible(accessible);
        }
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Class<?> getReturnClass() {
        return method.getReturnType();
    }
    
    public List<Class<?>> getParametersClasses() {
        return parametersClasses;
    }
}
