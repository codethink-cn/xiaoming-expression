package cn.codethink.xiaoming.expression.analyzer;

import cn.codethink.xiaoming.expression.Expression;

/**
 * <h1>分析器</h1>
 * 
 * @author Chuanwise 
 */
public interface Analyzer {
    
    /**
     * 分析对象，构造表达式
     *
     * @param context 分析环境
     * @throws AnalyzingException 分析过程中抛出的异常
     * @return 表达式
     */
    Expression analyze(AnalyzingContext context) throws AnalyzingException;
}
