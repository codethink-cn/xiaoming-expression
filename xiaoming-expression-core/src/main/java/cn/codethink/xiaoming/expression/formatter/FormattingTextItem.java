/*
 * Copyright 2023. CodeThink Technologies and contributors.
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

import com.google.common.base.Preconditions;

public class FormattingTextItem
    implements FormattingItem {
    
    private final int countOfSpacesBeforeContent;
    private final int countOfSpacesAfterContent;
    private final String content;
    
    public FormattingTextItem(int countOfSpacesBeforeContent, String content, int countOfSpacesAfterContent) {
        Preconditions.checkArgument(countOfSpacesBeforeContent >= 0, "Count of spaces before content must be greater than or equals to 0!");
        Preconditions.checkArgument(countOfSpacesAfterContent >= 0, "Count of spaces after content must be greater than or equals to 0!");
        
        this.countOfSpacesBeforeContent = countOfSpacesBeforeContent;
        this.content = content;
        this.countOfSpacesAfterContent = countOfSpacesAfterContent;
    }
    
    public FormattingTextItem(String content) {
        this(0, content, 0);
    }
    
    public FormattingTextItem(String content, int countOfSpacesAfterContent) {
        this(0, content, countOfSpacesAfterContent);
    }
    
    public FormattingTextItem(int countOfSpacesBeforeContent, String content) {
        this(countOfSpacesBeforeContent, content, 0);
    }
    
    public int getCountOfSpacesBeforeContent() {
        return countOfSpacesBeforeContent;
    }
    
    public int getCountOfSpacesAfterContent() {
        return countOfSpacesAfterContent;
    }
    
    public String getContent() {
        return content;
    }
}
