package com.xuxue.spider.core.downloader;

import com.xuxue.spider.core.cache.ResultIteamCache;
import com.xuxue.spider.core.cache.TaskCache;
import com.xuxue.spider.core.cache.TestResultIteamCache;
import com.xuxue.spider.core.cache.TestTaskCache;
import com.xuxue.spider.core.pipleline.ResultIteam;
import com.xuxue.spider.core.pluging.Pluging;
import com.xuxue.spider.core.pluging.ProcessStatus;
import com.xuxue.spider.core.pluging.SpiderEvent;
import com.xuxue.spider.core.pluging.TestPluging;
import com.xuxue.spider.core.task.CookieLeave;
import com.xuxue.spider.core.task.DocumentGetTask;
import com.xuxue.spider.core.task.Task;
import com.xuxue.spider.core.tools.StreamReader;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by HanHan on 2016/6/14.
 */
public class DocumentDownloader implements Downloader{


    private final Logger logger=Logger.getLogger(getClass());

    //*************************不可变的对象的声明**********************
    public final TaskCache taskCache;

    public final ResultIteamCache resultIteamcache;

    public final CloseableHttpClient client;


    public final HashMap<String,List<Pluging>> listener;

    private DocumentDownloader(TaskCache cache,
                              ResultIteamCache iteamCache,
                              CloseableHttpClient client,
                              List<Pluging> plugings){
        this.taskCache=cache;
        this.resultIteamcache=iteamCache;
        this.client=client;
        listener=new HashMap<String,List<Pluging>>();
        for(Pluging pluging:plugings){
            String name=pluging.getName();
            List<Pluging> p=listener.get(name);
            if(p==null) p=new ArrayList<Pluging>();
            p.add(pluging);
            listener.put(name,p);
        }

    }

    //************************可变的对象声明*********************


    @Override
    public void consume(Task task) {
        //TODO
        SpiderEvent event=new SpiderEvent();
        event.setProcessStatus(ProcessStatus.PROCESSING);
        event.setTask(task);
        try(CloseableHttpResponse res=client.execute(task.buildHttpRequest())){
            int responseCode=res.getStatusLine().getStatusCode();
            event.setResponseCode(responseCode);
            Document doc= StreamReader.streamToDocument(res.getEntity().getContent(),task,true);
            event.setResult(doc);
            event.setProcessStatus(ProcessStatus.SUCCESS);
        }catch (Exception e){
            event.setProcessStatus(ProcessStatus.EXCEPTION);
            event.setException(e);
        }finally {
            List<Pluging> p=listener.get(task.getName());
            for(Pluging pluging:p){
                List<Task> tasks=pluging.createChildTask(event);
                for(Task t:tasks){
                    try{
                        taskCache.putTask(t);
                    }catch (IOException e){
                        logger.warn("存储task出现异常",e);
                    }
                }
                List<ResultIteam> iteam=pluging.createResult(event);

                for(ResultIteam i:iteam){
                    try {
                        resultIteamcache.putResult(i);
                    }catch (IOException ee){
                        logger.warn("存入ResultIteam 出现异常");
                    }
                }
            }
        }
    }

    @Override
    public void addPluging(Pluging pluging) {
        List<Pluging> p=listener.get(pluging.getName());
        if(p==null) p=new ArrayList<Pluging>();
        p.add(pluging);
        listener.put(pluging.getName(),p);
    }

    public static DocumentDownloaderBuilder custom(){
        return new DocumentDownloaderBuilder();
    }


    public static class DocumentDownloaderBuilder{

        private TaskCache taskCache;

        private ResultIteamCache iteamCache;

        private CloseableHttpClient client;

        private List<Pluging> plugings;


        private DocumentDownloaderBuilder(){
            taskCache=new TestTaskCache();
            iteamCache=new TestResultIteamCache();
            client= HttpClients.createDefault();
            plugings=new ArrayList<Pluging>();
        }

        public DocumentDownloaderBuilder setTaskCache(TaskCache taskCache){
            this.taskCache=taskCache;
            return this;
        }

        public DocumentDownloaderBuilder setResultIteamCache(ResultIteamCache resultIteamCache){
            this.iteamCache=resultIteamCache;
            return this;
        }

        public DocumentDownloaderBuilder setCloseableHttpClient(CloseableHttpClient cliet){
            this.client=cliet;
            return this;
        }

        public DocumentDownloaderBuilder setPlugings(List<Pluging> plugings){
            this.plugings=plugings;
            return this;
        }
        public DocumentDownloader build(){
            return new DocumentDownloader(taskCache,iteamCache,client,plugings);
        }

    }

    public static void main(String[] args)throws Exception{
        DocumentDownloader d=DocumentDownloader.custom()
                .setCloseableHttpClient(HttpClients.createDefault())
                .build();
        Task task=DocumentGetTask.custum().setName("test")
                .setURI(new URI("http://www.baidu.com"))
                .setID(0)
                .setConnectTimeout(5000)
                .setMaxRedirection(2)
                .setCookieLeave(CookieLeave.NoNeed)
                .build();
        d.addPluging(new TestPluging());
        d.consume(task);
        System.out.println(d.taskCache.getTaskUsedMemory());
        System.out.println(d.resultIteamcache.getResultUsedMemory());
        System.out.println(d.taskCache.getTask().getHost());
        System.out.println(d.resultIteamcache.getResult().toString());
    }
}
