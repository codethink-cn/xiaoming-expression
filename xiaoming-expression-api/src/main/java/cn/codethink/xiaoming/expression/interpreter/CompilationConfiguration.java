package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.api.APIFactory;

/**
 * <h1>编译配置</h1>
 *
 * @author Chuanwise
 */
public interface CompilationConfiguration
    extends Cloneable {
    
    /**
     * 编译配置构建器
     *
     * @author Chuanwise
     */
    interface Builder {
    
        /**
         * 设置严格模式
         *
         * @param strict 严格模式
         * @return 编译配置构建器
         */
        Builder strict(boolean strict);
    
        /**
         * 构造编译配置
         *
         * @return 编译配置
         */
        CompilationConfiguration build();
    }
    
    /**
     * 获取编译配置构建器
     *
     * @return 编译配置构建器
     */
    static Builder builder() {
        return APIFactory.getInstance().getCompileConfigurationBuilder();
    }
    
    /**
     * 获取编译配置
     *
     * @return 编译配置
     */
    static CompilationConfiguration getInstance() {
        return APIFactory.getInstance().getCompileConfiguration();
    }
    
    /**
     * 是否开启严格模式
     *
     * @return 开启严格模式
     */
    boolean isStrict();
    
    /**
     * 复制配置对象
     *
     * @return 配置对象
     */
    CompilationConfiguration clone();
}
