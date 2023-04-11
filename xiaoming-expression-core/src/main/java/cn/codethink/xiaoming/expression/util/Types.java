package cn.codethink.xiaoming.expression.util;

import cn.codethink.xiaoming.expression.analyzer.Analyzer;
import cn.codethink.xiaoming.expression.anlyzer.DefaultAnalyzerImpl;
import cn.codethink.xiaoming.expression.anlyzer.MethodAnalyzerImpl;
import cn.codethink.xiaoming.expression.constructor.Constructor;
import cn.codethink.xiaoming.expression.constructor.MethodConstructorImpl;
import cn.codethink.xiaoming.expression.formatter.FormattingContext;
import cn.codethink.xiaoming.expression.formatter.FormattingException;
import cn.codethink.xiaoming.expression.type.JavaTypeImpl;
import cn.codethink.xiaoming.expression.type.Type;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Types {
    private Types() {
        throw new UnsupportedOperationException("No types instance for you!");
    }
    
    public static Type parseType(Object subject) {
        
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
        
        // 如果包含无参构造，则生成默认分析方法
        final Constructor defaultConstructor = type.getConstructor(Collections.emptyList());
        if (defaultConstructor != null && analyzers.isEmpty()) {
            analyzers.add(new DefaultAnalyzerImpl(defaultConstructor));
        }
    
        return type;
    }
}
