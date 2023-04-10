package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.lang.Type;
import cn.codethink.xiaoming.expression.lang.acl.Scanner;
import cn.codethink.xiaoming.expression.lang.acl.parser;
import com.google.common.base.Preconditions;

import java.io.Reader;
import java.io.StringReader;
import java.util.NoSuchElementException;

public abstract class AbstractInterpreter
    implements Interpreter {
    
    protected final Interpreter parent;
    
    protected AbstractInterpreter(Interpreter parent) {
        this.parent = parent;
    }
    
    @Override
    public Interpreter getParent() {
        return parent;
    }
    
    @Override
    public final Type getType(String name) {
        Preconditions.checkNotNull(name, "Name is null!");
        Preconditions.checkArgument(!name.isEmpty(), "Name is empty!");
    
        if (parent == null) {
            return getType0(name);
        } else {
            Type type = parent.getType(name);
            if (type == null) {
                type = getType0(name);
            }
            return type;
        }
    }
    
    protected abstract Type getType0(String name);
    
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
    public Expression compile(Reader reader) throws CompileException {
        Preconditions.checkNotNull(reader, "Reader is null!");
    
        return compile(reader, CompilationConfiguration.getInstance());
    }
    
    @Override
    @SuppressWarnings("all")
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
