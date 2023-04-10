package cn.codethink.xiaoming.expression.lang;

import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class NothingTypeImpl
    implements Type {
    
    private static final NothingTypeImpl INSTANCE = new NothingTypeImpl();
    
    public static NothingTypeImpl getInstance() {
        return INSTANCE;
    }
    
    private NothingTypeImpl() {
    }
    
    @Override
    public Set<Constructor> getConstructors() {
        return Collections.emptySet();
    }
    
    @Override
    public Constructor getConstructor(List<Type> types) {
        Preconditions.checkNotNull(types, "Types are null!");
        return null;
    }
    
    @Override
    public Constructor getConstructorOrFail(List<Type> types) {
        Preconditions.checkNotNull(types, "Types are null!");
        throw new NoSuchElementException("No such constructor with parameters types: " + types + " in type: Nothing");
    }
    
    @Override
    public Class<?> getJavaClass() {
        return Void.class;
    }
    
    @Override
    public boolean isAssignableFrom(Type type) {
        return true;
    }
    
    @Override
    public String getName() {
        return "Nothing";
    }
}
