package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.*;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingContext;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingException;
import cn.codethink.xiaoming.expression.anlyzer.AnalyzingContextImpl;
import cn.codethink.xiaoming.expression.compiler.CompilingException;
import cn.codethink.xiaoming.expression.compiler.CompilingConfiguration;
import cn.codethink.xiaoming.expression.formatter.FormattingConfiguration;
import cn.codethink.xiaoming.expression.formatter.FormattingContextImpl;
import cn.codethink.xiaoming.expression.formatter.FormattingException;
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
    
    @Override
    public String format(Expression expression) throws FormattingException {
        Preconditions.checkNotNull(expression, "Expression is null!");
        
        return format(expression, FormattingConfiguration.getInstance());
    }
    
    @Override
    public String format(Expression expression, FormattingConfiguration configuration) throws FormattingException {
        Preconditions.checkNotNull(expression, "Expression is null!");
        Preconditions.checkNotNull(configuration, "Formatting configuration is null!");
    
        final FormattingContextImpl formattingContext = new FormattingContextImpl(expression, configuration, this);
        if (expression instanceof ConstantExpression) {
            final Object constant = ((ConstantExpression) expression).getConstant();
            if (constant == null) {
                return "null";
            }
            return expression.getType().getFormatter().format(constant, formattingContext);
        }
        if (expression instanceof ConstructExpression) {
            final ConstructExpression constructExpression = (ConstructExpression) expression;
    
            final List<Expression> arguments = constructExpression.getArguments();
            final String expressions;
            
            if (arguments.isEmpty()) {
                return constructExpression.getType().getName() +
                    (configuration.isInsertSpaceBeforeParenthesis() ? " " : "") + "(" +
                    (configuration.isInsertSpaceAfterParenthesis() || configuration.isInsertSpaceBeforeParenthesis() ? " " : "") +
                    (configuration.isInsertSpaceBeforeParenthesis() ? " " : "") + ")" + (configuration.isInsertSpaceAfterParenthesis() ? " " : "");
            }
            
            if (arguments.size() == 1) {
                expressions = format(arguments.get(0), configuration);
            } else {
                final String comma = (configuration.isInsertSpaceBeforeComma() ? " " : "") + "," + (configuration.isInsertSpaceAfterComma() ? " " : "");
                final StringBuilder stringBuilder = new StringBuilder(format(arguments.get(0), configuration));
                final int size = arguments.size();
                for (int i = 1; i < size; i++) {
                    stringBuilder.append(comma).append(format(arguments.get(i), configuration));
                }
                expressions = stringBuilder.toString();
            }
            
            return constructExpression.getType().getName() +
                (configuration.isInsertSpaceBeforeParenthesis() ? " " : "") + "(" + (configuration.isInsertSpaceAfterParenthesis() ? " " : "") +
                expressions +
                (configuration.isInsertSpaceBeforeParenthesis() ? " " : "") + ")" + (configuration.isInsertSpaceAfterParenthesis() ? " " : "");
        }
        if (expression instanceof ListExpression) {
            final List<Expression> arguments = ((ListExpression) expression).getExpressions();
            
            if (arguments.isEmpty()) {
                return (configuration.isInsertSpaceBeforeBrackets() ? " " : "") + "[" +
                    (configuration.isInsertSpaceAfterBrackets() || configuration.isInsertSpaceBeforeBrackets() ? " " : "") +
                    (configuration.isInsertSpaceBeforeBrackets() ? " " : "") + "]" + (configuration.isInsertSpaceAfterBrackets() ? " " : "");
            }
    
            final String expressions;
            if (arguments.size() == 1) {
                expressions = format(arguments.get(0), configuration);
            } else {
                final String comma = (configuration.isInsertSpaceBeforeComma() ? " " : "") + "," + (configuration.isInsertSpaceAfterComma() ? " " : "");
                final StringBuilder stringBuilder = new StringBuilder(format(arguments.get(0), configuration));
                final int size = arguments.size();
                for (int i = 1; i < size; i++) {
                    stringBuilder.append(comma).append(format(arguments.get(i), configuration));
                }
                expressions = stringBuilder.toString();
            }
    
            return (configuration.isInsertSpaceBeforeBrackets() ? " " : "") + "[" + (configuration.isInsertSpaceAfterBrackets() ? " " : "") +
                expressions +
                (configuration.isInsertSpaceBeforeBrackets() ? " " : "") + "]" + (configuration.isInsertSpaceAfterBrackets() ? " " : "");
        }
        if (expression instanceof SetExpression) {
            final List<Expression> arguments = ((SetExpression) expression).getExpressions();
        
            if (arguments.isEmpty()) {
                return (configuration.isInsertSpaceBeforeBraces() ? " " : "") + "{" +
                    (configuration.isInsertSpaceAfterBraces() || configuration.isInsertSpaceBeforeBraces() ? " " : "") +
                    (configuration.isInsertSpaceBeforeBraces() ? " " : "") + "}" + (configuration.isInsertSpaceAfterBraces() ? " " : "");
            }
        
            final String expressions;
            if (arguments.size() == 1) {
                expressions = format(arguments.get(0), configuration);
            } else {
                final String comma = (configuration.isInsertSpaceBeforeComma() ? " " : "") + "," + (configuration.isInsertSpaceAfterComma() ? " " : "");
                final StringBuilder stringBuilder = new StringBuilder(format(arguments.get(0), configuration));
                final int size = arguments.size();
                for (int i = 1; i < size; i++) {
                    stringBuilder.append(comma).append(format(arguments.get(i), configuration));
                }
                expressions = stringBuilder.toString();
            }
        
            return (configuration.isInsertSpaceBeforeBraces() ? " " : "") + "{" + (configuration.isInsertSpaceAfterBraces() ? " " : "") +
                expressions +
                (configuration.isInsertSpaceBeforeBraces() ? " " : "") + "}" + (configuration.isInsertSpaceAfterBraces() ? " " : "");
        }
        return null;
    }
}
