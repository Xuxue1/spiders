package com.xuxue.spider.test.akka;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import java.util.ArrayList;

/**
 * Created by HanHan on 2016/7/8.
 */
public class Test2<T extends HttpUriRequest> {

    ArrayList<T> t=new ArrayList<T>();

    public void add(T tt){
        t.add(tt);
    }

    public static void mian(String[] args){
        Test2 t=new Test2<HttpUriRequest>();
        t.add(new HttpGet());
    }

}
