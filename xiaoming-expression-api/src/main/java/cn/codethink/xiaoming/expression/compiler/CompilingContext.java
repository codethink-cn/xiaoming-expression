package cn.codethink.xiaoming.expression.compiler;

import cn.codethink.xiaoming.expression.interpreter.Interpreter;

/**
 * <h1>编译环境</h1>
 *
 * @author Chuanwise
 */
public interface CompilingContext {
    
    /**
     * 获取编译配置
     *
     * @return 编译配置
     */
    CompilingConfiguration getConfiguration();
    
    /**
     * 获取解释器
     *
     * @return 解释器
     */
    Interpreter getInterpreter();
}
