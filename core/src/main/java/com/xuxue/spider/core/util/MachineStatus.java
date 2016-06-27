package com.xuxue.spider.core.util;

/**
 * Zookeeper中machine节点下的子节点的状态
 *
 *
 */
public enum MachineStatus {
    /**
     * 这个机器是活跃的
     */
    MACHINE_ALIVE,

    /**
     * 这个机器出故障断开了
     */
    MACHINE_LOST,

    /**
     * 这个节点没有创建
     */
    MACHINE_NOT_CREATE
}
