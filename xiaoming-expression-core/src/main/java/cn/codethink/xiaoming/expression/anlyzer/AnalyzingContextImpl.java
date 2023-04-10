package cn.codethink.xiaoming.expression.anlyzer;

import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingContext;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;
import com.google.common.base.Preconditions;

public class AnalyzingContextImpl
    implements AnalyzingContext {
    
    private final Object subject;
    private final AnalyzingConfiguration configuration;
    private final Interpreter interpreter;
    
    public AnalyzingContextImpl(Object subject, AnalyzingConfiguration configuration, Interpreter interpreter) {
        Preconditions.checkNotNull(subject, "Subject is null!");
        Preconditions.checkNotNull(configuration, "Analyzing configuration is null!");
        Preconditions.checkNotNull(interpreter, "Interpreter is null!");
        
        this.subject = subject;
        this.configuration = configuration;
        this.interpreter = interpreter;
    }
    
    @Override
    public AnalyzingConfiguration getConfiguration() {
        return configuration;
    }
    
    @Override
    public Object getSubject() {
        return subject;
    }
    
    @Override
    public Interpreter getInterpreter() {
        return interpreter;
    }
}
