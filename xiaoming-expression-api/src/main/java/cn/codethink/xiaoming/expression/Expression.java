package cn.codethink.xiaoming.expression;

import cn.codethink.xiaoming.expression.type.Type;

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
     * @throws CalculateException 计算过程中出现的异常
     */
    Object calculate() throws CalculateException;
    
    /**
     * 获取表达式类型
     *
     * @return 表达式类型
     */
    Type getType();
}
