package com.xuxue.spider.core.scala.util

import com.xuxue.spider.core.scala.tools.StreamReader
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients


/**
  * Created by HanHan on 2016/7/5.
  */
object Loan {
  def use[T <: {def close();}](resource:T)(func:T=>Unit){
    try{
      func(resource)
    }finally{
      resource.close
    }
  }

  def main(args: Array[String]) {
    use(HttpClients.createDefault()){
      client=>
        val get=new HttpGet("http://www.baidu.com")
        use(client.execute(get)){
          response=>
            println(StreamReader.toString(response.getEntity.getContent,true))
        }
    }
  }
}
