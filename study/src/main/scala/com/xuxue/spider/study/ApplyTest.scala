package com.xuxue.spider.study

/**
  * Created by HanHan on 2016/6/16.
  */
class ApplyTest {

  def apply() = {println("Hello")}

  def print:Unit={println("word")}

}

object ApplyTest{
  def apply(): ApplyTest = {
    println("wuwuwuwu")
    new ApplyTest();
  }

  def main(args: Array[String]) {
    val a=ApplyTest()
    a()
    a.print
  }
}
