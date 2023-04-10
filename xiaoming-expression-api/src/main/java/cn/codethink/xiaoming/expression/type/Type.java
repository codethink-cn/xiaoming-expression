package cn.codethink.xiaoming.expression.type;

import cn.codethink.xiaoming.expression.analyzer.Analyzer;
import cn.codethink.xiaoming.expression.constructor.Constructor;
import cn.codethink.xiaoming.expression.interpreter.Interpreter;

import java.util.List;
import java.util.Set;

/**
 * <h1>类型</h1>
 *
 * @author Chuanwise
 */
public interface Type {
    
    /**
     * 整数类型
     */
    Type INT = Interpreter.getInstance().getTypeOrFail("Int");
    
    /**
     * 浮点数类型
     */
    Type DOUBLE = Interpreter.getInstance().getTypeOrFail("Double");
    
    /**
     * 布尔类型
     */
    Type BOOL = Interpreter.getInstance().getTypeOrFail("Bool");
    
    /**
     * 字符类型
     */
    Type CHAR = Interpreter.getInstance().getTypeOrFail("Char");
    
    /**
     * 字符串类型
     */
    Type STRING = Interpreter.getInstance().getTypeOrFail("String");
    
    /**
     * 无类型
     */
    Type NOTHING = Interpreter.getInstance().getTypeOrFail("Nothing");
    
    /**
     * 列表类型
     */
    Type LIST = Interpreter.getInstance().getTypeOrFail("List");
    
    /**
     * 集合类型
     */
    Type SET = Interpreter.getInstance().getTypeOrFail("Set");
    
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
     * 获取分析器
     *
     * @return 分析器
     */
    Set<Analyzer> getAnalysers();
    
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
