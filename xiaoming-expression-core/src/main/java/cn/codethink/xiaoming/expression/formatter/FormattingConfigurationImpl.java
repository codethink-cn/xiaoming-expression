package cn.codethink.xiaoming.expression.formatter;

public class FormattingConfigurationImpl
    implements FormattingConfiguration {
    
    public static class BuilderImpl
        implements Builder {
    
        private boolean insertSpaceAfterParenthesis = false;
        private boolean insertSpaceBeforeParenthesis = false;
        private boolean insertSpaceAfterBrackets = false;
        private boolean insertSpaceBeforeBrackets = false;
        private boolean insertSpaceAfterBraces = false;
        private boolean insertSpaceBeforeBraces = false;
        private boolean insertSpaceAfterComma = true;
        private boolean insertSpaceBeforeComma = false;
        
        @Override
        public Builder insertSpaceAfterComma(boolean insertSpaceAfterComma) {
            this.insertSpaceAfterComma = insertSpaceAfterComma;
            return this;
        }
    
        @Override
        public Builder insertSpaceBeforeComma(boolean insertSpaceBeforeComma) {
            this.insertSpaceBeforeComma = insertSpaceBeforeComma;
            return this;
        }
    
        @Override
        public Builder insertSpaceBeforeParenthesis(boolean insertSpaceBeforeParenthesis) {
            this.insertSpaceBeforeParenthesis = insertSpaceBeforeParenthesis;
            return this;
        }
    
        @Override
        public Builder insertSpaceAfterParenthesis(boolean insertSpaceAfterParenthesis) {
            this.insertSpaceAfterParenthesis = insertSpaceAfterParenthesis;
            return this;
        }
    
        @Override
        public Builder insertSpaceBeforeBrackets(boolean insertSpaceBeforeBrackets) {
            this.insertSpaceBeforeBrackets = insertSpaceBeforeBrackets;
            return this;
        }
    
        @Override
        public Builder insertSpaceAfterBrackets(boolean insertSpaceAfterBrackets) {
            this.insertSpaceAfterBrackets = insertSpaceAfterBrackets;
            return this;
        }
    
        @Override
        public Builder insertSpaceBeforeBraces(boolean insertSpaceBeforeBraces) {
            this.insertSpaceBeforeBraces = insertSpaceBeforeBraces;
            return this;
        }
    
        @Override
        public Builder insertSpaceAfterBraces(boolean insertSpaceAfterBraces) {
            this.insertSpaceAfterBraces = insertSpaceAfterBraces;
            return this;
        }
    
        @Override
        public FormattingConfiguration build() {
            return new FormattingConfigurationImpl(
                insertSpaceAfterComma, insertSpaceBeforeComma,
                insertSpaceAfterParenthesis, insertSpaceBeforeParenthesis,
                insertSpaceAfterBrackets, insertSpaceBeforeBrackets,
                insertSpaceAfterBraces, insertSpaceBeforeBraces
            );
        }
    }
    
    private static final FormattingConfiguration INSTANCE = FormattingConfiguration.builder().build();
    
    public static FormattingConfiguration getInstance() {
        return INSTANCE;
    }
    
    private final boolean insertSpaceAfterComma;
    private final boolean insertSpaceBeforeComma;
    private final boolean insertSpaceAfterParenthesis;
    private final boolean insertSpaceBeforeParenthesis;
    private final boolean insertSpaceAfterBrackets;
    private final boolean insertSpaceBeforeBrackets;
    private final boolean insertSpaceAfterBraces;
    private final boolean insertSpaceBeforeBraces;
    
    public FormattingConfigurationImpl(boolean insertSpaceAfterComma, boolean insertSpaceBeforeComma,
                                       boolean insertSpaceAfterParenthesis, boolean insertSpaceBeforeParenthesis,
                                       boolean insertSpaceAfterBrackets, boolean insertSpaceBeforeBrackets,
                                       boolean insertSpaceAfterBraces, boolean insertSpaceBeforeBraces) {
        
        this.insertSpaceAfterComma = insertSpaceAfterComma;
        this.insertSpaceBeforeComma = insertSpaceBeforeComma;
        this.insertSpaceAfterParenthesis = insertSpaceAfterParenthesis;
        this.insertSpaceBeforeParenthesis = insertSpaceBeforeParenthesis;
        this.insertSpaceAfterBrackets = insertSpaceAfterBrackets;
        this.insertSpaceBeforeBrackets = insertSpaceBeforeBrackets;
        this.insertSpaceAfterBraces = insertSpaceAfterBraces;
        this.insertSpaceBeforeBraces = insertSpaceBeforeBraces;
    }
    
    @Override
    public boolean isInsertSpaceAfterComma() {
        return insertSpaceAfterComma;
    }
    
    @Override
    public boolean isInsertSpaceBeforeParenthesis() {
        return insertSpaceBeforeParenthesis;
    }
    
    @Override
    public boolean isInsertSpaceAfterParenthesis() {
        return insertSpaceAfterParenthesis;
    }
    
    @Override
    public boolean isInsertSpaceBeforeBrackets() {
        return insertSpaceBeforeBrackets;
    }
    
    @Override
    public boolean isInsertSpaceAfterBraces() {
        return insertSpaceAfterBraces;
    }
    
    @Override
    public boolean isInsertSpaceAfterBrackets() {
        return insertSpaceAfterBrackets;
    }
    
    @Override
    public boolean isInsertSpaceBeforeBraces() {
        return insertSpaceBeforeBraces;
    }
    
    @Override
    public boolean isInsertSpaceBeforeComma() {
        return insertSpaceBeforeComma;
    }
    
    @Override
    public FormattingConfiguration clone() {
        try {
            return (FormattingConfiguration) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
