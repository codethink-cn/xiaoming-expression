package cn.codethink.xiaoming.expression;

/**
 * <h1>常量表达式</h1>
 *
 * @author Chuanwise
 */
public interface ConstantExpression
    extends Expression {
    
    /**
     * 获取常量
     *
     * @return 常量
     */
    Object getConstant();
    
    @Override
    Object calculate();
}
