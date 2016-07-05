package com.xuxue.spider.core.cache;

import com.xuxue.spider.core.task.Task;
import com.xuxue.spider.core.util.SpiderEndException;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by HanHan on 2016/6/14.
 */
public class TestTaskCache implements TaskCache {


    public LinkedList<byte[]> tasks;

    private long size;

    public TestTaskCache(){
        tasks=new LinkedList<byte[]>();
    }


    @Override
    public synchronized void putTask(Task task)throws IOException {
        byte[] data=task.toBytes();
        size+=data.length;
        tasks.add(data);
    }

    @Override
    public long getTaskUsedMemory(){
        return size;
    }

    @Override
    public Task getTask() throws IOException,ClassNotFoundException,SpiderEndException{
        byte[] data=tasks.remove();
        Task task=Task.toTask(data);
        return task;
    }


    @Override
    public Task getTask(String host) {
        throw new UnsupportedOperationException("这个操作不支持");
    }

}
