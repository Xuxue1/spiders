package com.xuxue.spider.study

/**
  * Created by HanHan on 2016/6/16.
  */
object TestType {
  def main(args: Array[String]) {

    def myprint(p:{def print():Unit})=p.print();

    val a=new MyType

    myprint(a)
  }
}

class MyType{

  xuxue =>

  def print()=println("Hello");
  1 to 10

}
