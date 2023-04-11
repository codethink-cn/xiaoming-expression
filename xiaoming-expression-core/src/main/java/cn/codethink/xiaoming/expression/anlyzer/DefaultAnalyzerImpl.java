package cn.codethink.xiaoming.expression.anlyzer;

import cn.codethink.xiaoming.expression.ConstructExpressionImpl;
import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.analyzer.Analyzer;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingContext;
import cn.codethink.xiaoming.expression.analyzer.AnalyzingException;
import cn.codethink.xiaoming.expression.constructor.Constructor;
import cn.codethink.xiaoming.expression.type.Type;
import com.google.common.base.Preconditions;

import java.util.Collections;

public class DefaultAnalyzerImpl
    implements Analyzer {
    
    private final Constructor constructor;
    
    public DefaultAnalyzerImpl(Constructor constructor) {
        Preconditions.checkNotNull(constructor, "Constructor is null!");
        
        this.constructor = constructor;
    }
    
    @Override
    public Expression analyze(AnalyzingContext context) throws AnalyzingException {
        Preconditions.checkNotNull(context, "Analyzing context is null!");
        
        return new ConstructExpressionImpl(constructor, Collections.emptyList());
    }
}
