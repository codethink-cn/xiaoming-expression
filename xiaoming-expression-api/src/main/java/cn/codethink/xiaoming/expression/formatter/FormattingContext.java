package cn.codethink.xiaoming.expression.formatter;

import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;

/**
 * <h1>格式化环境</h1>
 *
 * @author Chuanwise
 */
public interface FormattingContext {
    
    /**
     * 获取格式化配置
     *
     * @return 格式化配置
     */
    FormattingConfiguration getConfiguration();
    
    /**
     * 获取表达式
     *
     * @return 表达式
     */
    Expression getExpression();
    
    /**
     * 获取解释器
     *
     * @return 解释器
     */
    Interpreter getInterpreter();
}
