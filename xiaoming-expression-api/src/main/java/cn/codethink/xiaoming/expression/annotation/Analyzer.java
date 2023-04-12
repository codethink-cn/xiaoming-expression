package cn.codethink.xiaoming.expression.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <h1>分析器</h1>
 *
 * @author Chuanwise
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Analyzer {
    
    /**
     * 获取分析器类型
     *
     * @return 分析器类型
     */
    Class<?> value() default Void.class;
}
