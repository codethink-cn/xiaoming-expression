package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.ConstantExpressionImpl;
import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.ListExpressionImpl;
import cn.codethink.xiaoming.expression.SetExpressionImpl;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingContext;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingException;
import cn.codethink.xiaoming.expression.anlyzer.AnalyzingConfigurationImpl;
import cn.codethink.xiaoming.expression.anlyzer.AnalyzingContextImpl;
import cn.codethink.xiaoming.expression.type.JavaTypeImpl;
import cn.codethink.xiaoming.expression.type.Type;

import java.util.*;

public class RootInterpreterImpl
    extends AbstractInterpreter {
    
    private static final RootInterpreterImpl INSTANCE = new RootInterpreterImpl();
    
    public static RootInterpreterImpl getInstance() {
        return INSTANCE;
    }
    
    private final Map<String, Type> types;
    
    private RootInterpreterImpl() {
        super(null);
        
        final Map<String, Type> types = new HashMap<>();
    
        final Type intType = new JavaTypeImpl("Int", Integer.class);
        final Type doubleType = new JavaTypeImpl("Double", Double.class);
        final Type boolType = new JavaTypeImpl("Bool", Boolean.class);
        final Type charType = new JavaTypeImpl("Char", Character.class);
        final Type stringType = new JavaTypeImpl("String", String.class);
        final Type nothingType = new JavaTypeImpl("Nothing", Void.class);
        final Type listType = new JavaTypeImpl("List", List.class);
        final Type setType = new JavaTypeImpl("Set", Set.class);
    
        types.put(intType.getName(), intType);
        types.put(doubleType.getName(), doubleType);
        types.put(boolType.getName(), boolType);
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
    
    @Override
    protected Expression analyze0(AnalyzingContext context) throws AnalyzingException {
        final Object subject = context.getSubject();
    
        if (subject instanceof Integer) {
            return ConstantExpressionImpl.of(subject, Type.INT);
        }
        if (subject instanceof Double) {
            return ConstantExpressionImpl.of(subject, Type.DOUBLE);
        }
        if (subject instanceof Boolean) {
            return ConstantExpressionImpl.of(subject, Type.BOOL);
        }
        if (subject instanceof Character) {
            return ConstantExpressionImpl.of(subject, Type.CHAR);
        }
        if (subject instanceof String) {
            return ConstantExpressionImpl.of(subject, Type.STRING);
        }
    
        if (subject instanceof List) {
            final List<?> list = (List<?>) subject;
            if (list.isEmpty()) {
                return new ListExpressionImpl(Collections.emptyList(), Type.LIST);
            } else {
                final List<Expression> expressions = new ArrayList<>(list.size());
                for (Object element : list) {
                    expressions.add(context.getInterpreter().analyze(new AnalyzingContextImpl(element, context.getConfiguration(), context.getInterpreter())));
                }
                return new ListExpressionImpl(expressions, Type.LIST);
            }
        }
        if (subject instanceof Set) {
            final Set<?> set = (Set<?>) subject;
            if (set.isEmpty()) {
                return new SetExpressionImpl(Collections.emptyList(), Type.SET);
            } else {
                final List<Expression> expressions = new ArrayList<>(set.size());
                for (Object element : set) {
                    expressions.add(context.getInterpreter().analyze(new AnalyzingContextImpl(element, context.getConfiguration(), context.getInterpreter())));
                }
                return new SetExpressionImpl(expressions, Type.SET);
            }
        }
    
        return null;
    }
}
