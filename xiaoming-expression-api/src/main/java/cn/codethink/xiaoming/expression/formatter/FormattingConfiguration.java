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

import cn.codethink.xiaoming.expression.api.APIFactory;

/**
 * <h1>格式配置</h1>
 *
 * @author Chuanwise
 */
public interface FormattingConfiguration
    extends Cloneable {
    
    /**
     * 格式配置构建器
     *
     * @author Chuanwise
     */
    interface Builder {
    
        /**
         * 设置是否最小化空格
         *
         * @param minimizeSpaces 是否最小化空格
         * @return 格式配置构建器
         */
        Builder minimizeSpaces(boolean minimizeSpaces);
    
        /**
         * 设置左圆括号前的空格数
         *
         * @param spacesBeforeLeftParenthesis 左圆括号前的空格数
         * @return 格式配置构建器
         */
        Builder spacesBeforeLeftParenthesis(int spacesBeforeLeftParenthesis);
    
        /**
         * 设置左圆括号后的空格数
         *
         * @param spacesAfterLeftParenthesis 左圆括号后的空格数
         * @return 格式配置构建器
         */
        Builder spacesAfterLeftParenthesis(int spacesAfterLeftParenthesis);
    
        /**
         * 设置右圆括号前的空格数
         *
         * @param spacesBeforeRightParenthesis 右圆括号前的空格数
         * @return 格式配置构建器
         */
        Builder spacesBeforeRightParenthesis(int spacesBeforeRightParenthesis);
    
        /**
         * 设置右圆括号后的空格数
         *
         * @param spacesAfterRightParenthesis 右圆括号后的空格数
         * @return 格式配置构建器
         */
        Builder spacesAfterRightParenthesis(int spacesAfterRightParenthesis);
    
        /**
         * 设置空圆括号中的空格数
         *
         * @param spacesInEmptyParenthesis 空圆括号中的空格数
         * @return 格式配置构建器
         */
        Builder spacesInEmptyParenthesis(int spacesInEmptyParenthesis);
    
        /**
         * 设置左方括号前的空格数
         *
         * @param spacesBeforeLeftBrackets 左方括号前的空格数
         * @return 格式配置构建器
         */
        Builder spacesBeforeLeftBrackets(int spacesBeforeLeftBrackets);
    
        /**
         * 设置左方括号后的空格数
         *
         * @param spacesAfterLeftBrackets 左方括号后的空格数
         * @return 格式配置构建器
         */
        Builder spacesAfterLeftBrackets(int spacesAfterLeftBrackets);
    
        /**
         * 设置右方括号前的空格数
         *
         * @param spacesBeforeRightBrackets 右方括号前的空格数
         * @return 格式配置构建器
         */
        Builder spacesBeforeRightBrackets(int spacesBeforeRightBrackets);
    
        /**
         * 设置右方括号后的空格数
         *
         * @param spacesAfterRightBrackets 右方括号后的空格数
         * @return 格式配置构建器
         */
        Builder spacesAfterRightBrackets(int spacesAfterRightBrackets);
    
        /**
         * 设置空方括号中的空格数
         *
         * @param spacesInEmptyBrackets 空方括号中的空格数
         * @return 格式配置构建器
         */
        Builder spacesInEmptyBrackets(int spacesInEmptyBrackets);
    
        /**
         * 设置左花括号前的空格数
         *
         * @param spacesBeforeLeftBraces 左花括号前的空格数
         * @return 格式配置构建器
         */
        Builder spacesBeforeLeftBraces(int spacesBeforeLeftBraces);
    
        /**
         * 设置左花括号后的空格数
         *
         * @param spacesAfterLeftBraces 左花括号后的空格数
         * @return 格式配置构建器
         */
        Builder spacesAfterLeftBraces(int spacesAfterLeftBraces);
    
        /**
         * 设置右花括号前的空格数
         *
         * @param spacesBeforeRightBraces 右花括号前的空格数
         * @return 格式配置构建器
         */
        Builder spacesBeforeRightBraces(int spacesBeforeRightBraces);
    
        /**
         * 设置右花括号后的空格数
         *
         * @param spacesAfterRightBraces 右花括号后的空格数
         * @return 格式配置构建器
         */
        Builder spacesAfterRightBraces(int spacesAfterRightBraces);
    
        /**
         * 设置空花括号中的空格数
         *
         * @param spacesInEmptyBraces 空花括号中的空格数
         * @return 格式配置构建器
         */
        Builder spacesInEmptyBraces(int spacesInEmptyBraces);
    
        /**
         * 设置逗号前的空格数
         *
         * @param spacesBeforeComma 逗号前的空格数
         * @return 格式配置构建器
         */
        Builder spacesBeforeComma(int spacesBeforeComma);
    
        /**
         * 设置逗号后的空格数
         *
         * @param spacesAfterComma 逗号后的空格数
         * @return 格式配置构建器
         */
        Builder spacesAfterComma(int spacesAfterComma);
    
        /**
         * 构造格式配置
         *
         * @return 格式配置
         */
        FormattingConfiguration build();
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
    static FormattingConfiguration getInstance() {
        return APIFactory.getInstance().getFormattingConfiguration();
    }
    
    /**
     * 是否最小化空格
     *
     * @return 最小化空格
     */
    boolean isMinimizeSpaces();
    
    /**
     * 获取左圆括号前的空格数
     *
     * @return 左圆括号前的空格数
     */
    int getSpacesBeforeLeftParenthesis();
    
    /**
     * 获取左圆括号后的空格数
     *
     * @return 左圆括号后的空格数
     */
    int getSpacesAfterLeftParenthesis();
    
    /**
     * 获取右圆括号前的空格数
     *
     * @return 右圆括号前的空格数
     */
    int getSpacesBeforeRightParenthesis();
    
    /**
     * 获取右圆括号后的空格数
     *
     * @return 右圆括号后的空格数
     */
    int getSpacesAfterRightParenthesis();
    
    /**
     * 获取空圆括号中的空格数
     *
     * @return 空圆括号中的空格数
     */
    int getSpacesInEmptyParenthesis();
    
    /**
     * 获取左方括号前的空格数
     *
     * @return 左方括号前的空格数
     */
    int getSpacesBeforeLeftBrackets();
    
    /**
     * 获取左方括号后的空格数
     *
     * @return 左方括号后的空格数
     */
    int getSpacesAfterLeftBrackets();
    
    /**
     * 获取右方括号前的空格数
     *
     * @return 右方括号前的空格数
     */
    int getSpacesBeforeRightBrackets();
    
    /**
     * 获取右方括号后的空格数
     *
     * @return 右方括号后的空格数
     */
    int getSpacesAfterRightBrackets();
    
    /**
     * 获取空方括号中的空格数
     *
     * @return 空方括号中的空格数
     */
    int getSpacesInEmptyBrackets();
    
    /**
     * 获取左花括号前的空格数
     *
     * @return 左花括号前的空格数
     */
    int getSpacesBeforeLeftBraces();
    
    /**
     * 获取左花括号后的空格数
     *
     * @return 左花括号后的空格数
     */
    int getSpacesAfterLeftBraces();
    
    /**
     * 获取右花括号前的空格数
     *
     * @return 右花括号前的空格数
     */
    int getSpacesBeforeRightBraces();
    
    /**
     * 获取右花括号后的空格数
     *
     * @return 右花括号后的空格数
     */
    int getSpacesAfterRightBraces();
    
    /**
     * 获取空花括号中的空格数
     *
     * @return 空花括号中的空格数
     */
    int getSpacesInEmptyBraces();
    
    /**
     * 获取逗号前的空格数
     *
     * @return 逗号前的空格数
     */
    int getSpacesBeforeComma();
    
    /**
     * 获取逗号后的空格数
     *
     * @return 逗号后的空格数
     */
    int getSpacesAfterComma();
}
