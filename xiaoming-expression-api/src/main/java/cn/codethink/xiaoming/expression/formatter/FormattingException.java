package cn.codethink.xiaoming.expression.formatter;

import cn.codethink.xiaoming.expression.ExpressionException;

/**
 * <h1>格式化异常</h1>
 *
 * @author Chuanwise
 */
public class FormattingException
    extends ExpressionException {
    
    public FormattingException() {
    }
    
    public FormattingException(String message) {
        super(message);
    }
    
    public FormattingException(Throwable cause) {
        super(cause);
    }
    
    public FormattingException(String message, Throwable cause) {
        super(message, cause);
    }
}
