package com.shangzf.common.util;

import java.util.Map;

/**
 * 用户上下文
 */
public class UserContextHolder {

    private final ThreadLocal<Map<String, String>> threadLocal;

    private UserContextHolder() {
        this.threadLocal = new ThreadLocal<>();
    }

    /**
     * 创建实例
     */
    public static UserContextHolder getInstance() {
        return SingletonHolder.HOLDER;
    }

    /**
     * 静态内部类单例模式
     * 单例初使化
     */
    private static class SingletonHolder {
        private static final UserContextHolder HOLDER = new UserContextHolder();
    }

    /**
     * 用户上下文中放入信息
     */
    public void setContext(Map<String, String> map) {
        threadLocal.set(map);
    }

    /**
     * 获取上下文中的信息
     */
    public Map<String, String> getContext() {
        return threadLocal.get();
    }

    /**
     * 清空上下文
     */
    public void clear() {
        threadLocal.remove();
    }

}
