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

public class PairedFormatUnitImpl
    implements PairedFormatUnit {
    
    private final FormatUnit leftUnit;
    private final FormatUnit rightUnit;
    private final FormatUnit emptyUnit;
    
    private Integer hashCodeCache;
    
    public PairedFormatUnitImpl(FormatUnit leftUnit, FormatUnit emptyUnit, FormatUnit rightUnit) {
        Preconditions.checkNotNull(leftUnit, "Left unit is null!");
        Preconditions.checkNotNull(rightUnit, "Right unit is null!");
        Preconditions.checkNotNull(emptyUnit, "Empty unit is null!");
        
        this.leftUnit = leftUnit;
        this.rightUnit = rightUnit;
        this.emptyUnit = emptyUnit;
    }
    
    @Override
    public FormatUnit getEmptyUnit() {
        return emptyUnit;
    }
    
    @Override
    public FormatUnit getLeftUnit() {
        return leftUnit;
    }
    
    @Override
    public FormatUnit getRightUnit() {
        return rightUnit;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PairedFormatUnitImpl that = (PairedFormatUnitImpl) o;
        return Objects.equals(leftUnit, that.leftUnit) && Objects.equals(rightUnit, that.rightUnit);
    }
    
    @Override
    public int hashCode() {
        if (hashCodeCache == null) {
            hashCodeCache = Objects.hash(leftUnit, rightUnit);
        }
        return hashCodeCache;
    }
    
    @Override
    public String toString() {
        return "PairedFormatUnitImpl{" +
            "leftUnit=" + leftUnit +
            ", rightUnit=" + rightUnit +
            ", hashCodeCache=" + hashCodeCache +
            '}';
    }
}
