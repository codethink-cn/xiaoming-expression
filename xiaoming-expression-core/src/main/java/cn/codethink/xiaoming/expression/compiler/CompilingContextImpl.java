package cn.codethink.xiaoming.expression.compiler;

import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingContext;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;
import com.google.common.base.Preconditions;

public class CompilingContextImpl
    implements CompilingContext {
    
    private final CompilingConfiguration configuration;
    private final Interpreter interpreter;
    
    public CompilingContextImpl(CompilingConfiguration configuration, Interpreter interpreter) {
        Preconditions.checkNotNull(configuration, "Configuration is null!");
        Preconditions.checkNotNull(interpreter, "Interpreter is null!");
        
        this.configuration = configuration;
        this.interpreter = interpreter;
    }
    
    @Override
    public CompilingConfiguration getConfiguration() {
        return configuration;
    }
    
    @Override
    public Interpreter getInterpreter() {
        return interpreter;
    }
}
