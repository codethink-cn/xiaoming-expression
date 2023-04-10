package cn.codethink.xiaoming.expression.interpreter;

import cn.codethink.xiaoming.expression.Expression;
import cn.codethink.xiaoming.expression.api.APIFactory;
import cn.codethink.xiaoming.expression.lang.Type;

import java.io.Reader;

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
     * @return 解释器
     */
    static Interpreter getInstance() {
        return APIFactory.getInstance().getInterpreter();
    }
    
    /**
     * 获取父解释器
     *
     * @return 父解释器或 null
     */
    Interpreter getParent();
    
    /**
     * 获取类型
     *
     * @param name 类型名
     * @return 类型或 null
     */
    Type getType(String name);
    
    /**
     * 获取类型
     *
     * @param name 类型名
     * @return 类型
     */
    Type getTypeOrFail(String name);
    
    /**
     * 编译表达式为抽象语法树
     *
     * @param expression 表达式
     * @return 抽象语法树
     * @throws CompileException 编译错误
     */
    Expression compile(String expression) throws CompileException;
    
    /**
     * 编译表达式为抽象语法树
     *
     * @param expression    表达式
     * @param configuration 编译配置
     * @return 抽象语法树
     * @throws CompileException 编译错误
     */
    Expression compile(String expression, CompilationConfiguration configuration) throws CompileException;
    
    /**
     * 编译表达式为抽象语法树
     *
     * @param reader 字符流
     * @return 抽象语法树
     * @throws CompileException 编译错误
     */
    Expression compile(Reader reader) throws CompileException;
    
    /**
     * 编译表达式为抽象语法树
     *
     * @param reader        字符流
     * @param configuration 编译配置
     * @return 抽象语法树
     * @throws CompileException 编译错误
     */
    Expression compile(Reader reader, CompilationConfiguration configuration) throws CompileException;
}