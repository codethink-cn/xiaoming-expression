package cn.codethink.xiaoming.expression.analyzer;

import cn.codethink.xiaoming.expression.interpreter.Interpreter;

/**
 * <h1>分析环境</h1>
 *
 * @author Chuanwise
 */
public interface AnalyzingContext {
    
    /**
     * 获取分析配置
     *
     * @return 分析配置
     */
    AnalyzingConfiguration getConfiguration();
    
    /**
     * 获取分析主体
     *
     * @return 分析主体
     */
    Object getSubject();
    
    /**
     * 获取解释器
     *
     * @return 解释器
     */
    Interpreter getInterpreter();
}
