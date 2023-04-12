package cn.codethink.xiaoming.expression;

/**
 * <h1>表达式</h1>
 *
 * @author Chuanwise
 */
public interface Expression {
    
    /**
     * 计算表达式的值
     *
     * @return 表达式的值
     */
    Object interpret();
    
    /**
     * 获取表达式类型
     *
     * @return 表达式类型
     */
    Class<?> getResultClass();
}
