package com.xuxue.spider.thread

import java.util.concurrent.Executors

import scala.concurrent.{Await, ExecutionContext, Future}

/**
  * Created by HanHan on 2016/6/16.
  */
object Test2 extends App{
  import scala.concurrent.duration._
  implicit val ex=ExecutionContext.
    fromExecutor(Executors.newWorkStealingPool(1))

  def calc(name : String,max:Int=0)=(1 to max)
    .map(_*2)
    .map{
      number=>;
        Thread.sleep((1 second).toMillis)
        val cala=number+2
        println(name+ ":"+cala)
        cala
    }

  val f1=Future{
    calc("fut1",10)
  }

  val f2=Future{
    calc("fut2",10)
  }

  Await.ready(Future.sequence(List(f1,f2)),5 minutes)

}
