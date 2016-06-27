package com.xuxue.spider.study

/**
  * Created by HanHan on 2016/6/18.
  */
class Self {

}


object Sel{

  @annotation.tailrec
  def go(n:Int,acc:Int): Int ={
    if(n<=0) acc
    else go(n-1,n*acc)
  }

}