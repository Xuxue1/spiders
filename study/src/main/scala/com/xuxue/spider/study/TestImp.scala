package com.xuxue.spider.study

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.{JButton, JFrame}

/**
  * Created by HanHan on 2016/6/16.
  */
object TestImp {

  def main(args:Array[String])={
    val frame=new JFrame("Hello");
    val button=new JButton("Hello");
    var data=0;

    implicit def convertedAction(action:(ActionEvent)=>Unit)=new ActionListener {
      override def actionPerformed(e: ActionEvent): Unit = action(e)
    }

    button.addActionListener((event:ActionEvent)=>{data+=1; println(data)})
    frame.add(button);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  }


}
