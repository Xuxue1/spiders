package com.xuxue.spider.core.util;

/**
 * Created by HanHan on 2016/6/13.
 */
public class Args {

    public static void NotNULL(Object obj){
        if(obj==null)
            throw new IllegalArgumentException(obj+"is null");
    }

    public static void main(String[] args){
        System.out.println("Hello");
    }

}
