package cn.codethink.xiaoming.expression.lang;

import java.util.List;

/**
 * <h1>函数</h1>
 *
 * @author Chuanwise
 */
public interface Function {
    
    /**
     * 调用函数
     *
     * @param arguments 参数
     * @return 对象
     */
    Object invoke(List<Object> arguments);
    
    /**
     * 获取方法名
     *
     * @return 方法名
     */
    String getName();
    
    /**
     * 获取返回类型
     *
     * @return 返回类型
     */
    Class<?> getReturnClass();
    
    /**
     * 获取参数类型
     *
     * @return 参数类型
     */
    List<Class<?>> getParametersClasses();
}
