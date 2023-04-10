package cn.codethink.xiaoming.expression.analyzer;

import cn.codethink.xiaoming.expression.ExpressionException;

/**
 * <h1>分析异常</h1>
 *
 * @author Chuanwise
 */
public class AnalyzingException
    extends ExpressionException {
    
    public AnalyzingException() {
    }
    
    public AnalyzingException(String message) {
        super(message);
    }
    
    public AnalyzingException(Throwable cause) {
        super(cause);
    }
    
    public AnalyzingException(String message, Throwable cause) {
        super(message, cause);
    }
}
