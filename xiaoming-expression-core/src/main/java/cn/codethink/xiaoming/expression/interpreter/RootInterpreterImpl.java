package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.lang.JavaTypeImpl;
import cn.codethink.xiaoming.expression.lang.Type;

import java.util.*;

public class RootInterpreterImpl
    extends AbstractInterpreter {
    
    private static final RootInterpreterImpl INSTANCE = new RootInterpreterImpl(null);
    
    public static RootInterpreterImpl getInstance() {
        return INSTANCE;
    }
    
    private final Map<String, Type> types;
    
    private RootInterpreterImpl(Interpreter parent) {
        super(parent);
        
        final Map<String, Type> types = new HashMap<>();
    
        final Type intType = new JavaTypeImpl("Int", Integer.class, Collections.emptySet());
        final Type doubleType = new JavaTypeImpl("Double", Double.class, Collections.emptySet());
        final Type charType = new JavaTypeImpl("Char", Character.class, Collections.emptySet());
        final Type stringType = new JavaTypeImpl("String", String.class, Collections.emptySet());
        final Type nothingType = new JavaTypeImpl("Nothing", Void.class, Collections.emptySet());
        final Type listType = new JavaTypeImpl("List", List.class, Collections.emptySet());
        final Type setType = new JavaTypeImpl("Set", Set.class, Collections.emptySet());
    
        types.put(intType.getName(), intType);
        types.put(doubleType.getName(), doubleType);
        types.put(charType.getName(), charType);
        types.put(stringType.getName(), stringType);
        types.put(nothingType.getName(), nothingType);
        types.put(listType.getName(), listType);
        types.put(setType.getName(), setType);
        
        this.types = Collections.unmodifiableMap(types);
    }
    
    @Override
    protected Type getType0(String name) {
        return types.get(name);
    }
}
