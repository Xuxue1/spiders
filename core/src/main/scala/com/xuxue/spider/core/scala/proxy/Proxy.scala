package com.xuxue.spider.core.scala.proxy

/**
  * Created by HanHan on 2016/7/9.
  */



trait Proxy {

  def put(proxyElement: ProxyElement):Unit

  def get():ProxyElement

}
