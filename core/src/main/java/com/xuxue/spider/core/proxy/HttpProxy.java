package com.xuxue.spider.core.proxy;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;

/**
 * 表示一个代理地址
 */
public class HttpProxy {

    public final String address;

    public final int port;

    public final String name;

    public final String passwd;

    public volatile int maxUsed;

    private final HttpHost host;

    public final AuthScope authScope;

    public final Credentials credentials;

    private volatile int used;

    private Object maxUsedLock=new Object();

    private Object proxyLock=new Object();

    public HttpProxy(String address,int port,String name,String passwd,int maxUsed){
        this.address=address;
        this.port=port;
        this.name=name;
        this.passwd=passwd;
        this.maxUsed=maxUsed;
        this.used=0;
        host=new HttpHost(address,port);
        authScope=new AuthScope(address,port);
        credentials=new UsernamePasswordCredentials(name,passwd);
    }



    public int getMaxUsed(){

        synchronized (maxUsedLock){
            return maxUsed;
        }

    }

    public void setMaxUsed(int maxUSed){
        synchronized (maxUsedLock){
            this.maxUsed=maxUSed;
        }
    }

    public HttpHost getProxy(){
        synchronized (proxyLock){
            if(used<maxUsed){
                used+=1;
                return host;
            }else{
                return null;
            }
        }
    }

    public void backProxy(){
        synchronized (proxyLock){
            used-=1;
        }
    }

}
