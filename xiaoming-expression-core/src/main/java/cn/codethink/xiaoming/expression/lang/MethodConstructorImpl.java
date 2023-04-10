package cn.codethink.xiaoming.expression.lang;

import cn.codethink.xiaoming.expression.interpreter.Interpreter;
import com.google.common.base.Preconditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MethodConstructorImpl
    implements Constructor {
    
    private final List<Parameter> parameters;
    private final Method method;
    private final Type type;
    private final Object subject;
    
    public MethodConstructorImpl(Type type, Object subject, Method method) {
        Preconditions.checkNotNull(type, "Type is null!");
        Preconditions.checkNotNull(method, "Method is null!");
    
        final boolean isStatic = Modifier.isStatic(method.getModifiers());
        final Class<?> javaClass = type.getJavaClass();
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
        
        final java.lang.reflect.Parameter[] parameters = method.getParameters();
        final List<Parameter> parameterList = new ArrayList<>(parameters.length);
        
        this.parameters = Collections.unmodifiableList(parameterList);
        this.method = method;
        this.type = type;
        this.subject = subject;
        
        for (java.lang.reflect.Parameter parameter : parameters) {
            final JavaTypeImpl javaType = new JavaTypeImpl(parameter.getType().getSimpleName(), parameter.getType(), Collections.emptySet());
            parameterList.add(new ParameterImpl(parameter.getName(), javaType));
        }
    }
    
    @Override
    public Type getType() {
        return type;
    }
    
    @Override
    public Object construct(List<Object> arguments) throws InvocationTargetException {
        Preconditions.checkNotNull(arguments, "Arguments are null!");
        Preconditions.checkArgument(arguments.size() == parameters.size(),
            "Count of arguments not equals to count of parameters! Required " + parameters.size() + ", but got " + arguments.size());
    
        final Object[] objects = arguments.toArray(new Object[0]);
        for (int i = 0; i < objects.length; i++) {
            final Object object = objects[i];
            if (object != null) {
                final Class<?> parameterClass = parameters.get(i).getType().getJavaClass();
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
            throw new InvocationTargetException(e, "Can not access java method: " + method);
        } finally {
            method.setAccessible(accessible);
        }
    }
    
    @Override
    public List<Parameter> getParameters() {
        return parameters;
    }
}
