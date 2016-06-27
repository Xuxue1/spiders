package com.xuxue.spider.test.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

/**
 * Created by HanHan on 2016/6/15.
 */
public class TestBigInt {

    public static void main(String[] args)throws IOException{

        PrintWriter p=new PrintWriter("E:\\p.txt");
        BigInteger i=new BigInteger("1");

        long t1=System.nanoTime();
        i= i.shiftLeft(10000000);

        System.out.println(System.nanoTime()-t1);
        p.println(i);
        p.flush();
        p.close();
    }

}
