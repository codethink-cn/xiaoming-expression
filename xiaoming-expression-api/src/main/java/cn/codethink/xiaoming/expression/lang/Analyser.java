package cn.codethink.xiaoming.expression.lang;

import cn.codethink.xiaoming.expression.Expression;

/**
 * <h1>分析器</h1>
 * 
 * @author Chuanwise 
 */
public interface Analyser {
    
    /**
     * 分析对象，构造表达式
     *
     * @param object 对象
     * @return 表达式
     */
    Expression analysis(Object object);
}
