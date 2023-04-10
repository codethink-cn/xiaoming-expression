package cn.codethink.xiaoming.expression.util;

import cn.codethink.xiaoming.expression.analyzer.Analyzer;
import cn.codethink.xiaoming.expression.anlyzer.MethodAnalyzerImpl;
import cn.codethink.xiaoming.expression.constructor.Constructor;
import cn.codethink.xiaoming.expression.constructor.MethodConstructorImpl;
import cn.codethink.xiaoming.expression.formatter.Formatter;
import cn.codethink.xiaoming.expression.formatter.FormattingContext;
import cn.codethink.xiaoming.expression.formatter.FormattingException;
import cn.codethink.xiaoming.expression.formatter.MethodFormatterImpl;
import cn.codethink.xiaoming.expression.type.JavaTypeImpl;
import cn.codethink.xiaoming.expression.type.Type;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class Types {
    private Types() {
        throw new UnsupportedOperationException("No types instance for you!");
    }
    
    private static class ForwardFormatter
        implements Formatter {
        
        private Formatter formatter;
    
        @Override
        public String format(Object subject, FormattingContext context) throws FormattingException {
            return formatter.format(subject, context);
        }
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
        final ForwardFormatter formatter = new ForwardFormatter();
        final Set<Analyzer> analyzers = new HashSet<>();
        final Type type = new JavaTypeImpl(name, javaClass, constructors, analyzers, formatter);
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
            
            // 检查方法是否是格式化方法
            final cn.codethink.xiaoming.expression.annotation.Formatter formatterAnnotation =
                declaredMethod.getAnnotation(cn.codethink.xiaoming.expression.annotation.Formatter.class);
            if (formatterAnnotation != null) {
                if (formatter.formatter == null) {
                    formatter.formatter = new MethodFormatterImpl(type, subject, declaredMethod);
                } else {
                    throw new IllegalArgumentException("Redefine formatter");
                }
            }
        }
        
        if (formatter.formatter == null) {
            throw new IllegalArgumentException("No formatter declaration found for " + type.getName() + "!");
        }
        
        return type;
    }
}
