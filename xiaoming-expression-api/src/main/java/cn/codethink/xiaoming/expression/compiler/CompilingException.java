package cn.codethink.xiaoming.expression.compiler;

import cn.codethink.xiaoming.expression.ExpressionException;

/**
 * <h1>编译异常</h1>
 *
 * @author Chuanwise
 */
public class CompilingException
    extends ExpressionException {
    
    public CompilingException() {
    }
    
    public CompilingException(String message) {
        super(message);
    }
    
    public CompilingException(Throwable cause) {
        super(cause);
    }
    
    public CompilingException(String message, Throwable cause) {
        super(message, cause);
    }
}
