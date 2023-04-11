package cn.codethink.xiaoming.expression.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h1>类型</h1>
 *
 * @author Chuanwise
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Type {
    
    /**
     * 获取 Java 类型
     *
     * @return Java 类型
     */
    Class<?> value() default Void.class;
    
    /**
     * 获取类型名
     *
     * @return 类型名
     */
    String name() default "";
}
