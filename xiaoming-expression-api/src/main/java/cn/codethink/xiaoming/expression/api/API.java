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

package cn.codethink.xiaoming.expression.api;

import cn.codethink.xiaoming.expression.LiteralExpression;
import cn.codethink.xiaoming.expression.format.*;
import cn.codethink.xiaoming.expression.lang.Interpreter;

/**
 * <h1>表达式 API</h1>
 *
 * <p>表达式 API 是 xiaoming-expression-api 调用 xiaoming-expression-core 的桥梁。</p>
 *
 * @author Chuanwise
 */
public interface API {
    
    Interpreter getInterpreter();
    Interpreter getInterpreter(Interpreter interpreter);
    
    LiteralExpression getNullLiteralExpression();
    LiteralExpression getLiteralExpression(int value);
    LiteralExpression getLiteralExpression(double value);
    LiteralExpression getLiteralExpression(boolean value);
    LiteralExpression getLiteralExpression(char value);
    LiteralExpression getLiteralExpression(String value);
    
    FormatConfiguration getFormattingConfiguration();
    FormatConfiguration.Builder getFormattingConfigurationBuilder();
    
    TextFormatUnit getTextFormatUnit(int spacesBeforeText, String text, int spacesAfterText);
    SpacesFormatUnit getSpacesFormatUnit(int count);
    SpacesFormatUnit getEmptySpacesFormatUnit();
    PairedFormatUnit getPairedFormatUnit(FormatUnit leftUnit, FormatUnit emptyUnit, FormatUnit rightUnit);
    
    Formatter getFormatter(StringBuilder stringBuilder, FormatConfiguration configuration);
    Formatter getFormatter(FormatConfiguration configuration);
}
