package cn.codethink.xiaoming.expression.type;

/**
 * <h1>参数</h1>
 *
 * @author Chuanwise
 */
public interface Parameter {
    
    /**
     * 获取 Java 类型
     *
     * @return Java 类型
     */
    Class<?> getJavaClass();
    
    /**
     * 获取参数名
     *
     * @return 参数名
     */
    String getName();
}
