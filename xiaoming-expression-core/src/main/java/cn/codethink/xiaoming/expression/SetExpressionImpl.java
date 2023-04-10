package cn.codethink.xiaoming.expression;

import cn.codethink.xiaoming.expression.type.Type;
import com.google.common.base.Preconditions;

import java.util.*;

public class SetExpressionImpl
    implements SetExpression {
    
    private final List<Expression> expressions;
    private final Type type;
    
    public SetExpressionImpl(List<Expression> expressions, Type type) {
        Preconditions.checkNotNull(expressions, "Expressions are null!");
        Preconditions.checkNotNull(type, "Type is null!");
        
        this.expressions = expressions;
        this.type = type;
    }
    
    @Override
    public List<Expression> getExpressions() {
        return expressions;
    }
    
    @Override
    public Set<Object> calculate() throws CalculateException {
        if (expressions.isEmpty()) {
            return Collections.emptySet();
        }
        final Set<Object> set = new HashSet<>(expressions.size());
        for (Expression expression : expressions) {
            set.add(expression.calculate());
        }
        return Collections.unmodifiableSet(set);
    }
    
    @Override
    public Type getType() {
        return type;
    }
    
    @Override
    public Iterator<Expression> iterator() {
        return expressions.iterator();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SetExpressionImpl that = (SetExpressionImpl) o;
        return Objects.equals(expressions, that.expressions) && Objects.equals(type, that.type);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(expressions, type);
    }
}
