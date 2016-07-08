package com.xuxue.spider.core.scala.task

import com.xuxue.spider.core.scala.util.Writeable
import java.net.URI

import com.xuxue.spider.core.scala.task.CookieLeave.CookieLeave
import org.apache.http.HttpHost
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.HttpUriRequest

import scala.util.Try
/**
  * Created by HanHan on 2016/7/7.
  */
trait Task extends Writeable{

  val connectionTimeout:Int

  val readTimeout:Int

  val uri:URI

  val name:String

  val cookieLeave:CookieLeave

  val maxDirection:Int

  val id:Int

  val size=toBytes().get.length

  def buildURIRequest(httpHost:HttpHost):Try[HttpUriRequest]

  def buildURIRequest():Try[HttpUriRequest]={
    buildURIRequest(null)
  }

  def host:String=uri.getHost

  def buildRequestConfig(httpHost:HttpHost):RequestConfig={
    RequestConfig.custom()
      .setConnectionRequestTimeout(connectionTimeout)
      .setConnectionRequestTimeout(connectionTimeout)
      .setSocketTimeout(readTimeout)
      .setMaxRedirects(maxDirection)
      .build()
  }
  override def toString={
    s"name=$name id=$id uri=$uri cookieLeave=$cookieLeave " +
      s"connectionTimeout=$connectionTimeout readTimeout=$readTimeout" +
      s"maxDirection=$maxDirection"
  }
}
