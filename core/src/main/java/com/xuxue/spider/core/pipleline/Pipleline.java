package com.xuxue.spider.core.pipleline;

/**
 * Created by HanHan on 2016/6/14.
 */
public interface Pipleline {

    void save(ResultIteam iteam)throws PiplelineException;

    String getName();

    int getID();

}
