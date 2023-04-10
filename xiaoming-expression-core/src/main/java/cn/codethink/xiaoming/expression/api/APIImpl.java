package cn.codethink.xiaoming.expression.api;

import cn.codethink.xiaoming.expression.interpreter.*;

public class APIImpl
    implements API {
    
    @Override
    public Interpreter getInterpreter() {
        return RootInterpreterImpl.getInstance();
    }
    
    @Override
    public ConfigurableInterpreter getConfigurableInterpreter(Interpreter interpreter) {
        return new ConfigurableInterpreterImpl(interpreter);
    }
    
    @Override
    public AnalyzationConfiguration getAnalyzationConfiguration() {
        return AnalyzationConfigurationImpl.getInstance();
    }
    
    @Override
    public AnalyzationConfiguration.Builder getAnalyzationConfigurationBuilder() {
        return new AnalyzationConfigurationImpl.BuilderImpl();
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
