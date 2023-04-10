package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.analyzer.Analyzer;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingContext;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingException;
import cn.codethink.xiaoming.expression.anlyzer.MethodAnalyzerImpl;
import cn.codethink.xiaoming.expression.constructor.Constructor;
import cn.codethink.xiaoming.expression.constructor.MethodConstructorImpl;
import cn.codethink.xiaoming.expression.type.*;
import com.google.common.base.Preconditions;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigurableInterpreterImpl
    extends AbstractInterpreter
    implements ConfigurableInterpreter {
    
    private final Map<String, Type> types = new ConcurrentHashMap<>();
    
    public ConfigurableInterpreterImpl(Interpreter parent) {
        super(parent);
        
        Preconditions.checkNotNull(parent, "Parent interpreter is null!");
    }
    
    @Override
    public void registerType(Object subject) {
        Preconditions.checkNotNull(subject, "Subject is null!");
    
        final Class<?> objectClass = subject.getClass();
        final cn.codethink.xiaoming.expression.annotation.Type typeAnnotation =
            objectClass.getAnnotation(cn.codethink.xiaoming.expression.annotation.Type.class);
        
        if (typeAnnotation == null) {
            throw new IllegalArgumentException("Class of subject " + objectClass.getName() + " hasn't annotation @Type!");
        }
    
        final Method[] declaredMethods = objectClass.getDeclaredMethods();
        final Class<?> javaClass = typeAnnotation.value() == Void.class ? objectClass : typeAnnotation.value();
        final String name = typeAnnotation.name().isEmpty() ? javaClass.getSimpleName() : typeAnnotation.name();
    
        final Set<Constructor> constructors = new HashSet<>();
        final Set<Analyzer> analyzers = new HashSet<>();
        final Type type = new JavaTypeImpl(name, javaClass, constructors, analyzers);
        for (Method declaredMethod : declaredMethods) {
            
            // 检查方法是否是构造方法
            final cn.codethink.xiaoming.expression.annotation.Constructor constructorAnnotation =
                declaredMethod.getAnnotation(cn.codethink.xiaoming.expression.annotation.Constructor.class);
            if (constructorAnnotation != null) {
                if (!javaClass.isAssignableFrom(declaredMethod.getReturnType())) {
                    throw new IllegalArgumentException("Illegal return type of method: " + declaredMethod + ", expect: " + javaClass.getName());
                }
    
                constructors.add(new MethodConstructorImpl(type, subject, declaredMethod));
            }
    
            // 检查方法是否是分析方法
            final cn.codethink.xiaoming.expression.annotation.Analyser analyserAnnotation =
                declaredMethod.getAnnotation(cn.codethink.xiaoming.expression.annotation.Analyser.class);
            if (analyserAnnotation != null) {
                analyzers.add(new MethodAnalyzerImpl(type, subject, declaredMethod));
            }
        }
        
        registerType(type);
    }
    
    @Override
    protected Type getType0(String name) {
        return types.get(name);
    }
    
    @Override
    protected Expression analyze0(AnalyzingContext context) throws AnalyzingException {
        final Object subject = context.getSubject();
        final Class<?> subjectClass = subject.getClass();

        for (Type type : types.values()) {
            if (type.getJavaClass().isAssignableFrom(subjectClass)) {
                for (Analyzer analyzer : type.getAnalysers()) {
                    final Expression expression = analyzer.analyze(context);
                    if (expression != null) {
                        return expression;
                    }
                }
            }
        }
        return null;
    }
    
    @Override
    public void registerType(Type type) {
        Preconditions.checkNotNull(type, "Type is null!");
        
        final Type sameNameType = getType(type.getName());
        if (sameNameType != null) {
            throw new IllegalArgumentException("Type " + type.getName() + " already exists!");
        }
        types.put(type.getName(), type);
    }
}
