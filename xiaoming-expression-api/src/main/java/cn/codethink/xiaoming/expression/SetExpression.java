package cn.codethink.xiaoming.expression;

import java.util.List;
import java.util.Set;

/**
 * <h1>集合表达式</h1>
 *
 * @author Chuanwise
 */
public interface SetExpression
    extends Expression, Iterable<Expression> {
    
    /**
     * 获取表达式列表
     *
     * @return 表达式列表
     */
    List<Expression> getExpressions();
    
    @Override
    Set<Object> interpret();
}
