package com.xuxue.spider.thread

/**
  * Created by HanHan on 2016/6/16.
  */
import scala.concurrent.duration._
object Test1 extends App{

  def calc(name:String,max:Int=10):Unit=(1 to max)
    .map(_*2)
    .map{
        n=>;

        Thread.sleep((1.second).toMillis)

        val calc=n + 2
        println(name+":"+calc)
        calc
    }

  calc("fut2")
  Thread.sleep((5.seconds).toMillis)

}
