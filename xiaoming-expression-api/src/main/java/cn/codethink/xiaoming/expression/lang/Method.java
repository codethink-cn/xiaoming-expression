package cn.codethink.xiaoming.expression.lang;

import java.util.List;

/**
 * <h1>方法</h1>
 *
 * @author Chuanwise
 */
public interface Method {
    
    /**
     * 获取参数
     *
     * @return 参数
     */
    List<Parameter> getParameters();
}
