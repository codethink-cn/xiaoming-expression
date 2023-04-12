package cn.codethink.xiaoming.expression.api;

import cn.codethink.xiaoming.expression.LiteralExpression;
import cn.codethink.xiaoming.expression.LiteralExpressionImpl;
import cn.codethink.xiaoming.expression.formatter.FormattingConfiguration;
import cn.codethink.xiaoming.expression.formatter.FormattingConfigurationImpl;
import cn.codethink.xiaoming.expression.lang.*;

public class APIImpl
    implements API {
    
    @Override
    public Interpreter getInterpreter() {
        return new InterpreterImpl();
    }
    
    @Override
    public Interpreter getInterpreter(Interpreter interpreter) {
        return new InterpreterImpl(interpreter);
    }
    
    @Override
    public LiteralExpression getNullLiteralExpression() {
        return LiteralExpressionImpl.ofNull();
    }
    
    @Override
    public LiteralExpression getLiteralExpression(int value) {
        return LiteralExpressionImpl.of(value);
    }
    
    @Override
    public LiteralExpression getLiteralExpression(double value) {
        return LiteralExpressionImpl.of(value);
    }
    
    @Override
    public LiteralExpression getLiteralExpression(boolean value) {
        return LiteralExpressionImpl.of(value);
    }
    
    @Override
    public LiteralExpression getLiteralExpression(char value) {
        return LiteralExpressionImpl.of(value);
    }
    
    @Override
    public LiteralExpression getLiteralExpression(String value) {
        return LiteralExpressionImpl.of(value);
    }
    
    @Override
    public FormattingConfiguration getFormattingConfiguration() {
        return FormattingConfigurationImpl.getInstance();
    }
    
    @Override
    public FormattingConfiguration.Builder getFormattingConfigurationBuilder() {
        return new FormattingConfigurationImpl.BuilderImpl();
    }
}
