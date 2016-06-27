package com.xuxue.spider.study

/**
  * Created by HanHan on 2016/6/16.
  */
abstract  class TestAbstracr {

    def hello(message:String):Unit

    var id:Int

}

class t extends Testt with Test2{

  def say():Unit={
    print("Hello");
  }

  def say2():Unit={
    print("Hello")
  }

}



