package com.xuxue.spider.study

/**
  * Created by HanHan on 2016/6/16.
  */
object TestFunction {


  def main(args: Array[String]) {

    def sum(a:Int,b:Int,c:Int):Int=a+b+c;

    println(sum(1,2,3));

    val function=sum _;

    println(function(1,2,3));

    println(function.apply(1,2,3));

    val  fb=function(1,_:Int,3);
    println(fb(2))
    println(fb(10))

  }
}
