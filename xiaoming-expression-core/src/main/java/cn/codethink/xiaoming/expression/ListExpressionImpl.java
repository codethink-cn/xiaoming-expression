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

import com.google.common.base.Preconditions;

import java.util.*;

public class ListExpressionImpl
    implements ListExpression {
    
    private final List<Expression> expressions;
    
    public ListExpressionImpl(List<Expression> expressions) {
        Preconditions.checkNotNull(expressions, "Expressions are null!");
        
        this.expressions = expressions;
    }
    
    @Override
    public List<Expression> getExpressions() {
        return expressions;
    }
    
    @Override
    public List<Object> interpret() {
        if (expressions.isEmpty()) {
            return Collections.emptyList();
        }
        final List<Object> set = new ArrayList<>(expressions.size());
        for (Expression expression : expressions) {
            set.add(expression.interpret());
        }
        return Collections.unmodifiableList(set);
    }
    
    @Override
    public Class<?> getResultClass() {
        return List.class;
    }
    
    @Override
    public Iterator<Expression> iterator() {
        return expressions.iterator();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ListExpressionImpl that = (ListExpressionImpl) o;
        return Objects.equals(expressions, that.expressions);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(expressions);
    }
}
