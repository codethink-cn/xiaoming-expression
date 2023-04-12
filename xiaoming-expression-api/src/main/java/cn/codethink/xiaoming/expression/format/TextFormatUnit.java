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

import cn.codethink.xiaoming.expression.api.APIFactory;

/**
 * <h1>文本格式单元</h1>
 *
 * @author Chuanwise
 */
public interface TextFormatUnit
    extends FormatUnit {
    
    /**
     * 构造文本格式单元
     *
     * @param spacesBeforeText 文本前的空格数
     * @param text             文本
     * @param spacesAfterText  文本后的空格数
     * @return 文本格式单元
     */
    static TextFormatUnit of(int spacesBeforeText, String text, int spacesAfterText) {
        return APIFactory.getInstance().getTextFormatUnit(spacesBeforeText, text, spacesAfterText);
    }
    
    /**
     * 构造文本格式单元
     *
     * @param text            文本
     * @param spacesAfterText 文本后的空格数
     * @return 文本格式单元
     */
    static TextFormatUnit of(String text, int spacesAfterText) {
        return of(0, text, spacesAfterText);
    }
    
    /**
     * 构造文本格式单元
     *
     * @param spacesBeforeText 文本前的空格数
     * @param text             文本
     * @return 文本格式单元
     */
    static TextFormatUnit of(int spacesBeforeText, String text) {
        return of(spacesBeforeText, text, 0);
    }
    
    /**
     * 构造文本格式单元
     *
     * @param text 文本
     * @return 文本格式单元
     */
    static TextFormatUnit of(String text) {
        return of(0, text, 0);
    }
    
    /**
     * 获取文本
     *
     * @return 文本
     */
    String getText();
    
    /**
     * 获取文本前的空格数
     *
     * @return 文本前的空格数
     */
    int getSpacesBeforeText();
    
    /**
     * 获取文本后的空格数
     *
     * @return 文本后的空格数
     */
    int getSpacesAfterText();
}