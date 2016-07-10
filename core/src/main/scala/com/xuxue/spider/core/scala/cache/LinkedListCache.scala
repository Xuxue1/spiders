package com.xuxue.spider.core.scala.cache

import com.xuxue.spider.core.scala.util.Writeable

/**
  * Created by HanHan on 2016/7/9.
  */
class LinkedListCache[T <:Writeable ] extends Cache[T]{
  override var usedMemory: Long = _

  import java.util.LinkedList


  val list=new LinkedList[T]();

  override def putElement(t: T): Unit = {
    usedMemory+=t.size
    list.add(t)
  }

  override def getElement(): Either[T, Exception] = {
        try{
          val t=list.remove()
          usedMemory-=t.size
          Left(t)
        }catch {
          case ex:Exception=>Right(ex)
        }
  }
}
