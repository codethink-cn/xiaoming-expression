package cn.codethink.xiaoming.expression.constructor;

import cn.codethink.xiaoming.expression.type.Method;
import cn.codethink.xiaoming.expression.type.Type;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * <h1>构造函数</h1>
 *
 * @author Chuanwise
 */
public interface Constructor
    extends Method {
    
    /**
     * 获取类型
     *
     * @return 类型
     */
    Type getType();
    
    /**
     * 调用构造函数
     *
     * @param arguments 参数
     * @return 对象
     * @throws ConstructingException 构造过程中抛出的异常
     */
    Object construct(List<Object> arguments) throws ConstructingException;
}
