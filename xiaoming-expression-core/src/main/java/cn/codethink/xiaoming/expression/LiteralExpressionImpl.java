package cn.codethink.xiaoming.expression;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LiteralExpressionImpl
    implements LiteralExpression {
    
    private static final Set<Class<?>> LEGAL_CLASSES = new HashSet<>(Arrays.asList(
        Integer.class, Double.class, Character.class, Boolean.class, String.class
    ));
    
    private static final LiteralExpressionImpl NULL = new LiteralExpressionImpl(null, null);
    
    public static LiteralExpression of(Object value) {
        Preconditions.checkNotNull(value, "Value is null!");
    
        final Class<?> javaClass = value.getClass();
        for (Class<?> legalClass : LEGAL_CLASSES) {
            if (legalClass.isAssignableFrom(javaClass)) {
                return new LiteralExpressionImpl(value, legalClass);
            }
        }
        
        throw new IllegalArgumentException("Unexpected value: " + value + ", expect an instance of " + LEGAL_CLASSES);
    }
    
    public static LiteralExpressionImpl ofNull() {
        return NULL;
    }
    
    private final Object value;
    private final Class<?> resultClass;
    
    private LiteralExpressionImpl(Object value, Class<?> resultClass) {
        this.value = value;
        this.resultClass = resultClass;
    }
    
    @Override
    public Object getValue() {
        return value;
    }
    
    @Override
    public Object interpret() {
        return value;
    }
    
    @Override
    public Class<?> getResultClass() {
        return resultClass;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LiteralExpressionImpl that = (LiteralExpressionImpl) o;
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
