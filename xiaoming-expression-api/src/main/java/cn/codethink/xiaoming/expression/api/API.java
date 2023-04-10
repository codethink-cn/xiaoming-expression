package cn.codethink.xiaoming.expression.api;

import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;
import cn.codethink.xiaoming.expression.compiler.CompilingConfiguration;
import cn.codethink.xiaoming.expression.interpreter.ConfigurableInterpreter;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;

/**
 * <h1>表达式 API</h1>
 *
 * <p>表达式 API 是 xiaoming-expression-api 调用 xiaoming-expression-core 的桥梁。</p>
 *
 * @author Chuanwise
 */
public interface API {
    
    Interpreter getInterpreter();
    ConfigurableInterpreter getConfigurableInterpreter(Interpreter interpreter);
    
    AnalyzingConfiguration getAnalyzationConfiguration();
    AnalyzingConfiguration.Builder getAnalyzationConfigurationBuilder();
    
    CompilingConfiguration getCompileConfiguration();
    CompilingConfiguration.Builder getCompileConfigurationBuilder();
}
