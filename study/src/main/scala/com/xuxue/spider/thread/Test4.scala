package com.xuxue.spider.thread

import org.apache.http.client.methods.{HttpGet, HttpPut}
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.apache.http.util.EntityUtils

/**
  * Created by HanHan on 2016/7/2.
  */
object Test4 {
  def main(args: Array[String]) {
    val client=HttpClients.createDefault();
    val put=new HttpGet("http://localhost:5000/ticket/RHCP");
    val res=client.execute(put);
    val entry=res.getEntity();
    println(EntityUtils.toString(entry))
    res.close()
    client.close()
  }
}
