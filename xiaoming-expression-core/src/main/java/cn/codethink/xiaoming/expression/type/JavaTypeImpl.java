package cn.codethink.xiaoming.expression.type;

import cn.codethink.xiaoming.expression.analyzer.Analyzer;
import cn.codethink.xiaoming.expression.constructor.Constructor;
import com.google.common.base.Preconditions;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class JavaTypeImpl
    implements Type {
    
    private final String name;
    private final Class<?> javaClass;
    private final Set<Constructor> constructors;
    private Set<Analyzer> analyzers;
    
    public JavaTypeImpl(String name, Class<?> javaClass) {
        this(name, javaClass, Collections.emptySet(), Collections.emptySet());
    }
    
    public JavaTypeImpl(Class<?> javaClass) {
        this(javaClass.getSimpleName(), javaClass, Collections.emptySet(), Collections.emptySet());
    }
    
    public JavaTypeImpl(String name, Class<?> javaClass, Set<Constructor> constructors, Set<Analyzer> analyzers) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkNotNull(analyzers, "Analysers are null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
        Preconditions.checkNotNull(javaClass, "Java class is null!");
        Preconditions.checkNotNull(constructors, "Constructors are null!");
        
        this.name = name;
        this.javaClass = javaClass;
        this.constructors = Collections.unmodifiableSet(constructors);
        this.analyzers = Collections.unmodifiableSet(analyzers);
    }
    
    @Override
    public Set<Constructor> getConstructors() {
        return constructors;
    }
    
    @Override
    public Constructor getConstructor(List<Type> types) {
        Preconditions.checkNotNull(types, "Types are null!");
        
        for (Constructor constructor : constructors) {
            final List<Parameter> parameters = constructor.getParameters();
            if (types.size() != parameters.size()) {
                continue;
            }
    
            boolean matches = true;
            for (int i = 0; i < parameters.size(); i++) {
                if (!parameters.get(i).getType().isAssignableFrom(types.get(i))) {
                    matches = false;
                    break;
                }
            }
            
            if (matches) {
                return constructor;
            }
        }
        return null;
    }
    
    @Override
    public Constructor getConstructorOrFail(List<Type> types) {
        Preconditions.checkNotNull(types, "Types are null!");
    
        final Constructor constructor = getConstructor(types);
        if (constructor == null) {
            throw new NoSuchElementException("No such constructor with parameters types: " + types + " in type: " + name);
        }
        return constructor;
    }
    
    @Override
    public Class<?> getJavaClass() {
        return javaClass;
    }
    
    @Override
    public boolean isAssignableFrom(Type type) {
        Preconditions.checkNotNull(type, "Type is null!");
        return javaClass.isAssignableFrom(type.getJavaClass());
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public Set<Analyzer> getAnalysers() {
        return analyzers;
    }
}
