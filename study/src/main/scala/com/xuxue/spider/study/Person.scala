package com.xuxue.spider.study

/**
  * Created by HanHan on 2016/6/16.
  */
class Person (val name:String,val age:Int){

  def print={
    println("name="+name+"age="+age)
  };

}

class Work(override val name:String,override val age:Int,val salery:Int) extends Person(name,age){

  override  def print=println("name="+name+" age="+age+" salery="+salery)

}

object Person{
  def main(args: Array[String]) {

    val p=new Work("xuxue",22,10);
    p.print

  }
}

