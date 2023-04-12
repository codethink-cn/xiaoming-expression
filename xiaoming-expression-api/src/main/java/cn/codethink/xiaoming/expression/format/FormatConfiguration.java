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
 * <h1>格式配置</h1>
 *
 * @author Chuanwise
 */
public interface FormatConfiguration
    extends Cloneable {
    
    /**
     * 格式配置构建器
     *
     * @author Chuanwise
     */
    interface Builder {
    
        /**
         * 设置压缩空格
         *
         * @param compressSpaces 压缩空格
         * @return 格式配置构建器
         */
        Builder compressSpaces(boolean compressSpaces);
    
        /**
         * 设置最大空格个数
         *
         * @param maxSpaces 最大空格个数
         * @return 格式配置构建器
         */
        Builder maxSpaces(int maxSpaces);
    
        /**
         * 设置逗号格式单元
         *
         * @param comma 逗号格式单元
         * @return 格式配置构建器
         */
        Builder comma(FormatUnit comma);
    
        /**
         * 设置圆括号格式单元
         *
         * @param parenthesis 圆括号格式单元
         * @return 格式配置构建器
         */
        Builder parenthesis(PairedFormatUnit parenthesis);
    
        /**
         * 设置方括号格式单元
         *
         * @param brackets 方括号格式单元
         * @return 格式配置构建器
         */
        Builder brackets(PairedFormatUnit brackets);
    
        /**
         * 设置花括号格式单元
         *
         * @param braces 花括号格式单元
         * @return 格式配置构建器
         */
        Builder braces(PairedFormatUnit braces);
    
        /**
         * 构造格式配置
         *
         * @return 格式配置
         */
        FormatConfiguration build();
    }
    
    /**
     * 获取格式配置构建器
     *
     * @return 格式配置构建器
     */
    static Builder builder() {
        return APIFactory.getInstance().getFormattingConfigurationBuilder();
    }
    
    /**
     * 获取格式配置
     *
     * @return 格式配置
     */
    static FormatConfiguration getInstance() {
        return APIFactory.getInstance().getFormattingConfiguration();
    }
    
    /**
     * 获取最大空格个数
     *
     * @return 最大空格个数
     */
    int getMaxSpaces();
    
    /**
     * 是否压缩空格
     *
     * @return 压缩空格
     */
    boolean isCompressSpaces();
    
    /**
     * 获取逗号格式单元
     *
     * @return 逗号格式单元
     */
    FormatUnit getComma();
    
    /**
     * 获取圆括号格式单元
     *
     * @return 圆括号格式单元
     */
    PairedFormatUnit getParenthesis();
    
    /**
     * 获取方括号格式单元
     *
     * @return 方括号格式单元
     */
    PairedFormatUnit getBrackets();
    
    /**
     * 获取花括号格式单元
     *
     * @return 花括号格式单元
     */
    PairedFormatUnit getBraces();
}
