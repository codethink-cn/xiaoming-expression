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

package cn.codethink.xiaoming.expression.lang;

import cn.codethink.xiaoming.expression.Expression;

import java.net.URL;
import java.util.Collections;
import java.util.Set;

/**
 * <h1>分析器</h1>
 *
 * <p>分析是将对象转化为抽象语法树 {@link Expression} 的过程，常用于存储对象。
 * 例如，下面的代码通过编译和分析两种方式获取调用 {@link URL} 构造函数的表达式：</p>
 *
 * <p><pre>{@code
 * final Expression e1 = interpreter.compile("URL(\"https://docs.xiaoming.codethink.cn\")");
 * final Expression e2 = interpreter.analyze(new URL("https://docs.xiaoming.codethink.cn"));
 *
 * Assertions.assertEquals(e1, e2);
 * }</pre></p>
 * 
 * @author Chuanwise
 */
public interface Analyzer {
    
    /**
     * 分析对象，构造表达式
     *
     * @param context 分析环境
     * @return 表达式
     */
    Expression analyze(AnalyzingContext context);
    
    /**
     * 获取分析主体类型
     *
     * @return 分析主体类型
     */
    Class<?> getSubjectClass();
}