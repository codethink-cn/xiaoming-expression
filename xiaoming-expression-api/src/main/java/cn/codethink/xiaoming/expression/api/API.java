package cn.codethink.xiaoming.expression.api;

import cn.codethink.xiaoming.expression.LiteralExpression;
import cn.codethink.xiaoming.expression.formatter.FormattingConfiguration;
import cn.codethink.xiaoming.expression.lang.Interpreter;

/**
 * <h1>表达式 API</h1>
 *
 * <p>表达式 API 是 xiaoming-expression-api 调用 xiaoming-expression-core 的桥梁。</p>
 *
 * @author Chuanwise
 */
public interface API {
    
    Interpreter getInterpreter();
    Interpreter getInterpreter(Interpreter interpreter);
    
    LiteralExpression getNullLiteralExpression();
    LiteralExpression getLiteralExpression(int value);
    LiteralExpression getLiteralExpression(double value);
    LiteralExpression getLiteralExpression(boolean value);
    LiteralExpression getLiteralExpression(char value);
    LiteralExpression getLiteralExpression(String value);
    
    FormattingConfiguration getFormattingConfiguration();
    FormattingConfiguration.Builder getFormattingConfigurationBuilder();
}
