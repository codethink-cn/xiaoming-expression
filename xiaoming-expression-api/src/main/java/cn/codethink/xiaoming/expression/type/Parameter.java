package cn.codethink.xiaoming.expression.type;

/**
 * <h1>参数</h1>
 *
 * @author Chuanwise
 */
public interface Parameter {
    
    /**
     * 获取参数类型
     *
     * @return 参数类型
     */
    Type getType();
    
    /**
     * 获取参数名
     *
     * @return 参数名
     */
    String getName();
}
