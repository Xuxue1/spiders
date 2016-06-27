package com.xuxue.spider.core.cache;

import com.xuxue.spider.core.task.Task;
import com.xuxue.spider.core.util.Serializabled;

import java.io.IOException;

/**
 * 任务的缓存
 */
public interface TaskCache extends Serializabled {

    long getTaskUsedMemory();

    void putTask(Task task)throws IOException;

    Task getTask()throws IOException,ClassNotFoundException;

    Task getTask(String host)throws IOException,ClassNotFoundException;

}
