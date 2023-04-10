package cn.codethink.xiaoming.expression.anlyzer;

import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.analyzer.Analyzer;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingContext;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingException;
import cn.codethink.xiaoming.expression.annotation.Subject;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;
import cn.codethink.xiaoming.expression.type.Type;
import com.google.common.base.Preconditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class MethodAnalyzerImpl
    implements Analyzer {
    
    private final Selector[] selectors;
    private final Method method;
    private final Object subject;
    private final Type type;
    
    private interface Selector {
        Object select(AnalyzingContext context);
    }
    
    private static class AnalyzingContextSelector
        implements Selector {
        
        private static final AnalyzingContextSelector INSTANCE = new AnalyzingContextSelector();
        
        @Override
        public Object select(AnalyzingContext context) {
            return context;
        }
    }
    
    private static class AnalyzingConfigurationSelector
        implements Selector {
        
        private static final AnalyzingConfigurationSelector INSTANCE = new AnalyzingConfigurationSelector();
        
        @Override
        public Object select(AnalyzingContext context) {
            return context.getConfiguration();
        }
    }
    
    private static class InterpreterSelector
        implements Selector {
        
        private static final InterpreterSelector INSTANCE = new InterpreterSelector();
        
        @Override
        public Object select(AnalyzingContext context) {
            return context.getInterpreter();
        }
    }
    
    private static class SubjectSelector
        implements Selector {
        
        private static final SubjectSelector INSTANCE = new SubjectSelector();
        
        @Override
        public Object select(AnalyzingContext context) {
            return context.getSubject();
        }
    }
    
    public MethodAnalyzerImpl(Type type, Object subject, Method method) {
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
        this.selectors = new Selector[parameters.length];
        
        for (int i = 0; i < parameters.length; i++) {
            final Parameter parameter = parameters[i];
            
            final Class<?> parameterType = parameter.getType();
            if (parameterType.isAnnotationPresent(Subject.class)) {
                selectors[i] = SubjectSelector.INSTANCE;
                continue;
            }
            
            if (!Interpreter.class.isAssignableFrom(type.getJavaClass()) && parameterType.isAssignableFrom(Interpreter.class)) {
                selectors[i] = InterpreterSelector.INSTANCE;
                continue;
            }
            
            if (!AnalyzingConfiguration.class.isAssignableFrom(type.getJavaClass()) && parameterType.isAssignableFrom(AnalyzingConfiguration.class)) {
                selectors[i] = AnalyzingConfigurationSelector.INSTANCE;
                continue;
            }
            
            if (!AnalyzingContext.class.isAssignableFrom(type.getJavaClass()) && parameterType.isAssignableFrom(AnalyzingContext.class)) {
                selectors[i] = AnalyzingContextSelector.INSTANCE;
                continue;
            }
            
            if (parameterType.isAssignableFrom(type.getJavaClass())) {
                selectors[i] = SubjectSelector.INSTANCE;
                continue;
            }
            
            throw new IllegalArgumentException("Unexpected parameter type: " + parameterType.getName() + ", " +
                "expected: [ Interpreter, AnalyzingContext, AnalyzingConfiguration, " + type.getJavaClass().getSimpleName() + " ]");
        }
        
        this.method = method;
        this.subject = subject;
        this.type = type;
    }
    
    @Override
    public Expression analyze(AnalyzingContext context) throws AnalyzingException {
        Preconditions.checkNotNull(context, "Analyzing context are null!");
        
        final Object[] objects = new Object[selectors.length];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = selectors[i].select(context);
        }
        
        final boolean accessible = method.isAccessible();
        try {
            method.setAccessible(true);
            return (Expression) method.invoke(subject, objects);
        } catch (IllegalAccessException e) {
            throw new AnalyzingException("Can not access java method: " + method);
        } catch (InvocationTargetException e) {
            throw new AnalyzingException("Exception thrown while analyzing " + context.getSubject(), e.getCause());
        } finally {
            method.setAccessible(accessible);
        }
    }
}
