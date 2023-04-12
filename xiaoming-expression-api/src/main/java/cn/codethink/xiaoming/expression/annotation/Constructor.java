package cn.codethink.xiaoming.expression.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h1>构造函数风格的方法</h1>
 *
 * @author Chuanwise
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constructor {
    
    /**
     * 获取方法名
     *
     * @return 方法名
     */
    String value() default "";
}
