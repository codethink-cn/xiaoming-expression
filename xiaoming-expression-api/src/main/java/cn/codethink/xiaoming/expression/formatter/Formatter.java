package cn.codethink.xiaoming.expression.formatter;

import cn.codethink.xiaoming.expression.Expression;

/**
 * <h1>格式化器</h1>
 * 
 * @author Chuanwise 
 */
public interface Formatter {
    
    /**
     * 分析对象，构造表达式
     *
     * @param context 分析环境
     * @throws FormattingException 分析过程中抛出的异常
     * @return 表达式
     */
    String format(Object value, FormattingContext context) throws FormattingException;
}
