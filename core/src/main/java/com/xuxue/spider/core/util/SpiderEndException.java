package com.xuxue.spider.core.util;

/**
*
*当从 TaskCache中获取Task时 当TaskCache中没有Task时 会抛出这个异常
*@time 2016-6-27
*@author xuxue
*/

public class SpiderEndException extends Exception{

	public SpiderEndException(String massage){
		super(massage);
	}

	public SpiderEndException(Exception e){
		super(e);
	}

	public SpiderEndException(){
		super();
	}
}