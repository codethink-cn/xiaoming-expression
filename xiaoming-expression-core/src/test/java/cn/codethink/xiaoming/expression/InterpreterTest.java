package cn.codethink.xiaoming.expression;

import cn.codethink.xiaoming.expression.annotation.Constructor;
import cn.codethink.xiaoming.expression.annotation.Type;
import cn.codethink.xiaoming.expression.interpreter.CompileException;
import cn.codethink.xiaoming.expression.interpreter.ConfigurableInterpreter;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;
import cn.codethink.xiaoming.expression.lang.ParameterImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InterpreterTest {
    
    private static class Hello {
    }
    
    @Type(Hello.class)
    private static class HelloType {
        @Constructor
        public Hello construct() {
            return new Hello();
        }
        
        @Constructor
        public Hello construct(String prompt) {
            System.out.println(prompt);
            return new Hello();
        }
    }
    
    @Test
    public void registerType() throws ExpressionException {
        final ConfigurableInterpreter interpreter =
            ConfigurableInterpreter.newInstance(Interpreter.getInstance());
        
        interpreter.registerType(new HelloType());
    
        final Object object = interpreter.compile("Hello(\"Hello World)").calculate();
        Assertions.assertInstanceOf(Hello.class, object);
    }
    
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
