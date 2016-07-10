package com.xuxue.spider.core.scala.result

import com.xuxue.spider.core.scala.util.Writeable

import scala.collection.mutable

/**
  * Created by HanHan on 2016/7/8.
  */
class ResultIteam(val name:String, val id:Int, val url:String) extends Writeable{
  val resultMap=new mutable.HashMap[String,Object]()

  def put(name:String,obj:Object):Option[Object]={
    resultMap.put(name,obj)
  }

  def get(name:String):Option[Object]={
    resultMap.get(name)
  }
}

object ResultIteam{
  def apply(name: String,id: Int,url: String): ResultIteam = new ResultIteam(name,id,url)
}

