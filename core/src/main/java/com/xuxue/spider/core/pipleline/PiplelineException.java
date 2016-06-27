package com.xuxue.spider.core.pipleline;

/**
 * Created by HanHan on 2016/6/14.
 */
public class PiplelineException extends Exception{

    private Exception ex;

    public PiplelineException(String s){
        super(s);
    }

    public PiplelineException(Exception e){
        super(e);
        this.ex=e;
    }

    public PiplelineException(){
        super();
    }

    public Exception getCause(){
        return this.ex;
    }

}
