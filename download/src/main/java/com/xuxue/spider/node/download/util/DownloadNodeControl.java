package com.xuxue.spider.node.download.util;

import com.xuxue.spider.core.cache.TestResultIteamCache;
import com.xuxue.spider.core.cache.TestTaskCache;
import com.xuxue.spider.core.pipleline.ResultIteam;
import com.xuxue.spider.core.task.Task;
import com.xuxue.spider.core.tools.ByteToObject;
import com.xuxue.spider.core.util.DownloadNodeState;
import com.xuxue.spider.core.util.SpiderEndException;
import com.xuxue.spider.core.util.ResultEndException;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import com.xuxue.spider.core.cache.TaskCache;
import com.xuxue.spider.core.cache.ResultIteamCache;
import com.xuxue.spider.core.util.DownloadMachineZKNode;
import org.apache.log4j.Logger;


import java.io.IOException;


public class DownloadNodeControl implements NodeCacheListener,
	PathChildrenCacheListener,TaskCache,ResultIteamCache{

	private final Logger logger=Logger.getLogger(getClass());

	public transient final DownloadMachineZKNode zkNode;

	public transient final TaskCache taskCache;

	public transient final ResultIteamCache resultCache;

	public transient final Object getTaskLock=new Object();

    /**
     * 表示这个节点的状态
     */
    private DownloadNodeState state;
	
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
	public ResultIteam getResult() throws IOException, ClassNotFoundException ,ResultEndException{
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

	/**
	 *
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws SpiderEndException
     */
	@Override
	public Task getTask() throws IOException, ClassNotFoundException,SpiderEndException {
		synchronized (getTaskLock){
			this.processedTask+=1;
			try{
				return this.taskCache.getTask();
			}catch (SpiderEndException e){
				try{
					getTaskLock.wait();
				}catch (InterruptedException ee){
					logger.info("等待是中断",ee);
					throw new SpiderEndException(ee);
				}
				return getTask();
			}
		}
	}


    private void checkNodeState()throws SpiderEndException{
        if(this.state==DownloadNodeState.ALIVE){
            return;
        }else if(this.state==DownloadNodeState.STOP){
            throw new SpiderEndException();
        }else if(this.state==DownloadNodeState.WAIT){
            synchronized (getTaskLock){
                try{
                    getTaskLock.wait();
                }catch (InterruptedException e){
                    logger.info("",e);
                    throw new SpiderEndException(e);
                }
            }
        }
    }

	@Override
	public Task getTask(String host) throws IOException, ClassNotFoundException,SpiderEndException {
		synchronized (getTaskLock){
			this.processedTask+=1;
			try{
				return this.taskCache.getTask(host);
			}catch (SpiderEndException e){
				try{
					getTaskLock.wait();
				}catch (InterruptedException ee){
					logger.info("等待是中断",ee);
					throw new SpiderEndException(ee);
				}
				return getTask(host);
			}
		}
	}


	@Override
	public void nodeChanged() throws Exception {
		ChildData data=zkNode.nodeCache.getCurrentData();
		byte[] response=data.getData();
        DownloadNodeState sta= ByteToObject.getObject(response);
        this.state=sta;
        if(sta==DownloadNodeState.ALIVE){
            synchronized (getTaskLock) {
                getTaskLock.notifyAll();
            }
        }
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

		final  DownloadMachineZKNode node=new DownloadMachineZKNode("115.28.224.180:2181");
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