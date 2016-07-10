package com.xuxue.spider.core.scala.proxy

/**
  * Created by HanHan on 2016/7/9.
  */
class TestProxy extends Proxy{

  override def put(proxyElement: ProxyElement): Unit = println("add")

  override def get(): ProxyElement = null
}
