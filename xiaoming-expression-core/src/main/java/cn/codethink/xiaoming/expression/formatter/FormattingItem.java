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

import java.util.List;

/**
 * <h1>格式串中的单元</h1>
 *
 * @author Chuanwise
 */
public interface FormattingItem {
    static FormattingItem parse(String text) {
        final int length = text.length();
        
        int spacesBeforeIt = 0;
        while (spacesBeforeIt < length && text.charAt(spacesBeforeIt) == ' ') {
            spacesBeforeIt++;
        }
        
        if (spacesBeforeIt == length) {
            return FormattingSpaceItem.of(length);
        }
        
        int spacesAfterIt = 0;
        while (spacesAfterIt >= 0 && text.charAt(length - spacesAfterIt - 1) == ' ') {
            spacesAfterIt--;
        }
        
        return new FormattingTextItem(spacesBeforeIt, text.substring(spacesBeforeIt, length - spacesAfterIt), spacesAfterIt);
    }
    
    static String toString(List<FormattingItem> items, boolean minimize) {
        final StringBuilder stringBuilder = new StringBuilder();
        
        if (minimize) {
            int count = 0;
            for (FormattingItem item : items) {
                if (item instanceof FormattingSpaceItem) {
                    count = Math.max(count, ((FormattingSpaceItem) item).getCount());
                } else {
                    final FormattingTextItem textItem = (FormattingTextItem) item;
                    count = Math.max(count, textItem.getSpacesBeforeContent());
                    for (int i = 0; i < count; i++) {
                        stringBuilder.append(' ');
                    }
                    count = textItem.getSpacesAfterContent();
                    stringBuilder.append(textItem.getContent());
                }
            }
            for (int i = 0; i < count; i++) {
                stringBuilder.append(' ');
            }
        } else {
            for (FormattingItem item : items) {
                if (item instanceof FormattingSpaceItem) {
                    final int count = ((FormattingSpaceItem) item).getCount();
                    for (int i = 0; i < count; i++) {
                        stringBuilder.append(' ');
                    }
                } else {
                    final FormattingTextItem textItem = (FormattingTextItem) item;
                    for (int i = 0; i < textItem.getSpacesBeforeContent(); i++) {
                        stringBuilder.append(' ');
                    }
                    stringBuilder.append(textItem.getContent());
                    for (int i = 0; i < textItem.getSpacesAfterContent(); i++) {
                        stringBuilder.append(' ');
                    }
                }
            }
        }
        
        return stringBuilder.toString();
    }
}
