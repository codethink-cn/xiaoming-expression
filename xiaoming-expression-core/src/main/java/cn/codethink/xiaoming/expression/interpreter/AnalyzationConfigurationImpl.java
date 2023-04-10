package cn.codethink.xiaoming.expression.interpreter;

public class AnalyzationConfigurationImpl
    implements AnalyzationConfiguration {
    
    public static class BuilderImpl
        implements Builder {
    
        private boolean flatData = false;
        
        @Override
        public Builder flatData(boolean flatData) {
            this.flatData = flatData;
            return this;
        }
    
        @Override
        public AnalyzationConfiguration build() {
            return new AnalyzationConfigurationImpl(flatData);
        }
    }
    
    private static final AnalyzationConfiguration INSTANCE = AnalyzationConfiguration.builder().build();
    
    public static AnalyzationConfiguration getInstance() {
        return INSTANCE;
    }
    
    private final boolean flatData;
    
    public AnalyzationConfigurationImpl(boolean flatData) {
        this.flatData = flatData;
    }
    
    @Override
    public boolean isFlatData() {
        return flatData;
    }
    
    @Override
    public AnalyzationConfiguration clone() {
        try {
            return (AnalyzationConfiguration) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
