package com.xuxue.spider.core.scala.util



/**
  *
  * MyLogger特质 混入就可以使用Logger的方法了
  */
trait MyLogger {}
object MyLogger{
  implicit def getLogger(l:MyLogger)={
    import org.apache.log4j.Logger
    Logger.getLogger(l.getClass)
  }
}
