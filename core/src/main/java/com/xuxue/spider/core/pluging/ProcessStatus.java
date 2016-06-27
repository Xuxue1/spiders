package com.xuxue.spider.core.pluging;

/**
 * 这个枚举 枚举了一个任务执行的状态
 *
 * @time 2016-6-15
 *@author xuxue
 * Created by HanHan on 2016/6/15.
 */
public enum ProcessStatus {
    /**
     * 执行成功
     */
    SUCCESS,

    /**
     * 执行的过程中有异常
     */
    EXCEPTION,

    /**
     * 正在执行
     */
    PROCESSING
}
