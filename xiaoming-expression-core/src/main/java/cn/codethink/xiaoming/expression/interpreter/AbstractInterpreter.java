package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.*;
import cn.codethink.xiaoming.expression.acl.Scanner;
import cn.codethink.xiaoming.expression.acl.parser;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingContext;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingException;
import cn.codethink.xiaoming.expression.anlyzer.AnalyzingContextImpl;
import cn.codethink.xiaoming.expression.compiler.CompilingConfiguration;
import cn.codethink.xiaoming.expression.compiler.CompilingException;
import cn.codethink.xiaoming.expression.formatter.FormattingConfiguration;
import cn.codethink.xiaoming.expression.formatter.FormattingException;
import cn.codethink.xiaoming.expression.type.Type;
import com.google.common.base.Preconditions;
import org.apache.commons.text.StringEscapeUtils;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
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
            throw new CompilingException(e);
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
    
    /**
     * 格式串中的单元，前后可能存在若干数量的空格
     */
    private interface Item {
        static Item parse(String text) {
            final int length = text.length();
    
            int countOfSpacesBeforeIt = 0;
            while (countOfSpacesBeforeIt < length && text.charAt(countOfSpacesBeforeIt) == ' ') {
                countOfSpacesBeforeIt++;
            }
            
            if (countOfSpacesBeforeIt == length) {
                return new SpaceItem(length);
            }
    
            int countOfSpacesAfterIt = 0;
            while (countOfSpacesAfterIt >= 0 && text.charAt(length - countOfSpacesAfterIt - 1) == ' ') {
                countOfSpacesAfterIt--;
            }
    
            return new TextItem(countOfSpacesBeforeIt, text.substring(countOfSpacesBeforeIt, length - countOfSpacesAfterIt), countOfSpacesAfterIt);
        }
    
        static String toString(List<Item> items, boolean minimize) {
            final StringBuilder stringBuilder = new StringBuilder();
        
            if (minimize) {
                int count = 0;
                for (Item item : items) {
                    if (item instanceof SpaceItem) {
                        count = Math.max(count, ((SpaceItem) item).count);
                    } else {
                        final TextItem textItem = (TextItem) item;
                        count = Math.max(count, textItem.countOfSpacesBeforeContent);
                        for (int i = 0; i < count; i++) {
                            stringBuilder.append(' ');
                        }
                        count = textItem.countOfSpacesAfterContent;
                    }
                }
                for (int i = 0; i < count; i++) {
                    stringBuilder.append(' ');
                }
            } else {
                for (Item item : items) {
                    if (item instanceof SpaceItem) {
                        final int count = ((SpaceItem) item).count;
                        for (int i = 0; i < count; i++) {
                            stringBuilder.append(' ');
                        }
                    } else {
                        final TextItem textItem = (TextItem) item;
                        for (int i = 0; i < textItem.countOfSpacesBeforeContent; i++) {
                            stringBuilder.append(' ');
                        }
                        stringBuilder.append(textItem.content);
                        for (int i = 0; i < textItem.countOfSpacesAfterContent; i++) {
                            stringBuilder.append(' ');
                        }
                    }
                }
            }
        
            return stringBuilder.toString();
        }
    }
    
    private static class TextItem
        implements Item {
        
        private final int countOfSpacesBeforeContent;
        private final int countOfSpacesAfterContent;
        private final String content;
    
        public TextItem(int countOfSpacesBeforeContent, String content, int countOfSpacesAfterContent) {
            Preconditions.checkArgument(countOfSpacesBeforeContent >= 0, "Count of spaces before content must be greater than or equals to 0!");
            Preconditions.checkArgument(countOfSpacesAfterContent >= 0, "Count of spaces after content must be greater than or equals to 0!");
            
            this.countOfSpacesBeforeContent = countOfSpacesBeforeContent;
            this.content = content;
            this.countOfSpacesAfterContent = countOfSpacesAfterContent;
        }
    
        public TextItem(String content) {
            this(0, content, 0);
        }
    
        public TextItem(String content, int countOfSpacesAfterContent) {
            this(0, content, countOfSpacesAfterContent);
        }
    
        public TextItem(int countOfSpacesBeforeContent, String content) {
            this(countOfSpacesBeforeContent, content, 0);
        }
    }
    
    private static class SpaceItem
        implements Item {
    
        private final int count;
    
        public SpaceItem(int count) {
            Preconditions.checkArgument(count >= 0, "Count of spaces must be greater than or equals to 0!");
            this.count = count;
        }
    }
    
    @Override
    public String format(Expression expression, FormattingConfiguration configuration) throws FormattingException {
        Preconditions.checkNotNull(expression, "Expression is null!");
        Preconditions.checkNotNull(configuration, "Formatting configuration is null!");
    
        // 格式化常量类型
        if (expression instanceof ConstantExpression) {
            final Object constant = ((ConstantExpression) expression).getConstant();
            if (constant == null) {
                return "null";
            }
            
            if (constant instanceof Integer) {
                return constant.toString();
            }
            if (constant instanceof Double) {
                return constant.toString();
            }
            if (constant instanceof Boolean) {
                return constant.toString();
            }
            if (constant instanceof Character) {
                return "'" + StringEscapeUtils.escapeJava(String.valueOf(constant)) + "'";
            }
            if (constant instanceof String) {
                return "\"" + StringEscapeUtils.escapeJava((String) constant) + "\"";
            }
            
            throw new IllegalArgumentException("Unexpected value in ConstantExpression: " + constant);
        }
        
        // 格式化元素列表
        final List<Item> items = new ArrayList<>();
        final Item comma = new TextItem(configuration.getCountOfSpacesBeforeComma(), ",", configuration.getCountOfSpacesAfterComma());
        
        if (expression instanceof ConstructExpression) {
            final Item leftParenthesis = new TextItem(configuration.getCountOfSpacesBeforeLeftParenthesis(),
                "(", configuration.getCountOfSpacesAfterLeftParenthesis());
            final Item rightParenthesis = new TextItem(configuration.getCountOfSpacesBeforeRightParenthesis(),
                ")", configuration.getCountOfSpacesAfterRightParenthesis());
            
            final ConstructExpression constructExpression = (ConstructExpression) expression;
            final List<Expression> arguments = constructExpression.getArguments();
            
            items.add(new TextItem(constructExpression.getType().getName()));
            items.add(leftParenthesis);
            
            if (arguments.isEmpty()) {
                items.add(new SpaceItem(configuration.getCountOfSpacesInEmptyBraces()));
            } else {
                final int size = arguments.size();
                if (size == 1) {
                    items.add(Item.parse(format(arguments.get(0), configuration)));
                } else {
                    items.add(Item.parse(format(arguments.get(0), configuration)));
                    for (int i = 1; i < size; i++) {
                        items.add(comma);
                        items.add(Item.parse(format(arguments.get(i), configuration)));
                    }
                }
            }
            
            items.add(rightParenthesis);
            return Item.toString(items, configuration.isMinimizeSpaces());
        }
        if (expression instanceof ListExpression) {
            final Item leftBracket = new TextItem(configuration.getCountOfSpacesBeforeLeftBrackets(),
                "[", configuration.getCountOfSpacesAfterLeftBrackets());
            final Item rightBracket = new TextItem(configuration.getCountOfSpacesBeforeRightBrackets(),
                "]", configuration.getCountOfSpacesAfterRightBrackets());
            
            final List<Expression> arguments = ((ListExpression) expression).getExpressions();
    
            items.add(leftBracket);
    
            if (arguments.isEmpty()) {
                items.add(new SpaceItem(configuration.getCountOfSpacesInEmptyBrackets()));
            } else {
                final int size = arguments.size();
                if (size == 1) {
                    items.add(Item.parse(format(arguments.get(0), configuration)));
                } else {
                    items.add(Item.parse(format(arguments.get(0), configuration)));
                    for (int i = 1; i < size; i++) {
                        items.add(comma);
                        items.add(Item.parse(format(arguments.get(i), configuration)));
                    }
                }
            }
    
            items.add(rightBracket);
            return Item.toString(items, configuration.isMinimizeSpaces());
        }
        if (expression instanceof SetExpression) {
            final Item leftBrace = new TextItem(configuration.getCountOfSpacesBeforeLeftBraces(),
                "{", configuration.getCountOfSpacesAfterLeftBraces());
            final Item rightBrace = new TextItem(configuration.getCountOfSpacesBeforeRightBraces(),
                "}", configuration.getCountOfSpacesAfterRightBraces());
    
            final List<Expression> arguments = ((SetExpression) expression).getExpressions();
    
            items.add(leftBrace);
    
            if (arguments.isEmpty()) {
                items.add(new SpaceItem(configuration.getCountOfSpacesInEmptyBraces()));
            } else {
                final int size = arguments.size();
                if (size == 1) {
                    items.add(Item.parse(format(arguments.get(0), configuration)));
                } else {
                    items.add(Item.parse(format(arguments.get(0), configuration)));
                    for (int i = 1; i < size; i++) {
                        items.add(comma);
                        items.add(Item.parse(format(arguments.get(i), configuration)));
                    }
                }
            }
    
            items.add(rightBrace);
            return Item.toString(items, configuration.isMinimizeSpaces());
        }
        return null;
    }
}