package cn.codethink.xiaoming.expression;

import cn.codethink.xiaoming.expression.type.Type;
import com.google.common.base.Preconditions;

import java.util.Objects;

public class ConstantExpressionImpl
    implements ConstantExpression {
    
    private static final ConstantExpressionImpl NULL = new ConstantExpressionImpl(null, Type.NOTHING);
    
    public static ConstantExpression of(Object value, Type type) {
        Preconditions.checkNotNull(value, "Value is null!");
        Preconditions.checkNotNull(type, "Type is null!");
    
        final Class<?> javaClass = type.getJavaClass();
        if (!javaClass.isAssignableFrom(value.getClass())) {
            throw new IllegalArgumentException("Value is not an instance of " + javaClass.getName());
        }
        
        return new ConstantExpressionImpl(value, type);
    }
    
    public static ConstantExpressionImpl ofNull() {
        return NULL;
    }
    
    private final Object value;
    private final Type type;
    
    private ConstantExpressionImpl(Object value, Type type) {
        this.type = type;
        this.value = value;
    }
    
    @Override
    public Object getConstant() {
        return value;
    }
    
    @Override
    public Object calculate() {
        return value;
    }
    
    @Override
    public Type getType() {
        return type;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConstantExpressionImpl that = (ConstantExpressionImpl) o;
        return Objects.equals(value, that.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    
    @Override
    public String toString() {
        return Objects.toString(value);
    }
}
