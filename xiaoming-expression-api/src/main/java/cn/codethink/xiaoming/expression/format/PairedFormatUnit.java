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
 * <h1>成对格式单元</h1>
 *
 * @author Chuanwise
 */
public interface PairedFormatUnit {
    
    /**
     * 构造成对格式单元
     *
     * @param leftUnit 左半部分
     * @param rightUnit 右半部分
     * @return 成对格式单元
     */
    static PairedFormatUnit of(FormatUnit leftUnit, FormatUnit emptyUnit, FormatUnit rightUnit) {
        return APIFactory.getInstance().getPairedFormatUnit(leftUnit, emptyUnit, rightUnit);
    }
    
    /**
     * 获取空格式单元时的格式
     *
     * @return 空格式单元时的格式
     */
    FormatUnit getEmptyUnit();
    
    /**
     * 获取左半部分
     *
     * @return 左半部分
     */
    FormatUnit getLeftUnit();
    
    /**
     * 获取右半部分
     *
     * @return 右半部分
     */
    FormatUnit getRightUnit();
}
