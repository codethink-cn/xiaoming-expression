package cn.codethink.xiaoming.expression.lang;

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
    public Expression analyze(AnalyzingContext context) {
        Preconditions.checkNotNull(context, "Analyzing context are null!");
        
        final Object[] objects;
        final Class<?> subjectClass = context.getSubject().getClass();
        if (parametersClasses.isEmpty()) {
            objects = EMPTY_ARRAY;
        } else {
            final Set<Object> properties = context.getProperties();
            objects = new Object[properties.size()];
    
            final Set<Object> propertiesCandidates = new HashSet<>();
            for (int i = 0; i < parametersClasses.size(); i++) {
                final Class<?> parameterClass = parametersClasses.get(i);
                if (parameterClass.isAssignableFrom(subjectClass)) {
                    objects[i] = context.getSubject();
                    continue;
                }
    
                for (Object property : properties) {
                    if (parameterClass.isAssignableFrom(property.getClass())) {
                        propertiesCandidates.add(property);
                    }
                }
                if (propertiesCandidates.isEmpty()) {
                    throw new NoSuchElementException("No such property that is an instance of " + parameterClass.getName());
                }
                if (propertiesCandidates.size() > 1) {
                    throw new IllegalArgumentException("Ambiguous properties class: " + parameterClass.getName() +
                        ", " + propertiesCandidates.size() + " candidates found!");
                }
                
                objects[i] = propertiesCandidates.iterator().next();
                propertiesCandidates.clear();
            }
        }
        
        final boolean accessible = method.isAccessible();
        try {
            method.setAccessible(true);
            return (Expression) method.invoke(subject, objects);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Can not access java method: " + method);
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException("Exception thrown while analyzing " + context.getSubject(), e.getCause());
        } finally {
            method.setAccessible(accessible);
        }
    }
    
    @Override
    public Class<?> getSubjectClass() {
        return subjectClass;
    }
}
