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

package cn.codethink.xiaoming.expression.acl;

public class JavaSymbol extends java_cup.runtime.Symbol {
    private final int line;
    private final int column;
    
    public JavaSymbol(int type, int line, int column) {
        this(type, line, column, -1, -1, null);
    }
    
    public JavaSymbol(int type, int line, int column, Object value) {
        this(type, line, column, -1, -1, value);
    }
    
    public JavaSymbol(int type, int line, int column, int left, int right, Object value) {
        super(type, left, right, value);
        this.line = line;
        this.column = column;
    }
    
    public int getLine() {
        return line;
    }
    
    public int getColumn() {
        return column;
    }
    
    public String toString() {
        return "line "
            + line
            + ", column "
            + column
            + ", sym: "
            + sym
            + (value == null ? "" : (", value: '" + value + "'"));
    }
}