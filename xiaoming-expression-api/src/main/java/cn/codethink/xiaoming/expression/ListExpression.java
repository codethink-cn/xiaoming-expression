package cn.codethink.xiaoming.expression;

import java.util.List;
import java.util.Set;

/**
 * <h1>列表表达式</h1>
 *
 * @author Chuanwise
 */
public interface ListExpression
    extends Expression, Iterable<Expression> {
    
    /**
     * 获取表达式列表
     *
     * @return 表达式列表
     */
    List<Expression> getExpressions();
    
    @Override
    List<Object> calculate() throws CalculateException;
}
