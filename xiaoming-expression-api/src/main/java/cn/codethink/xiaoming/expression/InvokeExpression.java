package cn.codethink.xiaoming.expression;

import cn.codethink.xiaoming.expression.lang.Function;

import java.util.List;

/**
 * <h1>构造表达式</h1>
 *
 * @author Chuanwise
 */
public interface InvokeExpression
    extends Expression {
    
    /**
     * 获取构造函数
     *
     * @return 构造函数
     */
    Function getConstructor();
    
    /**
     * 获取参数表达式
     *
     * @return 参数表达式
     */
    List<Expression> getArguments();
}
