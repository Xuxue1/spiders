package com.xuxue.spider.core.downloader;

import com.xuxue.spider.core.pluging.Pluging;
import com.xuxue.spider.core.task.Task;

/**
 * Created by HanHan on 2016/6/14.
 */
public interface Downloader {

    void consume(Task task);


    void addPluging(Pluging pluging);

}
