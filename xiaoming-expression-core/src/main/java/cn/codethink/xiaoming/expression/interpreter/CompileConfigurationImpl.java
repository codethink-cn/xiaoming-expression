package cn.codethink.xiaoming.expression.interpreter;

public class CompileConfigurationImpl
    implements CompileConfiguration {
    
    public static class BuilderImpl
        implements Builder {
    
        private boolean strict = false;
        
        @Override
        public Builder strict(boolean strict) {
            this.strict = strict;
            return this;
        }
    
        @Override
        public CompileConfiguration build() {
            return new CompileConfigurationImpl(strict);
        }
    }
    
    private static final CompileConfiguration INSTANCE = CompileConfiguration.builder().build();
    
    public static CompileConfiguration getInstance() {
        return INSTANCE;
    }
    
    private final boolean strict;
    
    public CompileConfigurationImpl(boolean strict) {
        this.strict = strict;
    }
    
    @Override
    public boolean isStrict() {
        return strict;
    }
    
    @Override
    public CompileConfiguration clone() {
        try {
            return (CompileConfiguration) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
