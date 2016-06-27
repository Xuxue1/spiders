package com.xuxue.spider.core.cache;

import com.xuxue.spider.core.pipleline.ResultIteam;
import com.xuxue.spider.core.util.Serializabled;
import com.xuxue.spider.core.util.ResultEndException;

import java.io.IOException;

/**
 *结果的缓存
 */
public interface ResultIteamCache extends Serializabled{

    /**
     * 向缓存中添加一个结果
     * @param iteam
     */
    void putResult(ResultIteam iteam)throws IOException;

    /**
     * 从缓存总获得一个项
     * @return
     */
    ResultIteam getResult()throws IOException,ClassNotFoundException,ResultEndException;

    /**
     * 返回这个缓存占用的内纯
     * @return
     */
    long getResultUsedMemory();
}
