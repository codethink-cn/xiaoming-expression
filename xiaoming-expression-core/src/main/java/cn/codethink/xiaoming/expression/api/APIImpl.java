package cn.codethink.xiaoming.expression.api;

import cn.codethink.xiaoming.expression.interpreter.CompileConfiguration;
import cn.codethink.xiaoming.expression.interpreter.CompileConfigurationImpl;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;
import cn.codethink.xiaoming.expression.interpreter.InterpreterImpl;

public class APIImpl
    implements API {
    
    @Override
    public Interpreter getInterpreter() {
        return InterpreterImpl.getInstance();
    }
    
    @Override
    public CompileConfiguration getCompileConfiguration() {
        return CompileConfigurationImpl.getInstance();
    }
    
    @Override
    public CompileConfiguration.Builder getCompileConfigurationBuilder() {
        return new CompileConfigurationImpl.BuilderImpl();
    }
}
