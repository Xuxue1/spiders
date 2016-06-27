package com.xuxue.spider.core.tools;

import java.io.*;

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

    public static byte[] objectToByte(Object obj)throws IOException{
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        ObjectOutputStream oout=new ObjectOutputStream(out);
        oout.writeObject(obj);
        byte[] data=out.toByteArray();
        oout.close();
        out.close();
        return data;
    }
}
