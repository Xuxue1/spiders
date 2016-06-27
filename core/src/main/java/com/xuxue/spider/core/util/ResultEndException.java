 package com.xuxue.spider.core.util;
 /**
 *当 从一个ResultIteamCache 中获取ResultIteam 时
 *缓存里没有ResultIteam 时 会抛出这个异常
 *
 *@time 2016-6-27
 *@author xuxue
 */
 public class ResultEndException extends Exception{

 	public ResultEndException(String message){
 		super(message);
 	}

 	public ResultEndException(Exception e){
 		super(e);
 	}

 	public ResultEndException(){
 		super();
 	}

 }