package cn.codethink.xiaoming.expression.api;

import cn.codethink.xiaoming.expression.formatter.FormattingConfiguration;
import cn.codethink.xiaoming.expression.formatter.FormattingConfigurationImpl;
import cn.codethink.xiaoming.expression.lang.*;
import cn.codethink.xiaoming.expression.util.Interpreters;

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
    public ConfigurableInterpreter getConfigurableInterpreter() {
        return Interpreters.getInstance();
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
    
    @Override
    public FormattingConfiguration getFormattingConfiguration() {
        return FormattingConfigurationImpl.getInstance();
    }
    
    @Override
    public FormattingConfiguration.Builder getFormattingConfigurationBuilder() {
        return new FormattingConfigurationImpl.BuilderImpl();
    }
}
