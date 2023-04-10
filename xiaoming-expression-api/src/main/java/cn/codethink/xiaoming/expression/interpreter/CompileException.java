package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.ExpressionException;

/**
 * <h1>编译异常</h1>
 *
 * @author Chuanwise
 */
public class CompileException
    extends ExpressionException {
    
    public CompileException() {
    }
    
    public CompileException(String message) {
        super(message);
    }
    
    public CompileException(Throwable cause) {
        super(cause);
    }
    
    public CompileException(String message, Throwable cause) {
        super(message, cause);
    }
}
