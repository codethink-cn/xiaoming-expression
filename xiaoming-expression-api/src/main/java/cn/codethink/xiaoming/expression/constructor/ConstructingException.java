package cn.codethink.xiaoming.expression.constructor;

import cn.codethink.xiaoming.expression.ExpressionException;

/**
 * <h1>构造异常</h1>
 *
 * @author Chuanwise
 */
public class ConstructingException
    extends ExpressionException {
    
    public ConstructingException() {
    }
    
    public ConstructingException(String message) {
        super(message);
    }
    
    public ConstructingException(Throwable cause) {
        super(cause);
    }
    
    public ConstructingException(String message, Throwable cause) {
        super(message, cause);
    }
}
