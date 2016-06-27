package com.xuxue.spider.core.scala.tools

import java.io.{ByteArrayOutputStream, InputStream}
import com.xuxue.spider.core.tools.CpdetectorEncoding


/**
  * Created by HanHan on 2016/6/13.
  */
object StreamReader {

  final val TEMPSIZE=128;

  /**
    *
    * @param in
    * @param close
    * @return
    */
  def toByte(in:InputStream,close:Boolean):Array[Byte]={
    val a=new ByteArrayOutputStream()
    val temp= new Array[Byte](TEMPSIZE)
    var size= 0
    def read(temp:Array[Byte],start:Int,end:Int):Int= {size=in.read(temp,0,TEMPSIZE); size}
    while(read(temp,0,TEMPSIZE)!= -1) a.write(temp,0,size)
    if(close) in.close()
    a.toByteArray
  }

  /**
    *
    * @param in
    * @param close
    * @return
    */
  def toString(in:InputStream,close:Boolean):String={
    val data=toByte(in,close)
    new String(data,CpdetectorEncoding.getEncoding(data,false))
  }

  /**
    *
    * @param args
    */
  def main(args: Array[String]): Unit = {
    new {
      self =>
    }
  }
}
