package cn.codethink.xiaoming.expression.formatter;

public class FormattingConfigurationImpl
    implements FormattingConfiguration {
    
    public static class BuilderImpl
        implements Builder {
    
        private boolean minimizeSpaces = true;
        
        private int countOfSpacesBeforeLeftParenthesis = 0;
        private int countOfSpacesAfterLeftParenthesis = 0;
        private int countOfSpacesBeforeRightParenthesis = 0;
        private int countOfSpacesAfterRightParenthesis = 0;
        private int countOfSpacesInEmptyParenthesis = 0;
        
        private int countOfSpacesBeforeLeftBrackets = 0;
        private int countOfSpacesAfterLeftBrackets = 0;
        private int countOfSpacesBeforeRightBrackets = 0;
        private int countOfSpacesAfterRightBrackets = 0;
        private int countOfSpacesInEmptyBrackets = 0;
        
        private int countOfSpacesBeforeLeftBraces = 0;
        private int countOfSpacesAfterLeftBraces = 0;
        private int countOfSpacesBeforeRightBraces = 0;
        private int countOfSpacesAfterRightBraces = 0;
        private int countOfSpacesInEmptyBraces = 0;
        
        private int countOfSpacesAfterComma = 0;
        private int countOfSpacesBeforeComma = 0;
        
        @Override
        public Builder minimizeSpaces(boolean minimizeSpaces) {
            this.minimizeSpaces = minimizeSpaces;
            return this;
        }
    
        @Override
        public Builder countOfSpacesBeforeLeftParenthesis(int countOfSpacesBeforeLeftParenthesis) {
            this.countOfSpacesBeforeLeftParenthesis = countOfSpacesBeforeLeftParenthesis;
            return this;
        }
    
        @Override
        public Builder countOfSpacesAfterLeftParenthesis(int countOfSpacesAfterLeftParenthesis) {
            this.countOfSpacesAfterLeftParenthesis = countOfSpacesAfterLeftParenthesis;
            return this;
        }
    
        @Override
        public Builder countOfSpacesBeforeRightParenthesis(int countOfSpacesBeforeRightParenthesis) {
            this.countOfSpacesBeforeRightParenthesis = countOfSpacesBeforeRightParenthesis;
            return this;
        }
    
        @Override
        public Builder countOfSpacesAfterRightParenthesis(int countOfSpacesAfterRightParenthesis) {
            this.countOfSpacesAfterRightParenthesis = countOfSpacesAfterRightParenthesis;
            return this;
        }
    
        @Override
        public Builder countOfSpacesInEmptyParenthesis(int countOfSpacesInEmptyParenthesis) {
            this.countOfSpacesInEmptyParenthesis = countOfSpacesInEmptyParenthesis;
            return this;
        }
    
        @Override
        public Builder countOfSpacesBeforeLeftBrackets(int countOfSpacesBeforeLeftBrackets) {
            this.countOfSpacesBeforeLeftBrackets = countOfSpacesBeforeLeftBrackets;
            return this;
        }
    
        @Override
        public Builder countOfSpacesAfterLeftBrackets(int countOfSpacesAfterLeftBrackets) {
            this.countOfSpacesAfterLeftBrackets = countOfSpacesAfterLeftBrackets;
            return this;
        }
    
        @Override
        public Builder countOfSpacesBeforeRightBrackets(int countOfSpacesBeforeRightBrackets) {
            this.countOfSpacesBeforeRightBrackets = countOfSpacesBeforeRightBrackets;
            return this;
        }
    
        @Override
        public Builder countOfSpacesAfterRightBrackets(int countOfSpacesAfterRightBrackets) {
            this.countOfSpacesAfterRightBrackets = countOfSpacesAfterRightBrackets;
            return this;
        }
    
        @Override
        public Builder countOfSpacesInEmptyBrackets(int countOfSpacesInEmptyBrackets) {
            this.countOfSpacesInEmptyBrackets = countOfSpacesInEmptyBrackets;
            return this;
        }
    
        @Override
        public Builder countOfSpacesBeforeLeftBraces(int countOfSpacesBeforeLeftBraces) {
            this.countOfSpacesBeforeLeftBraces = countOfSpacesBeforeLeftBraces;
            return this;
        }
    
        @Override
        public Builder countOfSpacesAfterLeftBraces(int countOfSpacesAfterLeftBraces) {
            this.countOfSpacesAfterLeftBraces = countOfSpacesAfterLeftBraces;
            return this;
        }
    
        @Override
        public Builder countOfSpacesBeforeRightBraces(int countOfSpacesBeforeRightBraces) {
            this.countOfSpacesBeforeRightBraces = countOfSpacesBeforeRightBraces;
            return this;
        }
    
        @Override
        public Builder countOfSpacesAfterRightBraces(int countOfSpacesAfterRightBraces) {
            this.countOfSpacesAfterRightBraces = countOfSpacesAfterRightBraces;
            return this;
        }
    
        @Override
        public Builder countOfSpacesInEmptyBraces(int countOfSpacesInEmptyBraces) {
            this.countOfSpacesInEmptyBraces = countOfSpacesInEmptyBraces;
            return this;
        }
    
        @Override
        public Builder countOfSpacesBeforeComma(int countOfSpacesBeforeComma) {
            this.countOfSpacesBeforeComma = countOfSpacesBeforeComma;
            return this;
        }
    
        @Override
        public Builder countOfSpacesAfterComma(int countOfSpacesAfterComma) {
            this.countOfSpacesAfterComma = countOfSpacesAfterComma;
            return this;
        }
    
        @Override
        public FormattingConfiguration build() {
            return new FormattingConfigurationImpl(
                minimizeSpaces,
                countOfSpacesBeforeLeftParenthesis,
                countOfSpacesAfterLeftParenthesis,
                countOfSpacesBeforeRightParenthesis,
                countOfSpacesAfterRightParenthesis,
                countOfSpacesInEmptyParenthesis,
                countOfSpacesBeforeLeftBrackets,
                countOfSpacesAfterLeftBrackets,
                countOfSpacesBeforeRightBrackets,
                countOfSpacesAfterRightBrackets,
                countOfSpacesInEmptyBrackets,
                countOfSpacesBeforeLeftBraces,
                countOfSpacesAfterLeftBraces,
                countOfSpacesBeforeRightBraces,
                countOfSpacesAfterRightBraces,
                countOfSpacesInEmptyBraces,
                countOfSpacesBeforeComma,
                countOfSpacesAfterComma
            );
        }
    }
    
    private static final FormattingConfiguration INSTANCE = FormattingConfiguration.builder().build();
    
    public static FormattingConfiguration getInstance() {
        return INSTANCE;
    }
    
    private final boolean minimizeSpaces;
    
    private final int countOfSpacesBeforeLeftParenthesis;
    private final int countOfSpacesAfterLeftParenthesis;
    private final int countOfSpacesBeforeRightParenthesis;
    private final int countOfSpacesAfterRightParenthesis;
    private final int countOfSpacesInEmptyParenthesis;
    
    private final int countOfSpacesBeforeLeftBrackets;
    private final int countOfSpacesAfterLeftBrackets;
    private final int countOfSpacesBeforeRightBrackets;
    private final int countOfSpacesAfterRightBrackets;
    private final int countOfSpacesInEmptyBrackets;
    
    private final int countOfSpacesBeforeLeftBraces;
    private final int countOfSpacesAfterLeftBraces;
    private final int countOfSpacesBeforeRightBraces;
    private final int countOfSpacesAfterRightBraces;
    private final int countOfSpacesInEmptyBraces;
    
    private final int countOfSpacesAfterComma;
    private final int countOfSpacesBeforeComma;
    
    public FormattingConfigurationImpl(boolean minimizeSpaces,
                                       int countOfSpacesBeforeLeftParenthesis,
                                       int countOfSpacesAfterLeftParenthesis,
                                       int countOfSpacesBeforeRightParenthesis,
                                       int countOfSpacesAfterRightParenthesis,
                                       int countOfSpacesInEmptyParenthesis,
                                       int countOfSpacesBeforeLeftBrackets,
                                       int countOfSpacesAfterLeftBrackets,
                                       int countOfSpacesBeforeRightBrackets,
                                       int countOfSpacesAfterRightBrackets,
                                       int countOfSpacesInEmptyBrackets,
                                       int countOfSpacesBeforeLeftBraces,
                                       int countOfSpacesAfterLeftBraces,
                                       int countOfSpacesBeforeRightBraces,
                                       int countOfSpacesAfterRightBraces,
                                       int countOfSpacesInEmptyBraces,
                                       int countOfSpacesBeforeComma,
                                       int countOfSpacesAfterComma) {
        
        this.minimizeSpaces = minimizeSpaces;
        this.countOfSpacesBeforeLeftParenthesis = countOfSpacesBeforeLeftParenthesis;
        this.countOfSpacesAfterLeftParenthesis = countOfSpacesAfterLeftParenthesis;
        this.countOfSpacesBeforeRightParenthesis = countOfSpacesBeforeRightParenthesis;
        this.countOfSpacesAfterRightParenthesis = countOfSpacesAfterRightParenthesis;
        this.countOfSpacesInEmptyParenthesis = countOfSpacesInEmptyParenthesis;
        this.countOfSpacesBeforeLeftBrackets = countOfSpacesBeforeLeftBrackets;
        this.countOfSpacesAfterLeftBrackets = countOfSpacesAfterLeftBrackets;
        this.countOfSpacesBeforeRightBrackets = countOfSpacesBeforeRightBrackets;
        this.countOfSpacesAfterRightBrackets = countOfSpacesAfterRightBrackets;
        this.countOfSpacesInEmptyBrackets = countOfSpacesInEmptyBrackets;
        this.countOfSpacesBeforeLeftBraces = countOfSpacesBeforeLeftBraces;
        this.countOfSpacesAfterLeftBraces = countOfSpacesAfterLeftBraces;
        this.countOfSpacesBeforeRightBraces = countOfSpacesBeforeRightBraces;
        this.countOfSpacesAfterRightBraces = countOfSpacesAfterRightBraces;
        this.countOfSpacesInEmptyBraces = countOfSpacesInEmptyBraces;
        this.countOfSpacesBeforeComma = countOfSpacesBeforeComma;
        this.countOfSpacesAfterComma = countOfSpacesAfterComma;
    }
    
    @Override
    public boolean isMinimizeSpaces() {
        return minimizeSpaces;
    }
    
    @Override
    public int getCountOfSpacesBeforeLeftParenthesis() {
        return countOfSpacesBeforeLeftParenthesis;
    }
    
    @Override
    public int getCountOfSpacesAfterLeftParenthesis() {
        return countOfSpacesAfterLeftParenthesis;
    }
    
    @Override
    public int getCountOfSpacesBeforeRightParenthesis() {
        return countOfSpacesBeforeRightParenthesis;
    }
    
    @Override
    public int getCountOfSpacesAfterRightParenthesis() {
        return countOfSpacesAfterRightParenthesis;
    }
    
    @Override
    public int getCountOfSpacesInEmptyParenthesis() {
        return countOfSpacesInEmptyParenthesis;
    }
    
    @Override
    public int getCountOfSpacesBeforeLeftBrackets() {
        return countOfSpacesBeforeLeftBrackets;
    }
    
    @Override
    public int getCountOfSpacesAfterLeftBrackets() {
        return countOfSpacesAfterLeftBrackets;
    }
    
    @Override
    public int getCountOfSpacesBeforeRightBrackets() {
        return countOfSpacesBeforeRightBrackets;
    }
    
    @Override
    public int getCountOfSpacesAfterRightBrackets() {
        return countOfSpacesAfterRightBrackets;
    }
    
    @Override
    public int getCountOfSpacesInEmptyBrackets() {
        return countOfSpacesInEmptyBrackets;
    }
    
    @Override
    public int getCountOfSpacesBeforeLeftBraces() {
        return countOfSpacesBeforeLeftBraces;
    }
    
    @Override
    public int getCountOfSpacesAfterLeftBraces() {
        return countOfSpacesAfterLeftBraces;
    }
    
    @Override
    public int getCountOfSpacesBeforeRightBraces() {
        return countOfSpacesBeforeRightBraces;
    }
    
    @Override
    public int getCountOfSpacesAfterRightBraces() {
        return countOfSpacesAfterRightBraces;
    }
    
    @Override
    public int getCountOfSpacesInEmptyBraces() {
        return countOfSpacesInEmptyBraces;
    }
    
    @Override
    public int getCountOfSpacesAfterComma() {
        return countOfSpacesAfterComma;
    }
    
    @Override
    public int getCountOfSpacesBeforeComma() {
        return countOfSpacesBeforeComma;
    }
}