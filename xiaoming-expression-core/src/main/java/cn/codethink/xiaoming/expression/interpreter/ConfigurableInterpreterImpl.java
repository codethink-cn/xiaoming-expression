package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.ConstantExpressionImpl;
import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.analyzer.Analyzer;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingContext;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingException;
import cn.codethink.xiaoming.expression.anlyzer.MethodAnalyzerImpl;
import cn.codethink.xiaoming.expression.constructor.Constructor;
import cn.codethink.xiaoming.expression.constructor.MethodConstructorImpl;
import cn.codethink.xiaoming.expression.type.*;
import cn.codethink.xiaoming.expression.util.Types;
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
    public void registerType(Object subject) {
        Preconditions.checkNotNull(subject, "Subject is null!");
        registerType(Types.parseType(subject));
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
    public void registerType(Object... objects) {
        Preconditions.checkNotNull(objects, "Objects are null!");
    
        for (Object object : objects) {
            registerType(object);
        }
    }
    
    @Override
    public void registerTypes(Object object) {
        Preconditions.checkNotNull(object, "Subject is null!");
        for (Type type : Types.parseTypes(object)) {
            registerType(type);
        }
    }
}
