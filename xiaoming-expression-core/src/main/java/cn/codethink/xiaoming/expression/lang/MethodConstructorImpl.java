package cn.codethink.xiaoming.expression.lang;

import com.google.common.base.Preconditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public class MethodConstructorImpl
    implements Constructor {
    
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
            } else if (!javaClass.isAssignableFrom(subject.getClass())) {
                throw new IllegalArgumentException("Subject is not an instance of " + javaClass.getName());
            }
        }
    
        final java.lang.reflect.Parameter[] parameters = method.getParameters();
        
    }
    
    @Override
    public Type getType() {
        return null;
    }
    
    @Override
    public Object construct(List<Object> arguments) throws InvocationTargetException {
        return null;
    }
    
    @Override
    public List<Parameter> getParameters() {
        return null;
    }
}
