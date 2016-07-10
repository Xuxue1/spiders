package com.xuxue.spider.core.pluging;

import com.xuxue.spider.core.pipleline.ResultIteam;
import com.xuxue.spider.core.task.Task;

import java.util.LinkedList;


/**
 *
 */

public interface Pluging {

    LinkedList<Task> createChildTask(SpiderEvent e);

    LinkedList<ResultIteam> createResult(SpiderEvent e);

    String getName();

    int getID();
}
