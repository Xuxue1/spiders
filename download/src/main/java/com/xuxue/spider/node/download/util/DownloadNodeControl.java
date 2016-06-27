package com.xuxue.spider.node.download.util;

import com.xuxue.spider.core.cache.TestResultIteamCache;
import com.xuxue.spider.core.cache.TestTaskCache;
import com.xuxue.spider.core.pipleline.ResultIteam;
import com.xuxue.spider.core.task.Task;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import com.xuxue.spider.core.cache.TaskCache;
import com.xuxue.spider.core.cache.ResultIteamCache;
import com.xuxue.spider.core.util.DownloadMachineZKNode;



import java.io.IOException;


public class DownloadNodeControl implements NodeCacheListener,
	PathChildrenCacheListener,TaskCache,ResultIteamCache{


	public transient final DownloadMachineZKNode zkNode;

	public transient final TaskCache taskCache;

	public transient final ResultIteamCache resultCache;

	public transient final Object getTaskLock=new Object();


	
	private long resultMemory=0;

	private long processedTask=0;

	private long backedTask=0;

	private long savedResult=0; 



	public DownloadNodeControl(final DownloadMachineZKNode zkNode,
		final TaskCache taskCache,final ResultIteamCache resultIteamCache){
		this.taskCache=taskCache;
		this.resultCache=resultIteamCache;
		this.zkNode=zkNode;

	}


	@Override
	public void putResult(ResultIteam iteam) throws IOException {
		long size=iteam.getSize();
		resultMemory+=size;
		resultCache.putResult(iteam);
	}

	@Override
	public ResultIteam getResult() throws IOException, ClassNotFoundException {
		ResultIteam iteam=resultCache.getResult();
		return iteam;
	}

	@Override
	public long getResultUsedMemory() {
		return resultMemory;
	}

	@Override
	public long getTaskUsedMemory() {
		return 0;
	}

	@Override
	public void putTask(Task task) throws IOException {
		backedTask+=1;
		this.taskCache.putTask(task);
	}

	@Override
	public Task getTask() throws IOException, ClassNotFoundException {
		synchronized (getTaskLock){
			this.processedTask+=1;
			return this.taskCache.getTask();
		}
	}

	@Override
	public Task getTask(String host) throws IOException, ClassNotFoundException {
		synchronized(getTaskLock){
			this.processedTask+=1;
			return this.taskCache.getTask(host);
		}
	}


	@Override
	public void nodeChanged() throws Exception {
		ChildData data=zkNode.nodeCache.getCurrentData();
		System.out.println(new String(data.getData()));
	}

	@Override
	public long getSize(){
		try{
			return toBytes().length;
		}catch (Exception e ){
			return -1;
		}
	}

	@Override
	public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {

	}



	public static void main(String[] args)throws Exception{

		final  DownloadMachineZKNode node=new DownloadMachineZKNode("192.168.27.34:2181");
		final TestTaskCache taskCache=new TestTaskCache();
		final ResultIteamCache iteamCache=new TestResultIteamCache();
		final DownloadNodeControl c=new DownloadNodeControl(node,taskCache,iteamCache);
		node.addNodeCacheListener(c);
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					node.close();
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}));

		Thread.sleep(Integer.MAX_VALUE);
	}
}