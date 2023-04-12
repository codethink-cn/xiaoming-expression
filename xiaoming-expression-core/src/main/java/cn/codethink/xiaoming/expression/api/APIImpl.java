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
import cn.codethink.xiaoming.expression.LiteralExpressionImpl;
import cn.codethink.xiaoming.expression.format.*;
import cn.codethink.xiaoming.expression.lang.*;

public class APIImpl
    implements API {
    
    @Override
    public Interpreter getInterpreter() {
        return new InterpreterImpl();
    }
    
    @Override
    public Interpreter getInterpreter(Interpreter interpreter) {
        return new InterpreterImpl(interpreter);
    }
    
    @Override
    public LiteralExpression getNullLiteralExpression() {
        return LiteralExpressionImpl.ofNull();
    }
    
    @Override
    public LiteralExpression getLiteralExpression(int value) {
        return LiteralExpressionImpl.of(value);
    }
    
    @Override
    public LiteralExpression getLiteralExpression(double value) {
        return LiteralExpressionImpl.of(value);
    }
    
    @Override
    public LiteralExpression getLiteralExpression(boolean value) {
        return LiteralExpressionImpl.of(value);
    }
    
    @Override
    public LiteralExpression getLiteralExpression(char value) {
        return LiteralExpressionImpl.of(value);
    }
    
    @Override
    public LiteralExpression getLiteralExpression(String value) {
        return LiteralExpressionImpl.of(value);
    }
    
    @Override
    public FormatConfiguration getFormattingConfiguration() {
        return FormatConfigurationImpl.getInstance();
    }
    
    @Override
    public FormatConfiguration.Builder getFormattingConfigurationBuilder() {
        return new FormatConfigurationImpl.BuilderImpl();
    }
    
    @Override
    public TextFormatUnit getTextFormatUnit(int spacesBeforeText, String text, int spacesAfterText) {
        return new TextFormatUnitImpl(spacesBeforeText, text, spacesAfterText);
    }
    
    @Override
    public SpacesFormatUnit getSpacesFormatUnit(int count) {
        return SpacesFormatUnitImpl.of(count);
    }
    
    @Override
    public PairedFormatUnit getPairedFormatUnit(FormatUnit leftUnit, FormatUnit emptyUnit, FormatUnit rightUnit) {
        return new PairedFormatUnitImpl(leftUnit, emptyUnit, rightUnit);
    }
    
    @Override
    public Formatter getFormatter(StringBuilder stringBuilder, FormatConfiguration configuration) {
        return new FormatterImpl(stringBuilder, configuration);
    }
    
    @Override
    public Formatter getFormatter(FormatConfiguration configuration) {
        return new FormatterImpl(configuration);
    }
    
    @Override
    public SpacesFormatUnit getEmptySpacesFormatUnit() {
        return SpacesFormatUnitImpl.empty();
    }
}
