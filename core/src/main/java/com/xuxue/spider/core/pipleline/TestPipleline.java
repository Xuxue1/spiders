package com.xuxue.spider.core.pipleline;

import com.xuxue.spider.core.util.Immutable;

import java.util.Map;
import java.util.Set;

/**
 * Created by HanHan on 2016/6/14.
 */
@Immutable
public class TestPipleline implements Pipleline{

    @Override
    public void save(ResultIteam iteam) throws PiplelineException {
        System.out.println("*******************"+"start a iteam"+"***********************");
        Set<Map.Entry<String,Object>> ens=iteam.entrySet();
        for(Map.Entry<String,Object> s:ens){
            System.out.println(s.getKey()+"   "+s.getValue());
        }
        System.out.println("****************************end a iteam************************");
    }

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public int getID() {
        return 1;
    }
}
