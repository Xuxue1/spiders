package com.xuxue.spider.node.download.bootstrap;

import java.io.File;
import java.io.IOException;

/**
 * 这个类表示下载节点的启动
 */
public class BootStrap {
    /*
    public final TaskCache taskCache;

    public final ResultIteamCache resultIteamCache;

    public final ProxyManager proxyManager;
*/
    public static void main(String[] args)throws IOException{
        File file=new File(".");
        File[] f=file.listFiles();
        for(File ff:f){
            System.out.println(ff.getAbsolutePath());
        }
    }


    private static class BootStrapBuilder{






    }

}
