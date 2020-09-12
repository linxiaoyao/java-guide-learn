package com.learn.javareflect.interceptor;

import com.learn.javareflect.handler.Invocation;
import com.learn.javareflect.handler.TargetHandler2;

/**
 * @Author: xiazewei
 * @Date: 2020-08-10 18:20
 */
public interface Interceptor {

    default void intercept() {}

    default void intercept(Invocation invocation) throws Exception {}

    /**
     *  插入目标类
     */
    default Object plugin(Object target) {
        return TargetHandler2.wrap(target, this);
    };
}
