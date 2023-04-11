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
     * 获取全局默认可配置解释器
     *
     * @return 可配置解释器
     */
    static ConfigurableInterpreter getInstance() {
        return APIFactory.getInstance().getConfigurableInterpreter();
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
    
    /**
     * 通过反射注册类型
     *
     * @param objects 对象
     */
    void registerType(Object... objects);
    
    /**
     * 通过反射注册多个类型
     *
     * @param object 对象
     */
    void registerTypes(Object object);
}
