package cn.codethink.xiaoming.expression.interpreter;

/**
 * <h1>语法异常</h1>
 *
 * @author Chuanwise
 */
public class SyntaxException
    extends CompileException {
    
    public SyntaxException() {
    }
    
    public SyntaxException(String message) {
        super(message);
    }
    
    public SyntaxException(Throwable cause) {
        super(cause);
    }
    
    public SyntaxException(String message, Throwable cause) {
        super(message, cause);
    }
}
