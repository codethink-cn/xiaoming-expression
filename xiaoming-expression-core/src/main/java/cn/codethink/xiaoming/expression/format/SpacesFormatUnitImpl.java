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

import com.google.common.base.Preconditions;

import java.util.Objects;

public class SpacesFormatUnitImpl
    implements SpacesFormatUnit {
    
    private static final SpacesFormatUnitImpl[] INSTANCES = new SpacesFormatUnitImpl[10];
    
    private final int count;
    
    private Integer hashCodeCache;
    
    private SpacesFormatUnitImpl(int count) {
        this.count = count;
    }
    
    public static SpacesFormatUnitImpl of(int count) {
        Preconditions.checkArgument(count >= 0, "Count must be greater than or equals to 0!");
    
        if (count < INSTANCES.length) {
            if (INSTANCES[count] == null) {
                INSTANCES[count] = new SpacesFormatUnitImpl(count);
            }
            return INSTANCES[count];
        }
        return new SpacesFormatUnitImpl(count);
    }
    
    public static SpacesFormatUnitImpl empty() {
        if (INSTANCES[0] == null) {
            INSTANCES[0] = new SpacesFormatUnitImpl(0);
        }
        return INSTANCES[0];
    }
    
    @Override
    public int getCount() {
        return count;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpacesFormatUnitImpl that = (SpacesFormatUnitImpl) o;
        return count == that.count;
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(count);
        }
        return hashCodeCache;
    }
    
    @Override
    public String toString() {
        return "SpacesFormatUnit{" +
            "count=" + count +
            '}';
    }
}
