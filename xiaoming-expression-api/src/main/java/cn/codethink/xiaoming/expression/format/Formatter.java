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
 * <h1>格式化器</h1>
 *
 * @author Chuanwise
 */
public interface Formatter {
    
    /**
     * 构造格式化器
     *
     * @param stringBuilder 字符串构建器
     * @param configuration 格式配置
     * @return 格式化器
     */
    static Formatter newInstance(StringBuilder stringBuilder, FormatConfiguration configuration) {
        return APIFactory.getInstance().getFormatter(stringBuilder, configuration);
    }
    
    /**
     * 构造格式化器
     *
     * @param configuration 格式配置
     * @return 格式化器
     */
    static Formatter newInstance(FormatConfiguration configuration) {
        return APIFactory.getInstance().getFormatter(configuration);
    }
    
    /**
     * 获取格式化配置
     *
     * @return 格式化配置
     */
    FormatConfiguration getConfiguration();
    
    /**
     * 追加格式化元素
     *
     * @param unit 格式化元素
     * @return 格式化器
     */
    Formatter plus(FormatUnit unit);
    
    /**
     * 追加文本
     *
     * @param text 文本
     * @return 格式化器
     */
    Formatter plus(String text);
    
    /**
     * 获取格式化结果
     *
     * @return 格式化结果
     */
    @Override
    String toString();
}
