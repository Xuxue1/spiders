package com.xuxue.spider.study

import scala.reflect.ClassTag

/**
  * Created by HanHan on 2016/6/16.
  */

class TestMatch[T:ClassTag]{

  def print(value:T):Unit=println(value);

  val t=print _

}

object TestMatch {

  def main(args:Array[String]):Unit={

    def matchArray(arr:Array[Int])={
      arr match {
        case _=>{
          arr.foreach(println);
        }
      }
    }

    matchArray(Array(0,1,2,3))

  }

}
