package com.xuxue.spider.core.scala.event

import com.xuxue.spider.core.scala.cache.Cache
import com.xuxue.spider.core.scala.result.ResultIteam
import com.xuxue.spider.core.scala.task.Task

/**
  * Created by HanHan on 2016/7/8.
  */
class SpiderEvent[T](val resultCache:Cache[ResultIteam],
                     val taskCache:Cache[Task],
                     val t:Either[T,Throwable],
                     val task:Task) {

}

object SpiderEvent{

}

class SpiderEventBuilder[T]{
  var t:Either[T,Throwable]= _
  var resultCache:Cache[ResultIteam]=_
  var task:Task=_
  var taskCache:Cache[Task]=_

  def setElement(either: Either[T,Throwable]):SpiderEventBuilder[T]={
    this.t=either
    this
  }

  def setResultCache(cache: Cache[ResultIteam]):SpiderEventBuilder[T]={
    this.resultCache=cache
    this
  }

  def setTask(task:Task):SpiderEventBuilder[T]={
    this.task=task
    this
  }

  def setTaskCache(taskCache:Cache[Task]):SpiderEventBuilder[T]={
    this.taskCache=taskCache
    this
  }

}