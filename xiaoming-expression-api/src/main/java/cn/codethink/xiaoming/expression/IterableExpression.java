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

import java.util.List;

/**
 * <h1>可迭代表达式</h1>
 *
 * @author Chuanwise
 */
public interface IterableExpression
    extends Expression, Iterable<Expression> {
    
    /**
     * 获取表达式列表
     *
     * @return 表达式列表
     */
    List<Expression> getExpressions();
    
    @Override
    Iterable<Object> interpret();
}
