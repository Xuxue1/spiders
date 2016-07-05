package com.xuxue.spider.test.client;

import java.awt.*;
import java.util.ArrayList;

import com.xuxue.spider.test.akka.Test;

/**
 * Created by HanHan on 2016/6/14.
 */
public class TestF {

    public static <T> T get(Object obj){
        return (T)obj;
    }

    public static void main(String[] args){
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<17;i++)
            list.add(null);
        list.add(11,11);

    }

}
