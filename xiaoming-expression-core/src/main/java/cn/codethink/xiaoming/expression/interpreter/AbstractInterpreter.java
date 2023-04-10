package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.*;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingContext;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingException;
import cn.codethink.xiaoming.expression.anlyzer.AnalyzingContextImpl;
import cn.codethink.xiaoming.expression.compiler.CompilingException;
import cn.codethink.xiaoming.expression.compiler.CompilingConfiguration;
import cn.codethink.xiaoming.expression.type.Type;
import cn.codethink.xiaoming.expression.type.acl.Scanner;
import cn.codethink.xiaoming.expression.type.acl.parser;
import com.google.common.base.Preconditions;

import java.io.Reader;
import java.io.StringReader;
import java.util.*;

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
    public Expression compile(String expression) throws CompilingException {
        Preconditions.checkNotNull(expression, "Expression is null!");
        Preconditions.checkArgument(!expression.isEmpty(), "Expression is empty!");
        
        return compile(new StringReader(expression), CompilingConfiguration.getInstance());
    }
    
    @Override
    public Expression compile(String expression, CompilingConfiguration configuration) throws CompilingException {
        Preconditions.checkNotNull(expression, "Expression is null!");
        Preconditions.checkNotNull(configuration, "Configuration is null!");
        Preconditions.checkArgument(!expression.isEmpty(), "Expression is empty!");
        
        return compile(new StringReader(expression), CompilingConfiguration.getInstance());
    }
    
    @Override
    public Expression compile(Reader reader) throws CompilingException {
        Preconditions.checkNotNull(reader, "Reader is null!");
    
        return compile(reader, CompilingConfiguration.getInstance());
    }
    
    @Override
    @SuppressWarnings("all")
    public Expression compile(Reader reader, CompilingConfiguration configuration) throws CompilingException {
        Preconditions.checkNotNull(reader, "Reader is null!");
        Preconditions.checkNotNull(configuration, "Configuration is null!");
    
        try {
            final Scanner scanner = new Scanner(reader);
            final parser parser = new parser(scanner);
            parser.initialize(this, configuration);
        
            return (Expression) parser.parse().value;
        } catch (Exception e) {
            throw new CompilingException(e.getMessage());
        }
    }
    
    @Override
    public Expression analyze(Object subject) throws AnalyzingException {
        return analyze(subject, AnalyzingConfiguration.getInstance());
    }
    
    @Override
    public Expression analyze(Object subject, AnalyzingConfiguration configuration) throws AnalyzingException {
        if (subject == null) {
            return ConstantExpressionImpl.ofNull();
        }
        final AnalyzingContext context = new AnalyzingContextImpl(subject, configuration, this);
        if (parent == null) {
            return analyze0(context);
        } else {
            Expression expression = parent.analyze(context);
            if (expression == null) {
                expression = analyze0(context);
            }
            return expression;
        }
    }
    
    protected abstract Expression analyze0(AnalyzingContext context) throws AnalyzingException;
}
