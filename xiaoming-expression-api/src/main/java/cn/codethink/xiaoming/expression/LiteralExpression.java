package cn.codethink.xiaoming.expression;

import cn.codethink.xiaoming.expression.api.APIFactory;

/**
 * <h1>字面量表达式</h1>
 *
 * @author Chuanwise
 */
public interface LiteralExpression
    extends Expression {
    
    /**
     * 获取代表 null 的字面量表达式
     *
     * @return 字面量表达式
     */
    static LiteralExpression ofNull() {
        return APIFactory.getInstance().getNullLiteralExpression();
    }
    
    /**
     * 构造字面量表达式
     *
     * @param value 值
     * @return 字面量表达式
     */
    static LiteralExpression of(int value) {
        return APIFactory.getInstance().getLiteralExpression(value);
    }
    
    /**
     * 构造字面量表达式
     *
     * @param value 值
     * @return 字面量表达式
     */
    static LiteralExpression of(double value) {
        return APIFactory.getInstance().getLiteralExpression(value);
    }
    
    /**
     * 构造字面量表达式
     *
     * @param value 值
     * @return 字面量表达式
     */
    static LiteralExpression of(char value) {
        return APIFactory.getInstance().getLiteralExpression(value);
    }
    
    /**
     * 构造字面量表达式
     *
     * @param value 值
     * @return 字面量表达式
     */
    static LiteralExpression of(boolean value) {
        return APIFactory.getInstance().getLiteralExpression(value);
    }
    
    /**
     * 构造字面量表达式
     *
     * @param value 值
     * @return 字面量表达式
     */
    static LiteralExpression of(String value) {
        return APIFactory.getInstance().getLiteralExpression(value);
    }
    
    /**
     * 获取常量
     *
     * @return 常量
     */
    Object getValue();
}