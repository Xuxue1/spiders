package com.xuxue.spider.core.task;

import com.xuxue.spider.core.util.Serializabled;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.*;
import java.net.URI;

/**
 * 这个接口定义了一个任务的规范
 *
 * 每一个任务必须能生成一个HttpRequest  必须能返回这个任务的URL的host
 * 必须定义了Cookie的等级
 *
 * @author  xuxue
 * @time 2016-6-13
 */
public interface Task extends Serializabled {

    /**
     *一个任务必须能够生成这个任务对应的Http请求
     * @return
     */
    HttpUriRequest buildHttpRequest();

    /**
     * 返回这个任务对应的Http请求地址的主机名
     * @return
     */
    String getHost();

    /**
     * 返回这个Task的URL
     * @return
     */
    URI getURI();

    /**
     * 返回这个任务需求的Cookie的等级
     * @return
     */
    CookieLeave getCookieLeave();

    /**
     * 返回这个任务的名字
     * @return
     */
    String getName();

    /**
     * 返回这个任务的id
     * @return
     */
    int getID();


    static <T> T toTask(byte[] data)throws IOException,ClassNotFoundException{
        ByteArrayInputStream in=new ByteArrayInputStream(data);
        ObjectInputStream obin=new ObjectInputStream(in);
        Object obj=obin.readObject();
        return (T)obj;
    }

}
