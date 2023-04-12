package cn.codethink.xiaoming.expression;

import com.google.common.base.Preconditions;

import java.util.*;

public class ListExpressionImpl
    implements ListExpression {
    
    private final List<Expression> expressions;
    
    public ListExpressionImpl(List<Expression> expressions) {
        Preconditions.checkNotNull(expressions, "Expressions are null!");
        
        this.expressions = expressions;
    }
    
    @Override
    public List<Expression> getExpressions() {
        return expressions;
    }
    
    @Override
    public List<Object> interpret() {
        if (expressions.isEmpty()) {
            return Collections.emptyList();
        }
        final List<Object> set = new ArrayList<>(expressions.size());
        for (Expression expression : expressions) {
            set.add(expression.interpret());
        }
        return Collections.unmodifiableList(set);
    }
    
    @Override
    public Class<?> getResultClass() {
        return List.class;
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
        ListExpressionImpl that = (ListExpressionImpl) o;
        return Objects.equals(expressions, that.expressions);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(expressions);
    }
}
