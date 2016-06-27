package com.xuxue.spider.core.cache;

import com.xuxue.spider.core.pipleline.ResultIteam;
import com.xuxue.spider.core.tools.ByteToObject;
import com.xuxue.spider.core.util.ResultEndException;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by HanHan on 2016/6/14.
 */
public class TestResultIteamCache implements ResultIteamCache {

    public LinkedList<byte[]> iteams;

    private long size=0;


    public TestResultIteamCache(){
        iteams=new LinkedList<byte[]>();
    }


    @Override
    public synchronized void putResult(ResultIteam iteam)throws IOException{
        byte[] data=iteam.toBytes();
        size+=data.length;
        iteams.add(data);
    }

    @Override
    public synchronized ResultIteam getResult() throws IOException,ClassNotFoundException,ResultEndException{
        byte[] data=iteams.remove();
        size-=data.length;
        return ByteToObject.getObject(data);
    }

    @Override
    public long getResultUsedMemory() {
        return size;
    }

}
