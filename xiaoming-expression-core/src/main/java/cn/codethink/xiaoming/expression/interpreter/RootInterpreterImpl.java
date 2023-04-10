package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.ConstantExpressionImpl;
import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.ListExpressionImpl;
import cn.codethink.xiaoming.expression.SetExpressionImpl;
import cn.codethink.xiaoming.expression.analyzer.Analyzer;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingContext;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingException;
import cn.codethink.xiaoming.expression.anlyzer.AnalyzingContextImpl;
import cn.codethink.xiaoming.expression.annotation.Analyser;
import cn.codethink.xiaoming.expression.type.Type;
import cn.codethink.xiaoming.expression.util.Types;

import java.util.*;

public class RootInterpreterImpl
    extends AbstractInterpreter {
    
    private static final RootInterpreterImpl INSTANCE = new RootInterpreterImpl();
    
    public static RootInterpreterImpl getInstance() {
        return INSTANCE;
    }
    
    private final Map<String, Type> types;
    
    @cn.codethink.xiaoming.expression.annotation.Type(value = Integer.class, name = "Int")
    private static class IntType {
        @Analyser
        public Expression analyze(Integer value, Type type) {
            return ConstantExpressionImpl.of(value, type);
        }
    }
    
    @cn.codethink.xiaoming.expression.annotation.Type(value = Double.class, name = "Double")
    private static class DoubleType {
        @Analyser
        public Expression analyze(Double value, Type type) {
            return ConstantExpressionImpl.of(value, type);
        }
    }
    
    @cn.codethink.xiaoming.expression.annotation.Type(value = Boolean.class, name = "Bool")
    private static class BoolType {
        @Analyser
        public Expression analyze(Boolean value, Type type) {
            return ConstantExpressionImpl.of(value, type);
        }
    }
    
    @cn.codethink.xiaoming.expression.annotation.Type(value = Character.class, name = "Char")
    private static class CharType {
        @Analyser
        public Expression analyze(Character value, Type type) {
            return ConstantExpressionImpl.of(value, type);
        }
    }
    
    @cn.codethink.xiaoming.expression.annotation.Type(value = String.class, name = "String")
    private static class StringType {
        @Analyser
        public Expression analyze(String value, Type type) {
            return ConstantExpressionImpl.of(value, type);
        }
    }
    
    @cn.codethink.xiaoming.expression.annotation.Type(value = void.class, name = "Nothing")
    private static class NothingType {
        @Analyser
        public Expression analyze() {
            return ConstantExpressionImpl.ofNull();
        }
    }
    
    @cn.codethink.xiaoming.expression.annotation.Type(value = List.class, name = "List")
    private static class ListType {
        @Analyser
        public Expression analyze(List<?> list, AnalyzingConfiguration configuration, Interpreter interpreter) {
            if (list.isEmpty()) {
                return new ListExpressionImpl(Collections.emptyList(), Type.LIST);
            } else {
                final List<Expression> expressions = new ArrayList<>(list.size());
                for (Object element : list) {
                    expressions.add(interpreter.analyze(new AnalyzingContextImpl(element, configuration, interpreter)));
                }
                return new ListExpressionImpl(expressions, Type.LIST);
            }
        }
    }
    
    @cn.codethink.xiaoming.expression.annotation.Type(value = Set.class, name = "Set")
    private static class SetType {
        @Analyser
        public Expression analyze(Set<?> set, AnalyzingConfiguration configuration, Interpreter interpreter) {
            if (set.isEmpty()) {
                return new SetExpressionImpl(Collections.emptyList(), Type.SET);
            } else {
                final List<Expression> expressions = new ArrayList<>(set.size());
                for (Object element : set) {
                    expressions.add(interpreter.analyze(new AnalyzingContextImpl(element, configuration, interpreter)));
                }
                return new SetExpressionImpl(expressions, Type.SET);
            }
        }
    }
    
    private RootInterpreterImpl() {
        super(null);
        
        final Map<String, Type> types = new HashMap<>();
    
        types.put("Int", Types.parseType(new IntType()));
        types.put("Double", Types.parseType(new DoubleType()));
        types.put("Bool", Types.parseType(new BoolType()));
        types.put("Char", Types.parseType(new CharType()));
        types.put("String", Types.parseType(new StringType()));
        types.put("Nothing", Types.parseType(new NothingType()));
        types.put("List", Types.parseType(new ListType()));
        types.put("Set", Types.parseType(new SetType()));
        
        this.types = Collections.unmodifiableMap(types);
    }
    
    @Override
    protected Type getType0(String name) {
        return types.get(name);
    }
    
    @Override
    protected Expression analyze0(AnalyzingContext context) throws AnalyzingException {
        final Object subject = context.getSubject();
        final Class<?> subjectClass = subject.getClass();
    
        for (Type type : types.values()) {
            if (type.getJavaClass().isAssignableFrom(subjectClass)) {
                for (Analyzer analyzer : type.getAnalysers()) {
                    final Expression expression = analyzer.analyze(context);
                    if (expression != null) {
                        return expression;
                    }
                }
            }
        }
        return null;
    }
}
