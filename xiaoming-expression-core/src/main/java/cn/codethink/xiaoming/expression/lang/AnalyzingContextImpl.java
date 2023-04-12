package cn.codethink.xiaoming.expression.lang;

import com.google.common.base.Preconditions;

import java.util.Set;

public class AnalyzingContextImpl
    implements AnalyzingContext {
    
    private final Object subject;
    private final Set<Object> properties;
    private final Interpreter interpreter;
    
    public AnalyzingContextImpl(Object subject, Set<Object> properties, Interpreter interpreter) {
        Preconditions.checkNotNull(subject, "Subject is null!");
        Preconditions.checkNotNull(properties, "Properties are null!");
        Preconditions.checkNotNull(interpreter, "Interpreter is null!");
        
        this.subject = subject;
        this.properties = properties;
        this.interpreter = interpreter;
    }
    
    @Override
    public Object getSubject() {
        return subject;
    }
    
    @Override
    public Set<Object> getProperties() {
        return properties;
    }
    
    @Override
    public Interpreter getInterpreter() {
        return interpreter;
    }
}
