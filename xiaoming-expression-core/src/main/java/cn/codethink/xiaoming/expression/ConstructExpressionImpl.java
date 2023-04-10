package cn.codethink.xiaoming.expression;

import cn.codethink.xiaoming.expression.constructor.ConstructingException;
import cn.codethink.xiaoming.expression.constructor.Constructor;
import cn.codethink.xiaoming.expression.type.Type;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ConstructExpressionImpl
    implements ConstructExpression {
    
    private final Constructor constructor;
    private final List<Expression> arguments;
    
    public ConstructExpressionImpl(Constructor constructor, List<Expression> arguments) {
        Preconditions.checkNotNull(constructor, "Constructor is null!");
        Preconditions.checkNotNull(arguments, "Arguments expressions are null!");
        
        this.constructor = constructor;
        this.arguments = arguments;
    }
    
    @Override
    public Constructor getConstructor() {
        return constructor;
    }
    
    @Override
    public List<Expression> getArguments() {
        return arguments;
    }
    
    @Override
    public Object calculate() throws CalculateException {
        try {
            if (arguments.isEmpty()) {
                return constructor.construct(Collections.emptyList());
            } else {
                final List<Object> arguments = new ArrayList<>(this.arguments.size());
                for (Expression parameterExpression : this.arguments) {
                    final Object argument = parameterExpression.calculate();
                    arguments.add(argument);
                }
                return constructor.construct(arguments);
            }
        } catch (ConstructingException e) {
            throw new CalculateException("Exception thrown while constructing", e);
        }
    }
    
    @Override
    public Type getType() {
        return constructor.getType();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConstructExpressionImpl that = (ConstructExpressionImpl) o;
        return Objects.equals(constructor, that.constructor)
            && Objects.equals(arguments, that.arguments);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(constructor, arguments);
    }
}
