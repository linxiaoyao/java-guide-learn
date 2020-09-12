package com.learn.javareflect.interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: xiazewei
 * @Date: 2020-08-10 19:19
 */
public class InterceptorChain {

    private List<Interceptor> interceptors = new ArrayList<>();

    /**
     * 插入所有拦截器
     */
    public Object pluginAll(Object target) {
        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    /**
     * 返回一个不可修改集合，只能通过addInterceptor方法添加
     * 这样控制权就在自己手里
     */
    public List<Interceptor> getInterceptorList() {
        return Collections.unmodifiableList(interceptors);
    }
}
