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

package cn.codethink.xiaoming.expression.lang;

import com.google.common.base.Preconditions;

import java.util.Set;

public class AnalyzingContextImpl
    implements AnalyzingContext {
    
    private final Object subject;
    private final Set<Object> properties;
    private final Interpreter interpreter;
    
    public AnalyzingContextImpl(Object subject, Set<Object> properties, Interpreter interpreter) {
        Preconditions.checkNotNull(subject, "Subject is null!");
        Preconditions.checkNotNull(properties, "Properties are null!");
        Preconditions.checkNotNull(interpreter, "Interpreter is null!");
        
        this.subject = subject;
        this.properties = properties;
        this.interpreter = interpreter;
    }
    
    @Override
    public Object getSubject() {
        return subject;
    }
    
    @Override
    public Set<Object> getProperties() {
        return properties;
    }
    
    @Override
    public Interpreter getInterpreter() {
        return interpreter;
    }
}
