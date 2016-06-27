package com.xuxue.spider.core.pluging;

import com.xuxue.spider.core.pipleline.ResultIteam;
import com.xuxue.spider.core.task.DocumentGetTask;
import com.xuxue.spider.core.task.Task;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by HanHan on 2016/6/15.
 */
public class TestPluging  implements Pluging{

    private String name;

    private int id;

    private Logger logger=Logger.getLogger(getClass());

    @Override
    public LinkedList<Task> createChildTask(SpiderEvent e) {
        Document doc=e.getResult();
        Elements eles=doc.select("a");
        LinkedList<Task> tasks=new LinkedList<Task>();
        for(Element se:eles){
            String s=se.absUrl("href");
            try{
                tasks.add(DocumentGetTask.custum().setURI(s).setID(0).build());
            }catch (Exception ee){
                ee.printStackTrace();
            }
        }
        return tasks;
    }

    @Override
    public LinkedList<ResultIteam> createResult(SpiderEvent e) {
        Document doc=e.getResult();
        String value=doc.toString();
        ResultIteam iteam=new ResultIteam(e.getTask().getURI().toString(),getName());
        iteam.put("html",doc.text());
        LinkedList<ResultIteam> iteams=new LinkedList<ResultIteam>();
        iteams.add(iteam);
        return iteams;
    }

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public int getID() {
        return 0;
    }

}
