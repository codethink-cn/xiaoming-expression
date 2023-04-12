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
         * @param countOfSpacesBeforeLeftParenthesis 左圆括号前的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesBeforeLeftParenthesis(int countOfSpacesBeforeLeftParenthesis);
    
        /**
         * 设置左圆括号后的空格数
         *
         * @param countOfSpacesAfterLeftParenthesis 左圆括号后的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesAfterLeftParenthesis(int countOfSpacesAfterLeftParenthesis);
    
        /**
         * 设置右圆括号前的空格数
         *
         * @param countOfSpacesBeforeRightParenthesis 右圆括号前的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesBeforeRightParenthesis(int countOfSpacesBeforeRightParenthesis);
    
        /**
         * 设置右圆括号后的空格数
         *
         * @param countOfSpacesAfterRightParenthesis 右圆括号后的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesAfterRightParenthesis(int countOfSpacesAfterRightParenthesis);
    
        /**
         * 设置空圆括号中的空格数
         *
         * @param countOfSpacesInEmptyParenthesis 空圆括号中的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesInEmptyParenthesis(int countOfSpacesInEmptyParenthesis);
    
        /**
         * 设置左方括号前的空格数
         *
         * @param countOfSpacesBeforeLeftBrackets 左方括号前的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesBeforeLeftBrackets(int countOfSpacesBeforeLeftBrackets);
    
        /**
         * 设置左方括号后的空格数
         *
         * @param countOfSpacesAfterLeftBrackets 左方括号后的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesAfterLeftBrackets(int countOfSpacesAfterLeftBrackets);
    
        /**
         * 设置右方括号前的空格数
         *
         * @param countOfSpacesBeforeRightBrackets 右方括号前的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesBeforeRightBrackets(int countOfSpacesBeforeRightBrackets);
    
        /**
         * 设置右方括号后的空格数
         *
         * @param countOfSpacesAfterRightBrackets 右方括号后的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesAfterRightBrackets(int countOfSpacesAfterRightBrackets);
    
        /**
         * 设置空方括号中的空格数
         *
         * @param countOfSpacesInEmptyBrackets 空方括号中的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesInEmptyBrackets(int countOfSpacesInEmptyBrackets);
    
        /**
         * 设置左花括号前的空格数
         *
         * @param countOfSpacesBeforeLeftBraces 左花括号前的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesBeforeLeftBraces(int countOfSpacesBeforeLeftBraces);
    
        /**
         * 设置左花括号后的空格数
         *
         * @param countOfSpacesAfterLeftBraces 左花括号后的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesAfterLeftBraces(int countOfSpacesAfterLeftBraces);
    
        /**
         * 设置右花括号前的空格数
         *
         * @param countOfSpacesBeforeRightBraces 右花括号前的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesBeforeRightBraces(int countOfSpacesBeforeRightBraces);
    
        /**
         * 设置右花括号后的空格数
         *
         * @param countOfSpacesAfterRightBraces 右花括号后的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesAfterRightBraces(int countOfSpacesAfterRightBraces);
    
        /**
         * 设置空花括号中的空格数
         *
         * @param countOfSpacesInEmptyBraces 空花括号中的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesInEmptyBraces(int countOfSpacesInEmptyBraces);
    
        /**
         * 设置逗号前的空格数
         *
         * @param countOfSpacesBeforeComma 逗号前的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesBeforeComma(int countOfSpacesBeforeComma);
    
        /**
         * 设置逗号后的空格数
         *
         * @param countOfSpacesAfterComma 逗号后的空格数
         * @return 格式配置构建器
         */
        Builder countOfSpacesAfterComma(int countOfSpacesAfterComma);
    
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
    int getCountOfSpacesBeforeLeftParenthesis();
    
    /**
     * 获取左圆括号后的空格数
     *
     * @return 左圆括号后的空格数
     */
    int getCountOfSpacesAfterLeftParenthesis();
    
    /**
     * 获取右圆括号前的空格数
     *
     * @return 右圆括号前的空格数
     */
    int getCountOfSpacesBeforeRightParenthesis();
    
    /**
     * 获取右圆括号后的空格数
     *
     * @return 右圆括号后的空格数
     */
    int getCountOfSpacesAfterRightParenthesis();
    
    /**
     * 获取空圆括号中的空格数
     *
     * @return 空圆括号中的空格数
     */
    int getCountOfSpacesInEmptyParenthesis();
    
    /**
     * 获取左方括号前的空格数
     *
     * @return 左方括号前的空格数
     */
    int getCountOfSpacesBeforeLeftBrackets();
    
    /**
     * 获取左方括号后的空格数
     *
     * @return 左方括号后的空格数
     */
    int getCountOfSpacesAfterLeftBrackets();
    
    /**
     * 获取右方括号前的空格数
     *
     * @return 右方括号前的空格数
     */
    int getCountOfSpacesBeforeRightBrackets();
    
    /**
     * 获取右方括号后的空格数
     *
     * @return 右方括号后的空格数
     */
    int getCountOfSpacesAfterRightBrackets();
    
    /**
     * 获取空方括号中的空格数
     *
     * @return 空方括号中的空格数
     */
    int getCountOfSpacesInEmptyBrackets();
    
    /**
     * 获取左花括号前的空格数
     *
     * @return 左花括号前的空格数
     */
    int getCountOfSpacesBeforeLeftBraces();
    
    /**
     * 获取左花括号后的空格数
     *
     * @return 左花括号后的空格数
     */
    int getCountOfSpacesAfterLeftBraces();
    
    /**
     * 获取右花括号前的空格数
     *
     * @return 右花括号前的空格数
     */
    int getCountOfSpacesBeforeRightBraces();
    
    /**
     * 获取右花括号后的空格数
     *
     * @return 右花括号后的空格数
     */
    int getCountOfSpacesAfterRightBraces();
    
    /**
     * 获取空花括号中的空格数
     *
     * @return 空花括号中的空格数
     */
    int getCountOfSpacesInEmptyBraces();
    
    /**
     * 获取逗号前的空格数
     *
     * @return 逗号前的空格数
     */
    int getCountOfSpacesBeforeComma();
    
    /**
     * 获取逗号后的空格数
     *
     * @return 逗号后的空格数
     */
    int getCountOfSpacesAfterComma();
}
