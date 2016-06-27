package com.xuxue.spider.core.tools;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by HanHan on 2016/6/23.
 */
public class ByteToObject {
    public static <T> T getObject(byte[] data)throws IOException,ClassNotFoundException{
        ByteArrayInputStream in=new ByteArrayInputStream(data);
        ObjectInputStream obin=new ObjectInputStream(in);
        Object obj=obin.readObject();
        obin.close();
        in.close();
        return (T)obj;
    }
}
