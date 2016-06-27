package com.xuxue.spider.core.pluging;

import com.xuxue.spider.core.cache.ResultIteamCache;
import com.xuxue.spider.core.cache.TaskCache;
import com.xuxue.spider.core.task.Task;

/**
 * Created by HanHan on 2016/6/15.
 */
public class SpiderEvent {

    private ResultIteamCache resultIteamCache;

    private TaskCache taskCache;

    private Object result;

    private ProcessStatus status;

    private Task task;

    private int responseCode;

    private Throwable e;

    public ProcessStatus getProcessStatus() {
        return this.status;
    }

    public void setProcessStatus(ProcessStatus status) {
        this.status = status;
    }

    public <T> T getResult() {
        return (T) result;
    }

    public Task getTask() {
        return this.task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public <T> void setResult(T result) {
        this.result = result;
    }

    public ResultIteamCache getResultIteamCache() {
        return this.resultIteamCache;
    }

    public void setResultIteamCache(ResultIteamCache resultIteamCache) {
        this.resultIteamCache = resultIteamCache;
    }

    public TaskCache getTaskCache() {
        return this.taskCache;
    }

    public void setTaskCache(TaskCache taskCache) {
        this.taskCache = taskCache;
    }

    public int getResponseCode(){
        return this.responseCode;
    }

    public void setResponseCode(int responseCode){
        this.responseCode=responseCode;
    }

    public void setException(Throwable e){
        this.e=e;
    }

    public Throwable getException(){
        return this.e;
    }

}
