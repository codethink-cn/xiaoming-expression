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

import java.util.List;

/**
 * <h1>函数</h1>
 *
 * @author Chuanwise
 */
public interface Function {
    
    /**
     * 调用函数
     *
     * @param arguments 参数
     * @return 对象
     */
    Object invoke(List<Object> arguments);
    
    /**
     * 获取方法名
     *
     * @return 方法名
     */
    String getName();
    
    /**
     * 获取返回类型
     *
     * @return 返回类型
     */
    Class<?> getReturnClass();
    
    /**
     * 获取参数类型
     *
     * @return 参数类型
     */
    List<Class<?>> getParametersClasses();
}
