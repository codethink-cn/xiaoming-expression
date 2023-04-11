package cn.codethink.xiaoming.expression.anlyzer;

import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;

public class AnalyzingConfigurationImpl
    implements AnalyzingConfiguration {
    
    public static class BuilderImpl
        implements Builder {
    
        @Override
        public AnalyzingConfiguration build() {
            return INSTANCE;
        }
    }
    
    private static final AnalyzingConfiguration INSTANCE = new AnalyzingConfigurationImpl();
    
    public static AnalyzingConfiguration getInstance() {
        return INSTANCE;
    }
    
    @Override
    public AnalyzingConfiguration clone() {
        try {
            return (AnalyzingConfiguration) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
