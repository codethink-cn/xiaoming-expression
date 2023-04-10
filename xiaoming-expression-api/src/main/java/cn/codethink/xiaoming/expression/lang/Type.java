package cn.codethink.xiaoming.expression.lang;

import java.util.List;
import java.util.Set;

/**
 * <h1>类型</h1>
 *
 * @author Chuanwise
 */
public interface Type {
    
    /**
     * 获取构造函数
     *
     * @return 构造函数
     */
    Set<Constructor> getConstructors();
    
    /**
     * 获取构造函数
     *
     * @param types 构造函数参数
     * @return 构造函数或 null
     */
    Constructor getConstructor(List<Type> types);
    
    /**
     * 获取构造函数
     *
     * @param types 构造函数参数
     * @return 构造函数
     */
    Constructor getConstructorOrFail(List<Type> types);
    
    /**
     * 获取 Java 类型
     *
     * @return Java 类型
     */
    Class<?> getJavaClass();
    
    /**
     * 判断另一类型能否赋值为当前类型
     *
     * @param type 类型
     * @return 另一类型能否赋值为当前类型
     */
    boolean isAssignableFrom(Type type);
    
    /**
     * 获取类型名
     *
     * @return 类型名
     */
    String getName();
}
