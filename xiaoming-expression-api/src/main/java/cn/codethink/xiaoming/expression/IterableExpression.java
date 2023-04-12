package cn.codethink.xiaoming.expression;

import java.util.List;

/**
 * <h1>可迭代表达式</h1>
 *
 * @author Chuanwise
 */
public interface IterableExpression
    extends Expression, Iterable<Expression> {
    
    /**
     * 获取表达式列表
     *
     * @return 表达式列表
     */
    List<Expression> getExpressions();
    
    @Override
    Iterable<Object> interpret() throws CalculateException;
}
