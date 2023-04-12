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

public class FormattingSpaceItem
    implements FormattingItem {
    
    private static final FormattingSpaceItem ZERO = new FormattingSpaceItem(0);
    
    public static FormattingSpaceItem of(int count) {
        if (count == 0) {
            return ZERO;
        } else {
            return new FormattingSpaceItem(count);
        }
    }
    
    private final int count;
    
    private FormattingSpaceItem(int count) {
        Preconditions.checkArgument(count >= 0, "Count of spaces must be greater than or equals to 0!");
        this.count = count;
    }
    
    public int getCount() {
        return count;
    }
}
