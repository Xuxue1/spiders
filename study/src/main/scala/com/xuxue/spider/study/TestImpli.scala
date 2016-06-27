package com.xuxue.spider.study

/**
  * Created by HanHan on 2016/6/16.
  */
class TestImpli {

}
import java.io.File

import scala.io.Source;
class FileRich(file:File){
  def read=Source.fromFile(file).mkString
}

class FileRich2(file:File){
  def read2=Source.fromFile(file).mkString
}

object contex{


}

object TestImpli{

  implicit def fileRich(file:File)=new FileRich(file)

  implicit def fileRich2(file:File)=new FileRich2(file)

  implicit  val default:String="Word"

  def print(m1:String)(implicit m2:String): Unit ={
    println(m1+"  "+m2)
  }

  def main(args: Array[String]) {

    //println(new File("E:\\p.txt").read2)

    print("Hello")("word")
    print("Hello")
  }

}
