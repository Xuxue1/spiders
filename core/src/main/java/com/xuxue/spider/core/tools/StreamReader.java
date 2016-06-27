package com.xuxue.spider.core.tools;

import com.xuxue.spider.core.task.Task;
import com.xuxue.spider.core.util.Args;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 读取流的工具,这个类里面包含几个静态的方法，方便读取流里的数据
 * @since 1.0
 * @author xuxue
 * @time 2016-6-13
 * Created by HanHan on 2016/6/13.
 */
public class StreamReader {

    public static final int TEMPSIZE=128;


    /**
     * 读取流中的二进制数据
     * @param in
     * @param close 读取过后是否关闭这个流  如果close为true 表示读取过后关闭这个流
     * @return 流中的二进制数据
     */
    public static byte[] streamToByte(InputStream in,boolean close)throws IOException{
        Args.NotNULL(in);
        try{
            ByteArrayOutputStream out=new ByteArrayOutputStream();
            byte[] temp=new byte[TEMPSIZE];
            int readedSize=0;
            while((readedSize=in.read(temp,0,TEMPSIZE))!=-1){
                out.write(temp,0,readedSize);
            }
            return out.toByteArray();
        }finally {
            if(close)
                in.close();
        }
    }

    public static String streamToString(InputStream in,boolean close)throws IOException{
        byte[] data=streamToByte(in,close);
        Charset set=CpdetectorEncoding.getEncoding(data,false);
        return new String(data,set);
    }

    public static Document streamToDocument(InputStream in, Task task,boolean close)throws IOException{
        String s=streamToString(in,close);
        Document doc= Jsoup.parse(s,task.getURI().toString());
        return doc;
    }
    public static void main(String[] args)throws IOException{
        CloseableHttpClient client= HttpClients.createDefault();
        HttpGet get=new HttpGet("http://www.baidu.com");
        CloseableHttpResponse res=client.execute(get);
        String s=streamToString(res.getEntity().getContent(),true);
    }



}
