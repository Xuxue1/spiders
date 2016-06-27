package com.xuxue.spider.study

/**
  * Created by HanHan on 2016/6/16.
  */
object TestFunction2 {

  def main(args:Array[String]){
    (1 to 9).map("*" * _).foreach(println)

    val fun=(number:Double)=>Math.ceil(number)

    println(fun(3.14))

    Array(1.1,2.2,3.3,4.4,5.5,6.6).map(fun).foreach(println)

    def hright(f:(Double)=>Double)=f(0.25)

    println(hright(Math.ceil(_)));
    println(hright(Math.sqrt(_)));


  }


}
