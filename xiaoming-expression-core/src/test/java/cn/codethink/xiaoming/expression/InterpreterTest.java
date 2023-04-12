package cn.codethink.xiaoming.expression;

import cn.codethink.xiaoming.expression.lang.Interpreter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InterpreterTest {
    
    @Test
    public void compile() {
        final Interpreter interpreter = Interpreter.newInstance();
        Assertions.assertEquals(6, interpreter.compile("6").interpret());
    }
}
