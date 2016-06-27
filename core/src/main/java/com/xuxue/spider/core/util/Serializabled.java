package com.xuxue.spider.core.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by HanHan on 2016/6/23.
 */
public interface Serializabled extends Serializable,Sized{

    default  byte[] toBytes()throws IOException{
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        ObjectOutputStream oout=new ObjectOutputStream(out);
        oout.writeObject(this);
        byte[] data=out.toByteArray();
        oout.close();
        out.close();
        return data;
    }

    default long getSize(){
       try{
           return toBytes().length;
       }catch (Exception e){
           return -1;
       }
    }
}
