package com.xuxue.spider.core.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;

public class MyPathChildenCacheListener implements PathChildrenCacheListener{

	private DownloadMachineZKNode node;

	public MyPathChildenCacheListener(DownloadMachineZKNode node){
		this.node=node;
	}


	public void childEvent(CuratorFramework client, PathChildrenCacheEvent event)throws Exception{

		if(event.getType()==PathChildrenCacheEvent.Type.CHILD_UPDATED){
			System.out.println(new String(event.getData().getPath()));
		}

	}

public static void main(String[] args)throws Exception{

	DownloadMachineZKNode node=new DownloadMachineZKNode("192.168.24.64:2181");
	MyPathChildenCacheListener l=new MyPathChildenCacheListener(node);
	node.addPathChildrenCacheListener(l);
	Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
		@Override
		public void run(){
			try{
				System.out.println("HHHHHHH");
				node.close();

			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}));
	Thread.sleep(Integer.MAX_VALUE);

}

}