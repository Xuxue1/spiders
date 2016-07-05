package com.xuxue.spider.core.scala.util

import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import scala.util.{Success, Try}

/**
  * Created by HanHan on 2016/7/5.
  */
trait Writeable extends Serializable{

  def toBytes():Try[Array[Byte]]={

    Success(new Array(0xFF))
  }

}
