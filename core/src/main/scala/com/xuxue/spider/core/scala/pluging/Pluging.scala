package com.xuxue.spider.core.scala.pluging

import com.xuxue.spider.core.scala.event.SpiderEvent
import com.xuxue.spider.core.scala.result.ResultIteam
import com.xuxue.spider.core.scala.task.Task
/**
  * Created by HanHan on 2016/7/8.
  */
trait Pluging[T] {

  val name:String

  val id:Int

  def filterTask(event:SpiderEvent[T]):Iterable[Task]

  def getResult(event:SpiderEvent[T]):ResultIteam

}
