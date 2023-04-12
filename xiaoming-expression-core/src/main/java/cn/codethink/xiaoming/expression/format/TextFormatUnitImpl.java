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

public class TextFormatUnitImpl
    implements TextFormatUnit {
    
    private final int spacesBeforeText;
    private final int spacesAfterText;
    private final String text;
    
    private Integer hashCodeCache;
    private String toStringCache;
    
    public TextFormatUnitImpl(int spacesBeforeText, String text, int spacesAfterText) {
        Preconditions.checkArgument(spacesBeforeText >= 0, "Count of spaces before content must be greater than or equals to 0!");
        Preconditions.checkArgument(spacesAfterText >= 0, "Count of spaces after content must be greater than or equals to 0!");
        Preconditions.checkNotNull(text, "Text is null!");
        Preconditions.checkArgument(!text.isEmpty(), "Text is empty!");
        
        this.spacesBeforeText = spacesBeforeText;
        this.text = text;
        this.spacesAfterText = spacesAfterText;
    }
    
    public TextFormatUnitImpl(String text) {
        this(0, text, 0);
    }
    
    public TextFormatUnitImpl(String text, int spacesAfterText) {
        this(0, text, spacesAfterText);
    }
    
    public TextFormatUnitImpl(int spacesBeforeText, String text) {
        this(spacesBeforeText, text, 0);
    }
    
    @Override
    public int getSpacesBeforeText() {
        return spacesBeforeText;
    }
    
    @Override
    public int getSpacesAfterText() {
        return spacesAfterText;
    }
    
    @Override
    public String getText() {
        return text;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TextFormatUnitImpl that = (TextFormatUnitImpl) o;
        return spacesBeforeText == that.spacesBeforeText
            && spacesAfterText == that.spacesAfterText
            && Objects.equals(text, that.text);
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(spacesBeforeText, spacesAfterText, text);
        }
        return hashCodeCache;
    }
    
    @Override
    public String toString() {
        if (toStringCache == null) {
            toStringCache = "TextFormatUnitImpl{" +
                "spacesBeforeText=" + spacesBeforeText +
                ", text='" + text + '\'' +
                ", spacesAfterText=" + spacesAfterText +
                '}';
        }
        return toStringCache;
    }
}
