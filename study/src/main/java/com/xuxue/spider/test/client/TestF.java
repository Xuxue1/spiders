package com.xuxue.spider.test.client;

import java.awt.*;

/**
 * Created by HanHan on 2016/6/14.
 */
public class TestF {

    public static <T> T get(Object obj){
        return (T)obj;
    }

    public static void main(String[] args){

        Point s=get("Hello");

        System.out.println(get("Hello"));
    }

}
