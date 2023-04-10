package cn.codethink.xiaoming.expression.type;

import com.google.common.base.Preconditions;

public class ParameterImpl
    implements Parameter {
    
    private final String name;
    private final Type type;
    
    public ParameterImpl(String name, Type type) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkNotNull(type, "Type is null!");
        
        this.name = name;
        this.type = type;
    }
    
    @Override
    public Type getType() {
        return type;
    }
    
    @Override
    public String getName() {
        return name;
    }
}
