package com.xuxue.spider.core.util;

import org.apache.curator.framework.recipes.cache.NodeCacheListener;

public class MyNodeCacheListener implements NodeCacheListener{

	DownloadMachineZKNode node;

	public MyNodeCacheListener(DownloadMachineZKNode node){
		this.node=node;
	}

	public void nodeChanged(){
		System.out.println("Hello word");
		System.out.println("node data update,new Data is"+
			new String(node.nodeCache.getCurrentData().getData()));
	}


	public static void main(String[] args)throws Exception{

		final DownloadMachineZKNode node=new DownloadMachineZKNode("192.168.24.64:2181");
		node.addNodeCacheListener(new MyNodeCacheListener(node));
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					System.out.println("lalala exit");
					node.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}));

		Thread.sleep(Integer.MAX_VALUE);


	}

}