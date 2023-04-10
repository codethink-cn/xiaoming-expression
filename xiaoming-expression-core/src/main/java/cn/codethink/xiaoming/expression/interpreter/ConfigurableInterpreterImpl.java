package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.lang.Constructor;
import cn.codethink.xiaoming.expression.lang.JavaTypeImpl;
import cn.codethink.xiaoming.expression.lang.MethodConstructorImpl;
import cn.codethink.xiaoming.expression.lang.Type;
import com.google.common.base.Preconditions;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigurableInterpreterImpl
    extends AbstractInterpreter
    implements ConfigurableInterpreter {
    
    private final Map<String, Type> types = new ConcurrentHashMap<>();
    
    public ConfigurableInterpreterImpl(Interpreter parent) {
        super(parent);
        
        Preconditions.checkNotNull(parent, "Parent interpreter is null!");
    }
    
    @Override
    public void registerType(Object object) {
        Preconditions.checkNotNull(object, "Object is null!");
    
        final Class<?> objectClass = object.getClass();
        final cn.codethink.xiaoming.expression.annotation.Type typeAnnotation =
            objectClass.getAnnotation(cn.codethink.xiaoming.expression.annotation.Type.class);
        
        if (typeAnnotation == null) {
            throw new IllegalArgumentException("Class of object " + objectClass.getName() + " hasn't annotation @Type!");
        }
    
        final Method[] declaredMethods = objectClass.getDeclaredMethods();
        final Class<?> javaClass = typeAnnotation.value() == Void.class ? objectClass : typeAnnotation.value();
        final String name = typeAnnotation.name().isEmpty() ? javaClass.getSimpleName() : typeAnnotation.name();
    
        final Set<Constructor> constructors = new HashSet<>();
        final Type type = new JavaTypeImpl(name, javaClass, constructors);
        for (Method declaredMethod : declaredMethods) {
            final cn.codethink.xiaoming.expression.annotation.Constructor constructorAnnotation =
                declaredMethod.getAnnotation(cn.codethink.xiaoming.expression.annotation.Constructor.class);
            if (constructorAnnotation == null) {
                continue;
            }
    
            if (!javaClass.isAssignableFrom(declaredMethod.getReturnType())) {
                throw new IllegalArgumentException("Illegal return type of method: " + declaredMethod + ", expect: " + javaClass.getName());
            }
            
            constructors.add(new MethodConstructorImpl(type, object, declaredMethod));
        }
        
        registerType(type);
    }
    
    @Override
    protected Type getType0(String name) {
        return types.get(name);
    }
    
    @Override
    public void registerType(Type type) {
        Preconditions.checkNotNull(type, "Type is null!");
        
        final Type sameNameType = getType(type.getName());
        if (sameNameType != null) {
            throw new IllegalArgumentException("Type " + type.getName() + " already exists!");
        }
        types.put(type.getName(), type);
    }
}
