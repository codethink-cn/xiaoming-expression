package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.compiler.CompilingConfiguration;

public class CompilingConfigurationImpl
    implements CompilingConfiguration {
    
    public static class BuilderImpl
        implements Builder {
    
        private boolean strict = false;
        
        @Override
        public Builder strict(boolean strict) {
            this.strict = strict;
            return this;
        }
    
        @Override
        public CompilingConfiguration build() {
            return new CompilingConfigurationImpl(strict);
        }
    }
    
    private static final CompilingConfiguration INSTANCE = CompilingConfiguration.builder().build();
    
    public static CompilingConfiguration getInstance() {
        return INSTANCE;
    }
    
    private final boolean strict;
    
    public CompilingConfigurationImpl(boolean strict) {
        this.strict = strict;
    }
    
    @Override
    public boolean isStrict() {
        return strict;
    }
    
    @Override
    public CompilingConfiguration clone() {
        try {
            return (CompilingConfiguration) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
