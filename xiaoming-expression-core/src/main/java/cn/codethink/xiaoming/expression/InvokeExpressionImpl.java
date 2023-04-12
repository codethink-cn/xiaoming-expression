/*
 * Copyright 2023. CodeThink Technologies and contributors.
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

import cn.codethink.xiaoming.expression.lang.Function;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class InvokeExpressionImpl
    implements InvokeExpression {
    
    private final Function function;
    private final List<Expression> arguments;
    
    public InvokeExpressionImpl(Function function, List<Expression> arguments) {
        Preconditions.checkNotNull(function, "Function is null!");
        Preconditions.checkNotNull(arguments, "Arguments expressions are null!");
        
        this.function = function;
        this.arguments = arguments;
    }
    
    @Override
    public Function getConstructor() {
        return function;
    }
    
    @Override
    public List<Expression> getArguments() {
        return arguments;
    }
    
    @Override
    public Object interpret() {
        if (arguments.isEmpty()) {
            return function.invoke(Collections.emptyList());
        } else {
            final List<Object> arguments = new ArrayList<>(this.arguments.size());
            for (Expression parameterExpression : this.arguments) {
                final Object argument = parameterExpression.interpret();
                arguments.add(argument);
            }
            return function.invoke(arguments);
        }
    }
    
    @Override
    public Class<?> getResultClass() {
        return function.getReturnClass();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvokeExpressionImpl that = (InvokeExpressionImpl) o;
        return Objects.equals(function, that.function)
            && Objects.equals(arguments, that.arguments);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(function, arguments);
    }
}
