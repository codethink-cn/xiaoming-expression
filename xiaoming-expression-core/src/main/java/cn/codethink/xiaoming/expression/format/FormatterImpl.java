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

public class FormatterImpl
    implements Formatter {
    
    private static final String EMPTY = "        ";
    private static final int EMPTY_STRING_LENGTH = EMPTY.length();
    
    private String string;
    private StringBuilder stringBuilder;
    private final FormatConfiguration configuration;
    
    private int spaces = 0;
    
    public FormatterImpl(StringBuilder stringBuilder, FormatConfiguration configuration) {
        Preconditions.checkNotNull(configuration, "Format configuration is null!");
        
        this.stringBuilder = stringBuilder;
        this.configuration = configuration;
    }
    
    public FormatterImpl(FormatConfiguration configuration) {
        this(null, configuration);
    }
    
    @Override
    public Formatter plus(String text) {
        appendText(text);
        return this;
    }
    
    @Override
    public Formatter plus(FormatUnit unit) {
        if (unit instanceof TextFormatUnit) {
            final TextFormatUnit textFormatUnit = (TextFormatUnit) unit;
            
            // spaces before text
            if (configuration.isCompressSpaces()) {
                spaces = Math.max(spaces, textFormatUnit.getSpacesBeforeText());
                appendSpaces(spaces);
            } else {
                appendSpaces(spaces + textFormatUnit.getSpacesBeforeText());
            }
    
            appendText(textFormatUnit.getText());
            
            spaces = textFormatUnit.getSpacesAfterText();
            return this;
        }
        if (unit instanceof SpacesFormatUnit) {
            final SpacesFormatUnit spacesFormatUnit = (SpacesFormatUnit) unit;
            if (configuration.isCompressSpaces()) {
                spaces = Math.max(spaces, spacesFormatUnit.getCount());
            } else {
                appendSpaces(spaces + spacesFormatUnit.getCount());
            }
            return this;
        }
        throw new IllegalArgumentException("Unexpected unit: " + unit);
    }
    
    private void appendSpaces(int spaces) {
        if (spaces == 0) {
            return;
        }
        if (spaces < EMPTY_STRING_LENGTH) {
            appendSpaces0(EMPTY_STRING_LENGTH);
            return;
        }
        while (spaces > EMPTY_STRING_LENGTH) {
            appendText(EMPTY);
            spaces -= EMPTY_STRING_LENGTH;
        }
        appendSpaces0(spaces);
    }
    
    private void appendText(String text) {
        if (stringBuilder != null) {
            stringBuilder.append(text);
            return;
        }
        if (string == null) {
            string = text;
            return;
        }
        stringBuilder = new StringBuilder(string);
    }
    
    private void appendSpaces0(int end) {
        if (stringBuilder != null) {
            stringBuilder.append(EMPTY, 0, end);
            return;
        }
        if (string == null) {
            string = EMPTY.substring(0, end);
            return;
        }
        stringBuilder = new StringBuilder(string + end);
        stringBuilder.append(EMPTY, 0, end);
    }
    
    @Override
    public FormatConfiguration getConfiguration() {
        return configuration;
    }
    
    @Override
    public String toString() {
        appendSpaces(spaces);
        spaces = 0;
        if (stringBuilder != null) {
            return stringBuilder.toString();
        } else if (string != null) {
            return string;
        } else {
            return "";
        }
    }
}
