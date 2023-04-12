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

package cn.codethink.xiaoming.expression;

import cn.codethink.xiaoming.expression.api.APIFactory;

/**
 * <h1>字面量表达式</h1>
 *
 * @author Chuanwise
 */
public interface LiteralExpression
    extends Expression {
    
    /**
     * 获取代表 null 的字面量表达式
     *
     * @return 字面量表达式
     */
    static LiteralExpression ofNull() {
        return APIFactory.getInstance().getNullLiteralExpression();
    }
    
    /**
     * 构造字面量表达式
     *
     * @param value 值
     * @return 字面量表达式
     */
    static LiteralExpression of(int value) {
        return APIFactory.getInstance().getLiteralExpression(value);
    }
    
    /**
     * 构造字面量表达式
     *
     * @param value 值
     * @return 字面量表达式
     */
    static LiteralExpression of(double value) {
        return APIFactory.getInstance().getLiteralExpression(value);
    }
    
    /**
     * 构造字面量表达式
     *
     * @param value 值
     * @return 字面量表达式
     */
    static LiteralExpression of(char value) {
        return APIFactory.getInstance().getLiteralExpression(value);
    }
    
    /**
     * 构造字面量表达式
     *
     * @param value 值
     * @return 字面量表达式
     */
    static LiteralExpression of(boolean value) {
        return APIFactory.getInstance().getLiteralExpression(value);
    }
    
    /**
     * 构造字面量表达式
     *
     * @param value 值
     * @return 字面量表达式
     */
    static LiteralExpression of(String value) {
        return APIFactory.getInstance().getLiteralExpression(value);
    }
    
    /**
     * 获取常量
     *
     * @return 常量
     */
    Object getValue();
}