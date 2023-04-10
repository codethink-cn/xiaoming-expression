package cn.codethink.xiaoming.expression.interpreter;

public class CompilationConfigurationImpl
    implements CompilationConfiguration {
    
    public static class BuilderImpl
        implements Builder {
    
        private boolean strict = false;
        
        @Override
        public Builder strict(boolean strict) {
            this.strict = strict;
            return this;
        }
    
        @Override
        public CompilationConfiguration build() {
            return new CompilationConfigurationImpl(strict);
        }
    }
    
    private static final CompilationConfiguration INSTANCE = CompilationConfiguration.builder().build();
    
    public static CompilationConfiguration getInstance() {
        return INSTANCE;
    }
    
    private final boolean strict;
    
    public CompilationConfigurationImpl(boolean strict) {
        this.strict = strict;
    }
    
    @Override
    public boolean isStrict() {
        return strict;
    }
    
    @Override
    public CompilationConfiguration clone() {
        try {
            return (CompilationConfiguration) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
