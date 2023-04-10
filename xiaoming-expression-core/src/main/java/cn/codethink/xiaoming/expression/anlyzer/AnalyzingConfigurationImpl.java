package cn.codethink.xiaoming.expression.anlyzer;

import cn.codethink.xiaoming.expression.analyzer.AnalyzingConfiguration;

public class AnalyzingConfigurationImpl
    implements AnalyzingConfiguration {
    
    public static class BuilderImpl
        implements Builder {
    
        private boolean flatData = false;
        
        @Override
        public Builder flatData(boolean flatData) {
            this.flatData = flatData;
            return this;
        }
    
        @Override
        public AnalyzingConfiguration build() {
            return new AnalyzingConfigurationImpl(flatData);
        }
    }
    
    private static final AnalyzingConfiguration INSTANCE = AnalyzingConfiguration.builder().build();
    
    public static AnalyzingConfiguration getInstance() {
        return INSTANCE;
    }
    
    private final boolean flatData;
    
    public AnalyzingConfigurationImpl(boolean flatData) {
        this.flatData = flatData;
    }
    
    @Override
    public boolean isFlatData() {
        return flatData;
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
