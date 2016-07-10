package com.xuxue.spider.core.scala.downloader

import com.xuxue.spider.core.scala.cache.Cache
import com.xuxue.spider.core.scala.task.Task
import com.xuxue.spider.core.scala.result.ResultIteam

import scala.collection.mutable.HashMap
import com.xuxue.spider.core.scala.pluging.Pluging
import org.apache.http.impl.client.{BasicCredentialsProvider, CloseableHttpClient, HttpClients}
import com.xuxue.spider.core.scala.proxy.Proxy
import org.apache.http.{Header, HttpRequest, HttpResponse}
import org.apache.http.config.MessageConstraints
import org.apache.http.conn.{HttpConnectionFactory, ManagedHttpClientConnection}
import org.apache.http.conn.routing.HttpRoute
import org.apache.http.impl.DefaultHttpResponseFactory
import org.apache.http.impl.conn.{DefaultHttpResponseParser, DefaultHttpResponseParserFactory, ManagedHttpClientConnectionFactory, PoolingHttpClientConnectionManager}
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory
import org.apache.http.io.{HttpMessageParserFactory, HttpMessageWriterFactory, SessionInputBuffer}
import org.apache.http.message.{BasicHeader, BasicLineParser}
import org.apache.http.util.CharArrayBuffer
import org.jsoup.nodes.Document

/**
  * Created by HanHan on 2016/7/9.
  */
class DocumentDownloader(
                          override val taskCacke:Cache[Task]
                         ,override val resultCache:Cache[ResultIteam]
                         ,override val pluging:HashMap[String,Pluging[Document]]
                         ,override val proxy:Proxy) extends Download[Document] with Runnable {

  val credsProvider:BasicCredentialsProvider=new BasicCredentialsProvider()

  val messageParserFactory:HttpMessageParserFactory[HttpResponse]=new DefaultHttpResponseParserFactory(){

   override def create(buffer:SessionInputBuffer,constraints:MessageConstraints):Unit={
      val lineParser=new BasicLineParser(){
        override def parseHeader(buffer:CharArrayBuffer):Header={
          try{
            super.parseHeader(buffer);
          }catch {
            case ex:Exception=> new BasicHeader(buffer.toString,null)
          }
        }
      }

     new DefaultHttpResponseParser(buffer,lineParser,DefaultHttpResponseFactory.INSTANCE,constraints){
       override protected def reject(line:CharArrayBuffer,count:Int):Boolean ={
         false
       }
     }

   }

    val requestWriterFactory:HttpMessageWriterFactory[HttpRequest]=new DefaultHttpRequestWriterFactory();

    val httpConnectionFactory:HttpConnectionFactory[HttpRoute,ManagedHttpClientConnection]
                      =new ManagedHttpClientConnectionFactory(requestWriterFactory,messageParserFactory)

    val
  }

  val connectionManager:PoolingHttpClientConnectionManager
        =new PoolingHttpClientConnectionManager()

  val client:CloseableHttpClient=
      HttpClients.custom()
        .setDefaultCredentialsProvider(credsProvider)
          .setConnec
        .build()

  override def consume(): Unit ={
    client.getDe
  }


  override def run(): Unit = {

  }
}
