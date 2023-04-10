package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.lang.JavaTypeImpl;
import cn.codethink.xiaoming.expression.lang.Type;
import cn.codethink.xiaoming.expression.lang.acl.Scanner;
import cn.codethink.xiaoming.expression.lang.acl.parser;
import com.google.common.base.Preconditions;

import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

public class InterpreterImpl
    implements Interpreter {
    
    private static InterpreterImpl INSTANCE = new InterpreterImpl(null);
    static {
        // Original types
        final Type intType = new JavaTypeImpl("Int", Integer.class, Collections.emptySet());
        final Type doubleType = new JavaTypeImpl("Double", Double.class, Collections.emptySet());
        final Type charType = new JavaTypeImpl("Char", Character.class, Collections.emptySet());
        final Type stringType = new JavaTypeImpl("String", String.class, Collections.emptySet());
        final Type nothingType = new JavaTypeImpl("Nothing", Void.class, Collections.emptySet());
        
        INSTANCE.registerType(intType);
        INSTANCE.registerType(doubleType);
        INSTANCE.registerType(charType);
        INSTANCE.registerType(stringType);
        INSTANCE.registerType(nothingType);
    }
    
    private final Interpreter parent;
    
    private final Map<String, Type> types = new ConcurrentHashMap<>();
    
    private InterpreterImpl(Interpreter parent) {
        this.parent = parent;
    }
    
    public static InterpreterImpl newInstance(Interpreter parent) {
        Preconditions.checkNotNull(parent, "Parent interpreter is null!");
        return new InterpreterImpl(parent);
    }
    
    public static InterpreterImpl getInstance() {
        return INSTANCE;
    }
    
    @Override
    public Interpreter getParentInterpreter() {
        return parent;
    }
    
    @Override
    public Type getType(String name) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
    
        if (parent == null) {
            return types.get(name);
        } else {
            Type type = parent.getType(name);
            if (type == null) {
                type = types.get(name);
            }
            return type;
        }
    }
    
    @Override
    public Type getTypeOrFail(String name) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
    
        final Type type = getType(name);
        if (type == null) {
            throw new NoSuchElementException("No such type: " + name);
        }
        return type;
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
    
    @Override
    public Expression compile(String expression) throws CompileException {
        Preconditions.checkNotNull(expression, "Expression is null!");
        Preconditions.checkArgument(!expression.isEmpty(), "Expression is empty!");
        
        return compile(new StringReader(expression), CompilationConfiguration.getInstance());
    }
    
    @Override
    public Expression compile(String expression, CompilationConfiguration configuration) throws CompileException {
        Preconditions.checkNotNull(expression, "Expression is null!");
        Preconditions.checkNotNull(configuration, "Configuration is null!");
        Preconditions.checkArgument(!expression.isEmpty(), "Expression is empty!");
        
        return compile(new StringReader(expression), CompilationConfiguration.getInstance());
    }
    
    @Override
    @SuppressWarnings("all")
    public Expression compile(Reader reader) throws CompileException {
        Preconditions.checkNotNull(reader, "Reader is null!");
    
        return compile(reader, CompilationConfiguration.getInstance());
    }
    
    @Override
    public Expression compile(Reader reader, CompilationConfiguration configuration) throws CompileException {
        Preconditions.checkNotNull(reader, "Reader is null!");
        Preconditions.checkNotNull(configuration, "Configuration is null!");
    
        try {
            final Scanner scanner = new Scanner(reader);
            final parser parser = new parser(scanner);
            parser.initialize(this, configuration);
        
            return (Expression) parser.parse().value;
        } catch (Exception e) {
            throw new CompileException(e.getMessage());
        }
    }
}
