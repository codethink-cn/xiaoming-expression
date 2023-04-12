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

package cn.codethink.xiaoming.expression;

import cn.codethink.xiaoming.expression.lang.Function;

import java.util.List;

/**
 * <h1>构造表达式</h1>
 *
 * @author Chuanwise
 */
public interface InvokeExpression
    extends Expression {
    
    /**
     * 获取构造函数
     *
     * @return 构造函数
     */
    Function getConstructor();
    
    /**
     * 获取参数表达式
     *
     * @return 参数表达式
     */
    List<Expression> getArguments();
}
