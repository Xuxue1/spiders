package com.xuxue.spider.core.scala.downloader

import com.xuxue.spider.core.scala.cache.Cache
import com.xuxue.spider.core.scala.result.ResultIteam
import com.xuxue.spider.core.scala.task.Task
import com.xuxue.spider.core.scala.pluging.Pluging
import org.apache.http.impl.client.CloseableHttpClient
import com.xuxue.spider.core.scala.event.SpiderEvent
import com.xuxue.spider.core.scala.util.MyLogger

import com.xuxue.spider.core.scala.proxy.Proxy

import scala.collection.mutable

/**
  * Created by HanHan on 2016/7/9.
  */
trait Download[T] extends MyLogger{

  val taskCacke:Cache[Task]

  val resultCache:Cache[ResultIteam]

  val pluging:mutable.HashMap[String,Pluging[T]]

  val proxy:Proxy

  def consume():Unit

  def addPluging(pluging:Pluging[T]):Unit={
    this.pluging.put(pluging.name,pluging)
  }

  def removePluging(pluging:Pluging[T]):Unit={
    this.pluging.remove(pluging.name)
  }

  def notify(event:SpiderEvent[T]): Unit ={
    pluging.get(event.task.name) match {
      case Some(e)=>{
        e.filterTask(event).map(taskCacke.putElement)
        resultCache.putElement(e.getResult(event))
      }
      case None=>this.warn(s"插件没有包含 ${event.task.uri.getHost} 的插件")
    }
  }

}
