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
