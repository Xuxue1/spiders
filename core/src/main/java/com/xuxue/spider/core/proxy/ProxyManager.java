package com.xuxue.spider.core.proxy;


import org.apache.http.HttpHost;

/**
 * Created by HanHan on 2016/6/23.
 */
public interface ProxyManager {

    HttpHost getProxy();

    HttpHost backProxy();

}
