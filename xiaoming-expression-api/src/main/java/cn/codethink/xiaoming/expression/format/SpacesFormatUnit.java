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
 * <h1>空格格式单元</h1>
 *
 * @author Chuanwise
 */
public interface SpacesFormatUnit
    extends FormatUnit {
    
    /**
     * 构造空格格式单元
     *
     * @param count 空格数
     * @return 空格格式单元
     */
    static SpacesFormatUnit of(int count) {
        return APIFactory.getInstance().getSpacesFormatUnit(count);
    }
    
    /**
     * 构造空格式单元
     *
     * @return 空格式单元
     */
    static SpacesFormatUnit empty() {
        return APIFactory.getInstance().getEmptySpacesFormatUnit();
    }
    
    /**
     * 获取空格数
     *
     * @return 空格数
     */
    int getCount();
    
    /**
     * 直接转化为字符串
     *
     * @return 字符串
     */
    @Override
    String toString();
}
