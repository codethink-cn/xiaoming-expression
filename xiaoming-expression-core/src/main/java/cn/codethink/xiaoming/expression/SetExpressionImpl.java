package cn.codethink.xiaoming.expression;

import com.google.common.base.Preconditions;

import java.util.*;

public class SetExpressionImpl
    implements SetExpression {
    
    private final List<Expression> expressions;
    
    public SetExpressionImpl(List<Expression> expressions) {
        Preconditions.checkNotNull(expressions, "Expressions are null!");
        
        this.expressions = expressions;
    }
    
    @Override
    public List<Expression> getExpressions() {
        return expressions;
    }
    
    @Override
    public Set<Object> interpret() {
        if (expressions.isEmpty()) {
            return Collections.emptySet();
        }
        final Set<Object> set = new HashSet<>(expressions.size());
        for (Expression expression : expressions) {
            set.add(expression.interpret());
        }
        return Collections.unmodifiableSet(set);
    }
    
    @Override
    public Class<?> getResultClass() {
        return Set.class;
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
        return Objects.equals(expressions, that.expressions);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(expressions);
    }
}
