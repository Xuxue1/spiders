package com.xuxue.spider.core.task;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * Created by HanHan on 2016/6/14.
 */
public class DocumentGetTask  implements Task{

    private final int connectTimeout;

    private final int readTimeout;

    private final URI uri;

    private final int id;

    private final String name;

    private final CookieLeave cookieLeave;

    private final int maxredirection;

    private final HttpHost proxy;

    private DocumentGetTask(int connectTimeout, int readTimeout, URI uri, int id, String name, CookieLeave cookieLeave
                            , int maxRedirection, HttpHost proxy){
        this.connectTimeout=connectTimeout;
        this.readTimeout=readTimeout;
        this.uri=uri;
        this.id=id;
        this.name=name;
        this.cookieLeave=cookieLeave;
        this.maxredirection=maxRedirection;
        this.proxy=proxy;
    }

    public static DocumentGetTaskBuilder custum(){
        return new DocumentGetTaskBuilder();
    }

    @Override
    public HttpUriRequest buildHttpRequest() {
        HttpGet get=new HttpGet(uri);
        RequestConfig config=RequestConfig.custom().setConnectTimeout(connectTimeout)
                .setMaxRedirects(maxredirection)
                .setConnectionRequestTimeout(connectTimeout)
                .setSocketTimeout(readTimeout)
                .setProxy(proxy)
                .build();
        get.setConfig(config);
        return get;
    }

    @Override
    public String getHost() {
        return this.uri.getHost();
    }

    @Override
    public URI getURI() {
        return this.uri;
    }

    @Override
    public CookieLeave getCookieLeave() {
        return this.cookieLeave;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getID() {
        return this.id;
    }


    public static class DocumentGetTaskBuilder{

        private int connectTimeout=1000;

        private int readTimeout=1000;

        private URI uri=null;

        private int id=0;

        private String name=null;

        private CookieLeave cookieLeave=CookieLeave.NoNeed;

        private int maxredirection=3;

        private HttpHost proxy=null;

        public DocumentGetTaskBuilder(){

        }

        public DocumentGetTaskBuilder setProxy(HttpHost proxy){
            this.proxy=proxy;
            return this;
        }

        public DocumentGetTask build(){

            return  new DocumentGetTask(connectTimeout,readTimeout,uri,id,name,cookieLeave,maxredirection,proxy);

        }

        public DocumentGetTaskBuilder setName(String name){
            this.name=name;
            return this;
        }

        public DocumentGetTaskBuilder setCookieLeave(CookieLeave cookieLeave){
            this.cookieLeave=cookieLeave;
            return this;
        }

        public DocumentGetTaskBuilder setMaxRedirection(int redirection){
            this.maxredirection=redirection;
            return this;
        }

        public DocumentGetTaskBuilder setURI(String uri)throws URISyntaxException{
            this.uri=new URI(uri);
            return this;
        }



        public DocumentGetTaskBuilder setConnectTimeout(int connectTimeout){
            this.connectTimeout=connectTimeout;
            return this;
        }

        public DocumentGetTaskBuilder setID(int id){
            this.id=id;
            return this;
        }

        public DocumentGetTaskBuilder setURI(URI uri){
            this.uri=uri;
            return this;
        }

        public DocumentGetTaskBuilder setReadTimeout(int readTimeout){
            this.readTimeout=readTimeout;
            return this;
        }
    }

    public static void main(String[] args)throws Exception{

    }
}
