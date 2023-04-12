/*
 * Copyright 2023 CodeThink Technologies and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
