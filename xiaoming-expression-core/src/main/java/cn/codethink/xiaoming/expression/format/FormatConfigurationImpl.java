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

package cn.codethink.xiaoming.expression.format;

import com.google.common.base.Preconditions;

import java.util.Objects;

public class FormatConfigurationImpl
    implements FormatConfiguration {
    
    public static class BuilderImpl
        implements Builder {
    
        private boolean compressSpaces = true;
        private int maxSpaces = Integer.MAX_VALUE;
    
        private FormatUnit comma = new TextFormatUnitImpl(",");
    
        private PairedFormatUnit parenthesis = new PairedFormatUnitImpl(new TextFormatUnitImpl("("), SpacesFormatUnitImpl.empty(), new TextFormatUnitImpl(")"));
        private PairedFormatUnit brackets = new PairedFormatUnitImpl(new TextFormatUnitImpl("["), SpacesFormatUnitImpl.empty(), new TextFormatUnitImpl("]"));
        private PairedFormatUnit braces = new PairedFormatUnitImpl(new TextFormatUnitImpl("{"), SpacesFormatUnitImpl.empty(), new TextFormatUnitImpl("}"));
    
        @Override
        public Builder compressSpaces(boolean compressSpaces) {
            this.compressSpaces = compressSpaces;
            return this;
        }
    
        @Override
        public Builder maxSpaces(int maxSpaces) {
            this.maxSpaces = maxSpaces;
            return this;
        }
    
        @Override
        public Builder comma(FormatUnit comma) {
            this.comma = comma;
            return this;
        }
    
        @Override
        public Builder parenthesis(PairedFormatUnit parenthesis) {
            this.parenthesis = parenthesis;
            return this;
        }
    
        @Override
        public Builder brackets(PairedFormatUnit brackets) {
            this.brackets = brackets;
            return this;
        }
    
        @Override
        public Builder braces(PairedFormatUnit braces) {
            this.braces = braces;
            return this;
        }
    
        @Override
        public FormatConfiguration build() {
            return new FormatConfigurationImpl(compressSpaces, maxSpaces, comma, parenthesis, brackets, braces);
        }
    }
    
    private static final FormatConfiguration INSTANCE = FormatConfiguration.builder().build();
    
    public static FormatConfiguration getInstance() {
        return INSTANCE;
    }
    
    private final boolean compressSpaces;
    private final int maxSpaces;
    
    private final FormatUnit comma;
    
    private final PairedFormatUnit parenthesis;
    private final PairedFormatUnit brackets;
    private final PairedFormatUnit braces;
    
    private Integer hashCodeCache;
    
    public FormatConfigurationImpl(boolean compressSpaces, int maxSpaces,
                                   FormatUnit comma,
                                   PairedFormatUnit parenthesis,
                                   PairedFormatUnit brackets,
                                   PairedFormatUnit braces) {
        
        Preconditions.checkNotNull(comma, "Format unit of comma is null!");
        Preconditions.checkNotNull(parenthesis, "Paired format unit of parenthesis is null!");
        Preconditions.checkNotNull(brackets, "Paired format unit of brackets is null!");
        Preconditions.checkNotNull(braces, "Paired format unit of braces is null!");
        
        this.compressSpaces = compressSpaces;
        this.maxSpaces = maxSpaces;
        this.comma = comma;
        this.parenthesis = parenthesis;
        this.brackets = brackets;
        this.braces = braces;
    }
    
    @Override
    public boolean isCompressSpaces() {
        return compressSpaces;
    }
    
    @Override
    public int getMaxSpaces() {
        return maxSpaces;
    }
    
    @Override
    public FormatUnit getComma() {
        return comma;
    }
    
    @Override
    public PairedFormatUnit getParenthesis() {
        return parenthesis;
    }
    
    @Override
    public PairedFormatUnit getBrackets() {
        return brackets;
    }
    
    @Override
    public PairedFormatUnit getBraces() {
        return braces;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass() || hashCode() != o.hashCode()) return false;
        FormatConfigurationImpl that = (FormatConfigurationImpl) o;
        return compressSpaces == that.compressSpaces
            && maxSpaces == that.maxSpaces
            && Objects.equals(comma, that.comma)
            && Objects.equals(parenthesis, that.parenthesis)
            && Objects.equals(brackets, that.brackets)
            && Objects.equals(braces, that.braces);
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(compressSpaces, maxSpaces, comma, parenthesis, brackets, braces);
        }
        return hashCodeCache;
    }
}