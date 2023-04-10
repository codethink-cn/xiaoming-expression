package cn.codethink.xiaoming.expression.formatter;

import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.annotation.Subject;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;
import cn.codethink.xiaoming.expression.type.Type;
import com.google.common.base.Preconditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class MethodFormatterImpl
    implements Formatter {
    
    private final Selector[] selectors;
    private final Method method;
    private final Object subject;
    private final Type type;
    
    private interface Selector {
        Object select(Object subject, FormattingContext context);
    }
    
    private static class FormattingContextSelector
        implements Selector {
        
        private static final FormattingContextSelector INSTANCE = new FormattingContextSelector();
        
        @Override
        public Object select(Object subject, FormattingContext context) {
            return context;
        }
    }
    
    private static class FormattingConfigurationSelector
        implements Selector {
        
        private static final FormattingConfigurationSelector INSTANCE = new FormattingConfigurationSelector();
        
        @Override
        public Object select(Object subject, FormattingContext context) {
            return context.getConfiguration();
        }
    }
    
    private static class InterpreterSelector
        implements Selector {
        
        private static final InterpreterSelector INSTANCE = new InterpreterSelector();
        
        @Override
        public Object select(Object subject, FormattingContext context) {
            return context.getInterpreter();
        }
    }
    
    private static class ExpressionSelector
        implements Selector {
        
        private static final ExpressionSelector INSTANCE = new ExpressionSelector();
        
        @Override
        public Object select(Object subject, FormattingContext context) {
            return context.getExpression();
        }
    }
    
    private static class SubjectSelector
        implements Selector {
        
        private static final SubjectSelector INSTANCE = new SubjectSelector();
        
        @Override
        public Object select(Object subject, FormattingContext context) {
            return subject;
        }
    }
    
    private class TypeSelector
        implements Selector {
        
        @Override
        public Object select(Object subject, FormattingContext context) {
            return type;
        }
    }
    
    public MethodFormatterImpl(Type type, Object subject, Method method) {
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
        
        final Parameter[] parameters = method.getParameters();
        this.selectors = new Selector[parameters.length];
        
        for (int i = 0; i < parameters.length; i++) {
            final Parameter parameter = parameters[i];
            
            final Class<?> parameterType = parameter.getType();
            if (parameterType.isAnnotationPresent(Subject.class)
                || parameterType.isAssignableFrom(type.getJavaClass())) {
                selectors[i] = SubjectSelector.INSTANCE;
                continue;
            }
            
            if (!Interpreter.class.isAssignableFrom(type.getJavaClass()) && parameterType.isAssignableFrom(Interpreter.class)) {
                selectors[i] = InterpreterSelector.INSTANCE;
                continue;
            }
            
            if (!FormattingConfiguration.class.isAssignableFrom(type.getJavaClass()) && parameterType.isAssignableFrom(FormattingConfiguration.class)) {
                selectors[i] = FormattingConfigurationSelector.INSTANCE;
                continue;
            }
    
            if (!Type.class.isAssignableFrom(type.getJavaClass()) && parameterType.isAssignableFrom(Type.class)) {
                selectors[i] = new TypeSelector();
                continue;
            }
            
            if (!FormattingContext.class.isAssignableFrom(type.getJavaClass()) && parameterType.isAssignableFrom(FormattingContext.class)) {
                selectors[i] = FormattingContextSelector.INSTANCE;
                continue;
            }
            
            throw new IllegalArgumentException("Unexpected parameter type: " + parameterType.getName() + ", " +
                "expected: [ Interpreter, FormattingContext, FormattingConfiguration, " + type.getJavaClass().getSimpleName() + " ]");
        }
        
        this.method = method;
        this.subject = subject;
        this.type = type;
    }
    
    @Override
    public String format(Object subject, FormattingContext context) throws FormattingException {
        Preconditions.checkNotNull(subject, "Subject are null!");
        Preconditions.checkNotNull(context, "Formatting context are null!");
        
        final Object[] objects = new Object[selectors.length];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = selectors[i].select(subject, context);
        }
        
        final boolean accessible = method.isAccessible();
        try {
            method.setAccessible(true);
            final Object result = method.invoke(this.subject, objects);
            if (result == null) {
                throw new FormattingException("Formatter returns null!");
            }
            if (result instanceof String) {
                return (String) result;
            } else {
                return result.toString();
            }
        } catch (IllegalAccessException e) {
            throw new FormattingException("Can not access java method: " + method);
        } catch (InvocationTargetException e) {
            throw new FormattingException("Exception thrown while analyzing", e.getCause());
        } finally {
            method.setAccessible(accessible);
        }
    }
}
