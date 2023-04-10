package cn.codethink.xiaoming.expression.compiler;

import cn.codethink.xiaoming.expression.Expression;

/**
 * <h1>编译器</h1>
 *
 * <p>编译器是将字符串表达式编译成由 {@link Expression} 表示的抽象语法树的工具。</p>
 *
 * @author Chuanwise
 */
public interface Compiler {
    
    /**
     * 编译表达式为抽象语法树
     *
     * @param expression 表达式
     * @return 抽象语法树
     * @throws CompileException 编译错误
     */
    Expression compile(String expression) throws CompileException;
}