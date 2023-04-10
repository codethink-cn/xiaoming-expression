package cn.codethink.xiaoming.expression.type;

import com.google.common.base.Preconditions;

public class ParameterImpl
    implements Parameter {
    
    private final String name;
    private final Class<?> javaClass;
    
    public ParameterImpl(String name, Class<?> javaClass) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkNotNull(javaClass, "Java class is null!");
        
        this.name = name;
        this.javaClass = javaClass;
    }
    
    @Override
    public Class<?> getJavaClass() {
        return javaClass;
    }
    
    @Override
    public String getName() {
        return name;
    }
}
