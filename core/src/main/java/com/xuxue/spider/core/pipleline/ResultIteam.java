package com.xuxue.spider.core.pipleline;

import com.xuxue.spider.core.util.Args;
import com.xuxue.spider.core.util.Immutable;
import com.xuxue.spider.core.util.Serializabled;

import java.util.*;

/**
 * Created by HanHan on 2016/6/14.
 */
@Immutable
public class ResultIteam implements Serializabled{

    private final HashMap<String,Object> result;

    private final String url;

    private final String name;

    private final byte[] data;

    public ResultIteam(String url,String name){
        this.url=url;
        this.name=name;
        result=new HashMap<String,Object>();
        try{
            data=toBytes();
        }catch (Exception e){
            throw new Error(e);
        }
    }

    public ResultIteam put(String name,Object value){
        result.put(name,value);
        return this;
    }

    public <T> T get(String name){
        Object obj=result.get(name);
        Args.NotNULL(obj);
        return (T)obj;
    }

    public Set<Map.Entry<String,Object>> entrySet(){
        return result.entrySet();
    }

    public Collection<Object> getAllValue(){
        return result.values();
    }

    public String getURL(){
        return this.url;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        StringBuilder builder=new StringBuilder();

        builder.append("[ url="+url+"  name="+name+"   result="+result);
        builder.append("]");

        return builder.toString();
    }

}
