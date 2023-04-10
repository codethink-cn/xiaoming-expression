package cn.codethink.xiaoming.expression.api;

import cn.codethink.xiaoming.expression.interpreter.CompilationConfiguration;
import cn.codethink.xiaoming.expression.interpreter.CompilationConfigurationImpl;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;
import cn.codethink.xiaoming.expression.interpreter.InterpreterImpl;

public class APIImpl
    implements API {
    
    @Override
    public Interpreter getInterpreter() {
        return InterpreterImpl.getInstance();
    }
    
    @Override
    public CompilationConfiguration getCompileConfiguration() {
        return CompilationConfigurationImpl.getInstance();
    }
    
    @Override
    public CompilationConfiguration.Builder getCompileConfigurationBuilder() {
        return new CompilationConfigurationImpl.BuilderImpl();
    }
}
