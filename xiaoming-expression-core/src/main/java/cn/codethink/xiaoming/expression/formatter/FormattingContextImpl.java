package cn.codethink.xiaoming.expression.formatter;

import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingContext;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;
import com.google.common.base.Preconditions;

public class FormattingContextImpl
    implements FormattingContext {
    
    private final Expression expression;
    private final FormattingConfiguration configuration;
    private final Interpreter interpreter;
    
    public FormattingContextImpl(Expression expression, FormattingConfiguration configuration, Interpreter interpreter) {
        Preconditions.checkNotNull(expression, "Expression is null!");
        Preconditions.checkNotNull(configuration, "Formatting configuration is null!");
        Preconditions.checkNotNull(interpreter, "Interpreter is null!");
        
        this.expression = expression;
        this.configuration = configuration;
        this.interpreter = interpreter;
    }
    
    @Override
    public FormattingConfiguration getConfiguration() {
        return configuration;
    }
    
    @Override
    public Expression getExpression() {
        return expression;
    }
    
    @Override
    public Interpreter getInterpreter() {
        return interpreter;
    }
}
