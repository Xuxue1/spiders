package com.xuxue.spider.core.scala.pluging

/**
  * Created by HanHan on 2016/7/9.
  */

import com.xuxue.spider.core.scala.event.SpiderEvent
import com.xuxue.spider.core.scala.result.ResultIteam
import com.xuxue.spider.core.scala.task.{CookieLeave, Task}
import org.jsoup.nodes.Document
import java.util.LinkedList

import com.xuxue.spider.core.scala.task.DocumentGetTask

import collection.JavaConversions._
class DocumentPluging extends Pluging[Document] {
  override val name: String = "test"

  override def filterTask(event: SpiderEvent[Document]): Iterable[Task] = {
    event.t match {
      case Left(element)=>{
        element.select("a").filter(_.hasAttr("href")).map{
          e=>
            val url=e.absUrl("href")
            DocumentGetTask.custom()
               .setConnectionTimeout(100)
              .setName("test")
              .setCookieLeave(CookieLeave.NoNeed)
              .setID(0)
                .setMaxDirection(5)
              .setURI(url)
              .setReadTimeout(100)
              .build()
        }
      }
      case Right(exception)=>new LinkedList[Task]()
    }
  }

  override def getResult(event: SpiderEvent[Document]): ResultIteam = {
    val iteam= ResultIteam("test",1,event.task.uri.toString)
    iteam.put("html",event.t.left.get.toString)
    iteam
  }

  override val id: Int = 0
}
