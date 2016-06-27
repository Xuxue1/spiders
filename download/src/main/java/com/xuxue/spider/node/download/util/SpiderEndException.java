package com.xuxue.spider.node.download.util;

/**
 *
 * When a
 * @time 2016-6-27
 * @author xuxue
 */
public class SpiderEndException extends Exception{

	public SpiderEndException(String message){
		super(message);
	}

	public SpiderEndException(Exception e){
		super(e);
	}

	public SpiderEndException(){
		super();
	}

}