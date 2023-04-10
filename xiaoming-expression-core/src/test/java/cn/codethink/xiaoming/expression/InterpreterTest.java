package cn.codethink.xiaoming.expression;

import cn.codethink.xiaoming.expression.interpreter.CompileException;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class InterpreterTest {
    
    @Test
    public void intLiterals() throws ExpressionException {
        final Interpreter interpreter = Interpreter.getInstance();
        
        Assertions.assertEquals(6, interpreter.compile("6").calculate());
        Assertions.assertEquals(6, interpreter.compile("0x6").calculate());
        Assertions.assertEquals(6, interpreter.compile("06").calculate());
        
        Assertions.assertEquals(0x56, interpreter.compile("0x56").calculate());
        Assertions.assertEquals(0xFF, interpreter.compile("0xFF").calculate());
    }
    
    @Test
    public void compile(Object expect, String expression) throws ExpressionException {
        final Interpreter interpreter = Interpreter.getInstance();
        
        Assertions.assertEquals(expect, interpreter.compile(expression).calculate());
    }
    
    @Test
    public void noSuchType() {
        final Interpreter interpreter = Interpreter.getInstance();
        
        Assertions.assertThrows(CompileException.class, () -> interpreter.compile("Hello()").calculate());
    }
}
