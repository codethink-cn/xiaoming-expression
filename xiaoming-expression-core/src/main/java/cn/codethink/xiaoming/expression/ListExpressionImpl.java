package cn.codethink.xiaoming.expression;

import cn.codethink.xiaoming.expression.lang.Type;
import com.google.common.base.Preconditions;

import java.util.*;

public class ListExpressionImpl
    implements ListExpression {
    
    private final List<Expression> expressions;
    private final Type type;
    
    public ListExpressionImpl(List<Expression> expressions, Type type) {
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
    public List<Object> calculate() throws CalculateException {
        if (expressions.isEmpty()) {
            return Collections.emptyList();
        }
        final List<Object> set = new ArrayList<>(expressions.size());
        for (Expression expression : expressions) {
            set.add(expression.calculate());
        }
        return Collections.unmodifiableList(set);
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
        ListExpressionImpl that = (ListExpressionImpl) o;
        return Objects.equals(expressions, that.expressions) && Objects.equals(type, that.type);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(expressions, type);
    }
}
