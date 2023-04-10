package cn.codethink.xiaoming.expression;

import cn.codethink.xiaoming.expression.constructor.Constructor;

import java.util.List;

/**
 * <h1>构造表达式</h1>
 *
 * @author Chuanwise
 */
public interface ConstructExpression
    extends Expression {
    
    /**
     * 获取构造函数
     *
     * @return 构造函数
     */
    Constructor getConstructor();
    
    /**
     * 获取参数表达式
     *
     * @return 参数表达式
     */
    List<Expression> getParameterExpressions();
}
