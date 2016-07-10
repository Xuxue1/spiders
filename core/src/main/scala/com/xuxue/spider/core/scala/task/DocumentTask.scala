package com.xuxue.spider.core.scala.task

import java.net.URI

import com.xuxue.spider.core.scala.task.CookieLeave.CookieLeave
import com.xuxue.spider.core.scala.util.MyLogger
import org.apache.http.HttpHost
import org.apache.http.client.methods.{HttpGet, HttpUriRequest}

import scala.util.Try

/**
  * Created by HanHan on 2016/7/7.
  */
class DocumentGetTask(
                    override val connectionTimeout:Int,
                    override val readTimeout:Int,
                    override val uri:URI,
                    override val name:String,
                    override val cookieLeave:CookieLeave,
                    override val maxDirection:Int,
                    override val id:Int) extends Task with MyLogger{


  override def buildURIRequest(httpHost: HttpHost): Try[HttpUriRequest] ={
    Try{
      val get=new HttpGet(uri)
      get.setConfig(buildRequestConfig(httpHost))
      get
    }
  }

  override def toString={
    s"name=$name id=$id uri=$uri cookieLeave=$cookieLeave " +
      s"connectionTimeout=$connectionTimeout readTimeout=$readTimeout" +
      s"maxDirection=$maxDirection"
  }

}

object DocumentGetTask{
  def custom():DocumentGetTaskBuilder={
    new DocumentGetTaskBuilder
  }
}

class DocumentGetTaskBuilder{

  var connectionTimeout:Int=500

  var readTimeout:Int=500

  var uri:URI= null

  var cookieLeave:CookieLeave=CookieLeave.NoNeed

  var name:String= null

  var maxDirection:Int=5

  var id:Int=0

  def build():DocumentGetTask={
    return new DocumentGetTask(connectionTimeout,readTimeout,uri,name,cookieLeave,maxDirection,id)
  }

  def setID(id:Int): DocumentGetTaskBuilder ={
    this.id=id;
    this
  }

  def setMaxDirection(maxDirection:Int):DocumentGetTaskBuilder={
    this.maxDirection=maxDirection
    this
  }

  def setName(name:String):DocumentGetTaskBuilder={
    this.name=name
    this
  }

  def setCookieLeave(cookieLeave: CookieLeave):DocumentGetTaskBuilder={
    this.cookieLeave=cookieLeave
    this
  }

  def setURI(uri:String):DocumentGetTaskBuilder={
    this.uri=new URI(uri)
    this
  }

  def setURI(uri:URI):DocumentGetTaskBuilder={
    this.uri=uri
    this
  }

  def setReadTimeout(readTimeout:Int):DocumentGetTaskBuilder={
    this.readTimeout=readTimeout
    this
  }

  def setConnectionTimeout(connectionTimeout:Int):DocumentGetTaskBuilder={
    this.connectionTimeout=connectionTimeout
    this
  }

}


