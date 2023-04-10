package cn.codethink.xiaoming.expression;

/**
 * <h1>计算异常</h1>
 *
 * @author Chuanwise
 */
public class CalculateException
    extends ExpressionException {
    
    public CalculateException() {
    }
    
    public CalculateException(String message) {
        super(message);
    }
    
    public CalculateException(Throwable cause) {
        super(cause);
    }
    
    public CalculateException(String message, Throwable cause) {
        super(message, cause);
    }
}
