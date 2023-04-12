/*
 * Copyright 2023 CodeThink Technologies and contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.codethink.xiaoming.expression.formatter;

public class FormattingConfigurationImpl
    implements FormattingConfiguration {
    
    public static class BuilderImpl
        implements Builder {
    
        private boolean minimizeSpaces = true;
        
        private int spacesBeforeLeftParenthesis = 0;
        private int spacesAfterLeftParenthesis = 0;
        private int spacesBeforeRightParenthesis = 0;
        private int spacesAfterRightParenthesis = 0;
        private int spacesInEmptyParenthesis = 0;
        
        private int spacesBeforeLeftBrackets = 0;
        private int spacesAfterLeftBrackets = 0;
        private int spacesBeforeRightBrackets = 0;
        private int spacesAfterRightBrackets = 0;
        private int spacesInEmptyBrackets = 0;
        
        private int spacesBeforeLeftBraces = 0;
        private int spacesAfterLeftBraces = 0;
        private int spacesBeforeRightBraces = 0;
        private int spacesAfterRightBraces = 0;
        private int spacesInEmptyBraces = 0;
        
        private int spacesAfterComma = 0;
        private int spacesBeforeComma = 0;
        
        @Override
        public Builder minimizeSpaces(boolean minimizeSpaces) {
            this.minimizeSpaces = minimizeSpaces;
            return this;
        }
    
        @Override
        public Builder spacesBeforeLeftParenthesis(int spacesBeforeLeftParenthesis) {
            this.spacesBeforeLeftParenthesis = spacesBeforeLeftParenthesis;
            return this;
        }
    
        @Override
        public Builder spacesAfterLeftParenthesis(int spacesAfterLeftParenthesis) {
            this.spacesAfterLeftParenthesis = spacesAfterLeftParenthesis;
            return this;
        }
    
        @Override
        public Builder spacesBeforeRightParenthesis(int spacesBeforeRightParenthesis) {
            this.spacesBeforeRightParenthesis = spacesBeforeRightParenthesis;
            return this;
        }
    
        @Override
        public Builder spacesAfterRightParenthesis(int spacesAfterRightParenthesis) {
            this.spacesAfterRightParenthesis = spacesAfterRightParenthesis;
            return this;
        }
    
        @Override
        public Builder spacesInEmptyParenthesis(int spacesInEmptyParenthesis) {
            this.spacesInEmptyParenthesis = spacesInEmptyParenthesis;
            return this;
        }
    
        @Override
        public Builder spacesBeforeLeftBrackets(int spacesBeforeLeftBrackets) {
            this.spacesBeforeLeftBrackets = spacesBeforeLeftBrackets;
            return this;
        }
    
        @Override
        public Builder spacesAfterLeftBrackets(int spacesAfterLeftBrackets) {
            this.spacesAfterLeftBrackets = spacesAfterLeftBrackets;
            return this;
        }
    
        @Override
        public Builder spacesBeforeRightBrackets(int spacesBeforeRightBrackets) {
            this.spacesBeforeRightBrackets = spacesBeforeRightBrackets;
            return this;
        }
    
        @Override
        public Builder spacesAfterRightBrackets(int spacesAfterRightBrackets) {
            this.spacesAfterRightBrackets = spacesAfterRightBrackets;
            return this;
        }
    
        @Override
        public Builder spacesInEmptyBrackets(int spacesInEmptyBrackets) {
            this.spacesInEmptyBrackets = spacesInEmptyBrackets;
            return this;
        }
    
        @Override
        public Builder spacesBeforeLeftBraces(int spacesBeforeLeftBraces) {
            this.spacesBeforeLeftBraces = spacesBeforeLeftBraces;
            return this;
        }
    
        @Override
        public Builder spacesAfterLeftBraces(int spacesAfterLeftBraces) {
            this.spacesAfterLeftBraces = spacesAfterLeftBraces;
            return this;
        }
    
        @Override
        public Builder spacesBeforeRightBraces(int spacesBeforeRightBraces) {
            this.spacesBeforeRightBraces = spacesBeforeRightBraces;
            return this;
        }
    
        @Override
        public Builder spacesAfterRightBraces(int spacesAfterRightBraces) {
            this.spacesAfterRightBraces = spacesAfterRightBraces;
            return this;
        }
    
        @Override
        public Builder spacesInEmptyBraces(int spacesInEmptyBraces) {
            this.spacesInEmptyBraces = spacesInEmptyBraces;
            return this;
        }
    
        @Override
        public Builder spacesBeforeComma(int spacesBeforeComma) {
            this.spacesBeforeComma = spacesBeforeComma;
            return this;
        }
    
        @Override
        public Builder spacesAfterComma(int spacesAfterComma) {
            this.spacesAfterComma = spacesAfterComma;
            return this;
        }
    
        @Override
        public FormattingConfiguration build() {
            return new FormattingConfigurationImpl(
                minimizeSpaces,
                spacesBeforeLeftParenthesis,
                spacesAfterLeftParenthesis,
                spacesBeforeRightParenthesis,
                spacesAfterRightParenthesis,
                spacesInEmptyParenthesis,
                spacesBeforeLeftBrackets,
                spacesAfterLeftBrackets,
                spacesBeforeRightBrackets,
                spacesAfterRightBrackets,
                spacesInEmptyBrackets,
                spacesBeforeLeftBraces,
                spacesAfterLeftBraces,
                spacesBeforeRightBraces,
                spacesAfterRightBraces,
                spacesInEmptyBraces,
                spacesBeforeComma,
                spacesAfterComma
            );
        }
    }
    
    private static final FormattingConfiguration INSTANCE = FormattingConfiguration.builder().build();
    
    public static FormattingConfiguration getInstance() {
        return INSTANCE;
    }
    
    private final boolean minimizeSpaces;
    
    private final int spacesBeforeLeftParenthesis;
    private final int spacesAfterLeftParenthesis;
    private final int spacesBeforeRightParenthesis;
    private final int spacesAfterRightParenthesis;
    private final int spacesInEmptyParenthesis;
    
    private final int spacesBeforeLeftBrackets;
    private final int spacesAfterLeftBrackets;
    private final int spacesBeforeRightBrackets;
    private final int spacesAfterRightBrackets;
    private final int spacesInEmptyBrackets;
    
    private final int spacesBeforeLeftBraces;
    private final int spacesAfterLeftBraces;
    private final int spacesBeforeRightBraces;
    private final int spacesAfterRightBraces;
    private final int spacesInEmptyBraces;
    
    private final int spacesAfterComma;
    private final int spacesBeforeComma;
    
    public FormattingConfigurationImpl(boolean minimizeSpaces,
                                       int spacesBeforeLeftParenthesis,
                                       int spacesAfterLeftParenthesis,
                                       int spacesBeforeRightParenthesis,
                                       int spacesAfterRightParenthesis,
                                       int spacesInEmptyParenthesis,
                                       int spacesBeforeLeftBrackets,
                                       int spacesAfterLeftBrackets,
                                       int spacesBeforeRightBrackets,
                                       int spacesAfterRightBrackets,
                                       int spacesInEmptyBrackets,
                                       int spacesBeforeLeftBraces,
                                       int spacesAfterLeftBraces,
                                       int spacesBeforeRightBraces,
                                       int spacesAfterRightBraces,
                                       int spacesInEmptyBraces,
                                       int spacesBeforeComma,
                                       int spacesAfterComma) {
        
        this.minimizeSpaces = minimizeSpaces;
        this.spacesBeforeLeftParenthesis = spacesBeforeLeftParenthesis;
        this.spacesAfterLeftParenthesis = spacesAfterLeftParenthesis;
        this.spacesBeforeRightParenthesis = spacesBeforeRightParenthesis;
        this.spacesAfterRightParenthesis = spacesAfterRightParenthesis;
        this.spacesInEmptyParenthesis = spacesInEmptyParenthesis;
        this.spacesBeforeLeftBrackets = spacesBeforeLeftBrackets;
        this.spacesAfterLeftBrackets = spacesAfterLeftBrackets;
        this.spacesBeforeRightBrackets = spacesBeforeRightBrackets;
        this.spacesAfterRightBrackets = spacesAfterRightBrackets;
        this.spacesInEmptyBrackets = spacesInEmptyBrackets;
        this.spacesBeforeLeftBraces = spacesBeforeLeftBraces;
        this.spacesAfterLeftBraces = spacesAfterLeftBraces;
        this.spacesBeforeRightBraces = spacesBeforeRightBraces;
        this.spacesAfterRightBraces = spacesAfterRightBraces;
        this.spacesInEmptyBraces = spacesInEmptyBraces;
        this.spacesBeforeComma = spacesBeforeComma;
        this.spacesAfterComma = spacesAfterComma;
    }
    
    @Override
    public boolean isMinimizeSpaces() {
        return minimizeSpaces;
    }
    
    @Override
    public int getSpacesBeforeLeftParenthesis() {
        return spacesBeforeLeftParenthesis;
    }
    
    @Override
    public int getSpacesAfterLeftParenthesis() {
        return spacesAfterLeftParenthesis;
    }
    
    @Override
    public int getSpacesBeforeRightParenthesis() {
        return spacesBeforeRightParenthesis;
    }
    
    @Override
    public int getSpacesAfterRightParenthesis() {
        return spacesAfterRightParenthesis;
    }
    
    @Override
    public int getSpacesInEmptyParenthesis() {
        return spacesInEmptyParenthesis;
    }
    
    @Override
    public int getSpacesBeforeLeftBrackets() {
        return spacesBeforeLeftBrackets;
    }
    
    @Override
    public int getSpacesAfterLeftBrackets() {
        return spacesAfterLeftBrackets;
    }
    
    @Override
    public int getSpacesBeforeRightBrackets() {
        return spacesBeforeRightBrackets;
    }
    
    @Override
    public int getSpacesAfterRightBrackets() {
        return spacesAfterRightBrackets;
    }
    
    @Override
    public int getSpacesInEmptyBrackets() {
        return spacesInEmptyBrackets;
    }
    
    @Override
    public int getSpacesBeforeLeftBraces() {
        return spacesBeforeLeftBraces;
    }
    
    @Override
    public int getSpacesAfterLeftBraces() {
        return spacesAfterLeftBraces;
    }
    
    @Override
    public int getSpacesBeforeRightBraces() {
        return spacesBeforeRightBraces;
    }
    
    @Override
    public int getSpacesAfterRightBraces() {
        return spacesAfterRightBraces;
    }
    
    @Override
    public int getSpacesInEmptyBraces() {
        return spacesInEmptyBraces;
    }
    
    @Override
    public int getSpacesAfterComma() {
        return spacesAfterComma;
    }
    
    @Override
    public int getSpacesBeforeComma() {
        return spacesBeforeComma;
    }
}