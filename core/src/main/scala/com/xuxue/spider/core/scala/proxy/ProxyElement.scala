package com.xuxue.spider.core.scala.proxy

import org.apache.http.HttpHost

/**
  * Created by HanHan on 2016/7/9.
  */
class ProxyElement(val host:String,
                   val port:Int,
                   val name:String,
                   val password:String,
                   val maxUsed:Int) {

  lazy val toHost:HttpHost=new HttpHost(host,port)

}
