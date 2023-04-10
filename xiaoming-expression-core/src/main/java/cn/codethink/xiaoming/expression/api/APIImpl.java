package cn.codethink.xiaoming.expression.api;

import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;
import cn.codethink.xiaoming.expression.anlyzer.AnalyzingConfigurationImpl;
import cn.codethink.xiaoming.expression.compiler.CompilingConfiguration;
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
    public AnalyzingConfiguration getAnalyzationConfiguration() {
        return AnalyzingConfigurationImpl.getInstance();
    }
    
    @Override
    public AnalyzingConfiguration.Builder getAnalyzationConfigurationBuilder() {
        return new AnalyzingConfigurationImpl.BuilderImpl();
    }
    
    @Override
    public CompilingConfiguration getCompileConfiguration() {
        return CompilingConfigurationImpl.getInstance();
    }
    
    @Override
    public CompilingConfiguration.Builder getCompileConfigurationBuilder() {
        return new CompilingConfigurationImpl.BuilderImpl();
    }
}
