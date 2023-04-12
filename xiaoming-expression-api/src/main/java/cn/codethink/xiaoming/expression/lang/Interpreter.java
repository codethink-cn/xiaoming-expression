package cn.codethink.xiaoming.expression.lang;

import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.api.APIFactory;
import cn.codethink.xiaoming.expression.formatter.FormattingConfiguration;

import java.io.Reader;
import java.util.List;
import java.util.Set;

/**
 * <h1>解释器</h1>
 *
 * <p>解释器用于处理小明表达式。主要有编译、反编译、注册和注销功能。</p>
 *
 * <p>编译是将字符串表达式编译成由 {@link Expression} 表示的抽象语法树的工具。</p>
 *
 * @author Chuanwise
 */
public interface Interpreter {
    
    /**
     * 获取解释器
     *
     * @param parent 父解释器
     * @return 解释器
     */
    static Interpreter newInstance(Interpreter parent) {
        return APIFactory.getInstance().getInterpreter(parent);
    }
    
    /**
     * 获取解释器
     *
     * @return 解释器
     */
    static Interpreter newInstance() {
        return APIFactory.getInstance().getInterpreter();
    }
    
    /**
     * 获取父解释器
     *
     * @return 父解释器或 null
     */
    Interpreter getParent();
    
    /**
     * 获取方法
     *
     * @param name        方法名
     * @param parametersClasses 参数类型
     * @return 方法
     */
    List<Function> getFunctions(String name, List<Class<?>> parametersClasses);
    
    /**
     * 获取方法
     *
     * @param name        方法名
     * @param parametersClasses 参数类型
     * @return 方法或 null
     */
    Function getFunction(String name, List<Class<?>> parametersClasses);
    
    /**
     * 获取方法
     *
     * @param name        方法名
     * @param paraClasses 参数类型
     * @return 方法
     */
    Function getFunctionOrFail(String name, List<Class<?>> paraClasses);
    
    /**
     * 编译表达式为抽象语法树
     *
     * @param expression 表达式
     * @return 抽象语法树
     */
    Expression compile(String expression);
    
    /**
     * 编译表达式为抽象语法树
     *
     * @param reader 字符流
     * @return 抽象语法树
     */
    Expression compile(Reader reader);
    
    /**
     * 分析对象为表达式
     *
     * @param subject 对象
     * @return 表达式
     */
    Expression analyze(Object subject);
    
    /**
     * 分析对象为表达式
     *
     * @param subject    对象
     * @param properties 属性
     * @return 表达式
     */
    Expression analyze(Object subject, Set<Object> properties);
    
    /**
     * 格式化表达式
     *
     * @param expression 表达式
     * @return 格式化后的表达式
     */
    String format(Expression expression);
    
    /**
     * 格式化表达式
     *
     * @param expression    表达式
     * @param configuration 格式配置
     * @return 格式化后的表达式
     */
    String format(Expression expression, FormattingConfiguration configuration);
    
    /**
     * 注册方法
     *
     * @param subjects 主体
     */
    void registerMethods(Object... subjects);
    
    /**
     * 注册方法
     *
     * @param subject 主体
     */
    void registerMethods(Object subject);
}