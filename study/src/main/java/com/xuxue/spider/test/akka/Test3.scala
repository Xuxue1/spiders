package com.xuxue.spider.test.akka

import org.apache.http.client.methods.{HttpGet, HttpUriRequest}

/**
  * Created by HanHan on 2016/7/8.
  */
class Test3[T <: HttpUriRequest] {


  def get():T={
    val get:T=new HttpGet("").asInstanceOf;
    get
  }

  new Function[] {}
}
