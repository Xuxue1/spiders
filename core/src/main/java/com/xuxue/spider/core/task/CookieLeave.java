package com.xuxue.spider.core.task;

/**
 * 这个枚举表示任务需要的cookie的等级
 * @author xuxue
 * @time 2016-6-13
 *
 */
public enum CookieLeave {
    /**
     * 表示不需要cookie
     */
    NoNeed,

    /**
     * 表示这个cookie只能被一台机器使用
     */
    NeedOnly,

    /**
     * 表示这个cookie能被不同的机器使用
     */
    NeedOpen
}
