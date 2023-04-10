package cn.codethink.xiaoming.expression.formatter;

import cn.codethink.xiaoming.expression.api.APIFactory;

/**
 * <h1>格式配置</h1>
 *
 * @author Chuanwise
 */
public interface FormattingConfiguration
    extends Cloneable {
    
    /**
     * 格式配置构建器
     *
     * @author Chuanwise
     */
    interface Builder {
    
        /**
         * 设置是否在逗号后插入一个空格
         *
         * @param insertSpaceAfterComma 是否在逗号后插入一个空格
         * @return 格式配置
         */
        Builder insertSpaceAfterComma(boolean insertSpaceAfterComma);
    
        /**
         * 设置是否在逗号前插入一个空格
         *
         * @param insertSpaceBeforeComma 是否在逗号前插入一个空格
         * @return 格式配置
         */
        Builder insertSpaceBeforeComma(boolean insertSpaceBeforeComma);
    
        /**
         * 设置是否在圆括号前插入一个空格
         *
         * @param insertSpaceBeforeParenthesis 是否在圆括号前插入一个空格
         * @return 格式配置
         */
        Builder insertSpaceBeforeParenthesis(boolean insertSpaceBeforeParenthesis);
        
        /**
         * 设置是否在圆括号后插入一个空格
         *
         * @param insertSpaceAfterParenthesis 是否在圆括号后插入一个空格
         * @return 格式配置
         */
        Builder insertSpaceAfterParenthesis(boolean insertSpaceAfterParenthesis);
        
        /**
         * 设置是否在方括号前插入一个空格
         *
         * @param insertSpaceBeforeBrackets 是否在方括号前插入一个空格
         * @return 格式配置
         */
        Builder insertSpaceBeforeBrackets(boolean insertSpaceBeforeBrackets);
        
        /**
         * 设置是否在方括号后插入一个空格
         *
         * @param insertSpaceAfterBrackets 是否在方括号后插入一个空格
         * @return 格式配置
         */
        Builder insertSpaceAfterBrackets(boolean insertSpaceAfterBrackets);
        
        /**
         * 设置是否在花括号前插入一个空格
         *
         * @param insertSpaceBeforeBraces 是否在花括号前插入一个空格
         * @return 格式配置
         */
        Builder insertSpaceBeforeBraces(boolean insertSpaceBeforeBraces);
        
        /**
         * 设置是否在花括号后插入一个空格
         *
         * @param insertSpaceAfterBraces 是否在花括号后插入一个空格
         * @return 格式配置
         */
        Builder insertSpaceAfterBraces(boolean insertSpaceAfterBraces);
    
        /**
         * 构造格式配置
         *
         * @return 格式配置
         */
        FormattingConfiguration build();
    }
    
    /**
     * 获取格式配置构建器
     *
     * @return 格式配置构建器
     */
    static Builder builder() {
        return APIFactory.getInstance().getFormattingConfigurationBuilder();
    }
    
    /**
     * 获取格式配置
     *
     * @return 格式配置
     */
    static FormattingConfiguration getInstance() {
        return APIFactory.getInstance().getFormattingConfiguration();
    }
    
    /**
     * 是否在逗号后插入一个空格
     *
     * @return 在逗号后插入一个空格
     */
    boolean isInsertSpaceAfterComma();
    
    /**
     * 是否在圆括号前插入空格
     *
     * @return 在圆括号前插入空格
     */
    boolean isInsertSpaceBeforeParenthesis();
    
    /**
     * 是否在圆括号后插入空格
     *
     * @return 在圆括号后插入空格
     */
    boolean isInsertSpaceAfterParenthesis();
    
    /**
     * 是否在方括号前插入空格
     *
     * @return 在方括号前插入空格
     */
    boolean isInsertSpaceBeforeBrackets();
    
    /**
     * 是否在方括号后插入空格
     *
     * @return 在方括号后插入空格
     */
    boolean isInsertSpaceAfterBrackets();
    
    /**
     * 是否在花括号前插入空格
     *
     * @return 在花括号前插入空格
     */
    boolean isInsertSpaceBeforeBraces();
    
    /**
     * 是否在花括号后插入空格
     *
     * @return 在花括号后插入空格
     */
    boolean isInsertSpaceAfterBraces();
    
    /**
     * 是否在逗号前插入一个空格
     *
     * @return 在逗号前插入一个空格
     */
    boolean isInsertSpaceBeforeComma();
    
    /**
     * 复制配置对象
     *
     * @return 配置对象
     */
    FormattingConfiguration clone();
}

