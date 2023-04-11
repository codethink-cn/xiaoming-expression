package cn.codethink.xiaoming.expression.util;

import cn.codethink.xiaoming.expression.analyzer.Analyzer;
import cn.codethink.xiaoming.expression.anlyzer.DefaultAnalyzerImpl;
import cn.codethink.xiaoming.expression.anlyzer.MethodAnalyzerImpl;
import cn.codethink.xiaoming.expression.constructor.Constructor;
import cn.codethink.xiaoming.expression.constructor.MethodConstructorImpl;
import cn.codethink.xiaoming.expression.type.JavaTypeImpl;
import cn.codethink.xiaoming.expression.type.Type;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

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
    
    private static class TypeContainer {
        private final Type type;
        private final Set<Constructor> constructors = new HashSet<>();
        private final Set<Analyzer> analyzers = new HashSet<>();
        
        public TypeContainer(String name, Class<?> javaClass) {
            type = new JavaTypeImpl(name, javaClass, constructors, analyzers);
        }
    }
    
    public static Set<Type> parseTypes(Object subject) {
        final Class<?> objectClass = subject.getClass();
        final Method[] declaredMethods = objectClass.getDeclaredMethods();
        
        final Map<String, TypeContainer> containers = new HashMap<>();
        for (Method declaredMethod : declaredMethods) {
            final cn.codethink.xiaoming.expression.annotation.Type typeAnnotation =
                declaredMethod.getAnnotation(cn.codethink.xiaoming.expression.annotation.Type.class);
            if (typeAnnotation == null) {
                continue;
            }
    
            final Class<?> javaClass = typeAnnotation.value() == Void.class ? declaredMethod.getReturnType() : typeAnnotation.value();
            final String name = typeAnnotation.name().isEmpty() ? javaClass.getSimpleName() : typeAnnotation.name();
    
            TypeContainer container = containers.get(name);
            if (container == null) {
                container = new TypeContainer(name, javaClass);
                containers.put(name, new TypeContainer(name, javaClass));
            }
            
            final Set<Constructor> constructors = container.constructors;
            final Set<Analyzer> analyzers = container.analyzers;
            final Type type = container.type;
    
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
        for (TypeContainer container : containers.values()) {
            final Type type = container.type;
            final Set<Analyzer> analyzers = container.analyzers;
            
            final Constructor defaultConstructor = type.getConstructor(Collections.emptyList());
            if (defaultConstructor != null && analyzers.isEmpty()) {
                analyzers.add(new DefaultAnalyzerImpl(defaultConstructor));
            }
        }
    
        return containers.values().stream()
            .map(x -> x.type)
            .collect(Collectors.toSet());
    }
}
