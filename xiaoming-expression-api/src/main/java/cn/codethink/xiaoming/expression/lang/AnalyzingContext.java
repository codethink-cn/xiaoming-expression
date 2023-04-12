package cn.codethink.xiaoming.expression.lang;

import java.util.Set;

/**
 * <h1>分析环境</h1>
 *
 * @author Chuanwise
 */
public interface AnalyzingContext {
    
    /**
     * 获取分析主体
     *
     * @return 分析主体
     */
    Object getSubject();
    
    /**
     * 获取分析属性
     *
     * @return 分析属性
     */
    Set<Object> getProperties();
    
    /**
     * 获取解释器
     *
     * @return 解释器
     */
    Interpreter getInterpreter();
}
