package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.api.APIFactory;

/**
 * <h1>分析配置</h1>
 *
 * @author Chuanwise
 */
public interface AnalyzationConfiguration
    extends Cloneable {
    
    /**
     * 分析配置构建器
     *
     * @author Chuanwise
     */
    interface Builder {
    
        /**
         * 设置是否展平数据
         *
         * @param flatData 是否展平数据
         * @return 分析配置构建器
         */
        Builder flatData(boolean flatData);
    
        /**
         * 构造分析配置
         *
         * @return 分析配置
         */
        AnalyzationConfiguration build();
    }
    
    /**
     * 获取分析配置构建器
     *
     * @return 分析配置构建器
     */
    static Builder builder() {
        return APIFactory.getInstance().getAnalyzationConfigurationBuilder();
    }
    
    /**
     * 获取分析配置
     *
     * @return 分析配置
     */
    static AnalyzationConfiguration getInstance() {
        return APIFactory.getInstance().getAnalyzationConfiguration();
    }
    
    /**
     * 是否展平数据
     *
     * @return 是否展平数据
     */
    boolean isFlatData();
    
    /**
     * 复制配置对象
     *
     * @return 配置对象
     */
    AnalyzationConfiguration clone();
}
