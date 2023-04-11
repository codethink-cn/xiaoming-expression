package cn.codethink.xiaoming.expression;

import cn.codethink.xiaoming.expression.analyzer.AnalyzingException;
import cn.codethink.xiaoming.expression.annotation.Analyser;
import cn.codethink.xiaoming.expression.annotation.Constructor;
import cn.codethink.xiaoming.expression.annotation.Type;
import cn.codethink.xiaoming.expression.compiler.CompilingException;
import cn.codethink.xiaoming.expression.interpreter.ConfigurableInterpreter;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    
    @ParameterizedTest
    @CsvSource({
        "Hello()",
        "Hello(\"Hello World\")"
    })
    public void formatString(String expression) {
        final ConfigurableInterpreter interpreter =
            ConfigurableInterpreter.newInstance(Interpreter.getInstance());
        interpreter.registerType(new HelloType());
    
        final Expression exp = interpreter.compile(expression);
        final String format = interpreter.format(exp);
        Assertions.assertEquals(expression, format);
    }
    
    @Test
    public void registerType() throws ExpressionException {
        final ConfigurableInterpreter interpreter =
            ConfigurableInterpreter.newInstance(Interpreter.getInstance());
        
        interpreter.registerType(new HelloType());
    
        Assertions.assertThrows(CompilingException.class, () -> interpreter.compile("Hello(\"Hello World)").calculate());
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
    public void noSuchType() {
        final Interpreter interpreter = Interpreter.getInstance();
        
        Assertions.assertThrows(CompilingException.class, () -> interpreter.compile("Hello()").calculate());
    }
    
    @Test
    public void analyzeString() throws AnalyzingException {
        final Interpreter interpreter = Interpreter.getInstance();
        
        final Expression expression = interpreter.analyze("Hello");
        Assertions.assertEquals(ConstantExpressionImpl.of("Hello", interpreter.getTypeOrFail("String")), expression);
    }
}
