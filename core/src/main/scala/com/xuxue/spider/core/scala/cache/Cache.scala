package com.xuxue.spider.core.scala.cache


import com.xuxue.spider.core.scala.util.Writeable


/**
  * Created by HanHan on 2016/7/6.
  */
trait Cache[T <: Writeable] {

  var usedMemory:Long;

  def getElement():Either[T,Exception]

  def putElement(t:T)

  def getUsedMemory():Long=usedMemory
}








