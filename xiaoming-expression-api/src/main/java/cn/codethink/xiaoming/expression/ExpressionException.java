package cn.codethink.xiaoming.expression;

/**
 * <h1>表达式异常</h1>
 *
 * @author Chuanwise
 */
public class ExpressionException
    extends Exception {
    
    public ExpressionException() {
    }
    
    public ExpressionException(String message) {
        super(message);
    }
    
    public ExpressionException(Throwable cause) {
        super(cause);
    }
    
    public ExpressionException(String message, Throwable cause) {
        super(message, cause);
    }
}
