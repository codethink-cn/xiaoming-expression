package cn.codethink.xiaoming.expression.analyzer;

import cn.codethink.xiaoming.expression.api.APIFactory;

/**
 * <h1>分析配置</h1>
 *
 * @author Chuanwise
 */
public interface AnalyzingConfiguration
    extends Cloneable {
    
    /**
     * 分析配置构建器
     *
     * @author Chuanwise
     */
    interface Builder {
    
        /**
         * 构造分析配置
         *
         * @return 分析配置
         */
        AnalyzingConfiguration build();
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
    static AnalyzingConfiguration getInstance() {
        return APIFactory.getInstance().getAnalyzationConfiguration();
    }
    
    /**
     * 复制配置对象
     *
     * @return 配置对象
     */
    AnalyzingConfiguration clone();
}
