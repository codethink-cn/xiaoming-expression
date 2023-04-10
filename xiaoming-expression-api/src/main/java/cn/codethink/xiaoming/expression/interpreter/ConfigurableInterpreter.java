package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.api.APIFactory;
import cn.codethink.xiaoming.expression.type.Type;

/**
 * <h1>可配置解释器</h1>
 *
 * @author Chuanwise
 */
public interface ConfigurableInterpreter
    extends Interpreter {
    
    /**
     * 构造可配置解释器
     *
     * @param parent 父解释器
     * @return 可配置解释器
     */
    static ConfigurableInterpreter newInstance(Interpreter parent) {
        return APIFactory.getInstance().getConfigurableInterpreter(parent);
    }
    
    /**
     * 注册类型
     *
     * @param type 类型
     */
    void registerType(Type type);
    
    /**
     * 通过反射注册类型
     *
     * @param object 对象
     */
    void registerType(Object object);
}
