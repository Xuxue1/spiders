package com.xuxue.spider.core.scala.util

import java.io._

import scala.util.{Success, Try}

/**
  * Created by HanHan on 2016/7/5.
  */
trait Writeable extends Serializable{

  lazy val size=toBytes().get.length

  def toBytes():Try[Array[Byte]]={
    import com.xuxue.spider.core.scala.util.Loan.use
    var data:Array[Byte]=null;
    Try{
      use(new ByteArrayOutputStream){
        byteOut=>
        use(new ObjectOutputStream(byteOut)){
          objOut =>
            objOut.writeObject(this)
            data=byteOut.toByteArray
        }
      }
      data
    }
  }

}

object Writeable{
  def toObject(data:Array[Byte]):Object={
    import com.xuxue.spider.core.scala.util.Loan._
    var ob:Object=null;
    val a=use(new ByteArrayInputStream(data,0,data.length)){
      byteIn=>
        use(new ObjectInputStream(byteIn)){
          obj=>
            ob=obj.readObject()
        }
    }
    ob
  }
}
