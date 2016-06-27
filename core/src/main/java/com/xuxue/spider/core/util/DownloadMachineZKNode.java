package com.xuxue.spider.core.util;


import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.Logger;
import org.apache.zookeeper.data.Stat;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;


import java.net.InetAddress;
import java.io.IOException;

/**
 * 每个下载节点都对应着一个zk的临时节点 这个临时节点的地址就是这个下载节点的ip地址
 *
 * 它对应着这个下载节点的状态  暂停状态或运行  节点 的下载线程有多少
 *
 * 这个节点有两个子节点
 *
 *                      spider
 *                        /
 *                       /
 *                      /
 *                  machine[持久节点]
 *                     /
 *                    /
 *                   /
 *                (ip)[客户端离开时自动删除]
 *               /   \
 *              /     \
 *  [临时节点]proxy   message[临时节点]
 *
 *
 *  proxy ==> 对应着这个节点的代理 (如果一个代理节点失效 ProxyNode通过这个节点来通知客户端)
 *  message==> 对应着这个节点抓取过程中的信息 例如 已经处理过多少个任务  失败了多少个任务  成功了多少个任务
 *
 *
 *
 *  这个类对应着znode上的(ip)节点  以及对这个节点的事件的监听
 */
public class DownloadMachineZKNode {

    private final Logger logger=Logger.getLogger(getClass());

    public static final String path="/spider/machine/"+getLocalAddress();

    /**
     * 连接zookeeper的客户端
     */
    private CuratorFramework client;

    /**
     * 连接zookeeper服务的地址
     */
    public final String connectionString;


    public final NodeCache nodeCache;

    public final PathChildrenCache childrenCache;


    /**
     *
     *根据一个zookeeper服务器的地址创建一个对应的下载节点
     */
    public DownloadMachineZKNode(final String connectionString)throws Exception{
        this.connectionString=connectionString;
        client= CuratorFrameworkFactory.builder().
                connectString(connectionString)
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();
        client.start();
        logger.info("创建一个连接到"+connectionString+"zookeeper客户端");
        CreateNodeStatus status=create();
        logger.info("机器节点已经创建 这个节点的ip地址是"+getLocalAddress());
        nodeCache=new NodeCache(client,path,false);   
        childrenCache=new PathChildrenCache(client,path,false);
        nodeCache.start();
        childrenCache.start(StartMode.POST_INITIALIZED_EVENT);
    }


/***************************静态方法声明*********************************************/
     /**
     * 获取本机的ip地址
     * @return 本机的ip地址
     */
    public static String getLocalAddress(){
        try{
            return InetAddress.getLocalHost().getHostAddress();
        }catch (Exception e){
            throw new Error("");
        }
    }

/******************************************公共方法声明*******************************/
    /**
     *返回这个节点的状态
     * @return
     * @throws Exception
     */
    public MachineStatus machineStatus()throws Exception{
        boolean machine=exist(path);
        boolean child=exist(path+"/massage");
        if(!machine) {
            return MachineStatus.MACHINE_NOT_CREATE;
        }else if(machine&&child){
            return MachineStatus.MACHINE_ALIVE;
        }else
            return MachineStatus.MACHINE_LOST;
    }

    /**
     *创建节点 并返回创建节点的状态
     * @return 
     * @throws Exception
     */
    public CreateNodeStatus create()throws Exception{
        MachineStatus status=machineStatus();
        if(status==MachineStatus.MACHINE_ALIVE){
            return CreateNodeStatus.NODE_EXIST;
        }else if(status==MachineStatus.MACHINE_LOST){
            deleteNode(path+"/proxy");
            deleteNode(path+"/massage");
            client.create().creatingParentsIfNeeded().forPath(path);
            client.create().creatingParentsIfNeeded().forPath(path+"/proxy");
            client.create().creatingParentsIfNeeded().forPath(path+"/massage");
            return CreateNodeStatus.DELETE_OLDE_CREATE_NEW;
        }else{
            client.create().creatingParentsIfNeeded().forPath(path);
            client.create().creatingParentsIfNeeded().forPath(path+"/proxy");
            client.create().creatingParentsIfNeeded().forPath(path+"/massage");
            return CreateNodeStatus.CREATE_NEW_NODE;
        }
    }


    public void addNodeCacheListener(NodeCacheListener listener){

        this.nodeCache.getListenable().addListener(listener);

    }

    public void addPathChildrenCacheListener(PathChildrenCacheListener listener){

        this.childrenCache.getListenable().addListener(listener);

    }

    public void close()throws IOException{
        nodeCache.close();
        childrenCache.close();
        deleteNode(path);
        client.close();
       
    }

    public void stopNode()throws Exception{
        update(path,"stop".getBytes());
    }

    public void startNode()throws Exception{
        update(path,"start".getBytes());
    }

    public void closeNode()throws Exception{
        update(path,"close".getBytes());
    }

    public void reportMessage()throws Exception{
        update(path,"close".getBytes());
    }


/**********************************************私有方法声明*****************************/
    
    private void update(String path,byte[] data)throws Exception{
        client.setData().forPath(path,data);
    }

    /**
     * 删除这个指定的节点
     * @param path
     * @throws Exception
     */
    private void deleteNode(final String path){
        try{
            client.delete().deletingChildrenIfNeeded().forPath(path);
        }catch(Exception e){
            logger.error("删除这个节点出现异常",e);
        }
    }

    /**
     *表示创建这个节点返回的状态
     */
    private enum CreateNodeStatus{
        CREATE_NEW_NODE,
        DELETE_OLDE_CREATE_NEW,
        NODE_EXIST
    }

    /**
     *检测给定的路径下的节点是否存在
     * @param path
     * @return
     * @throws Exception
     */
    private boolean exist(final String path)throws Exception{
        Stat stat=client.checkExists().forPath(path);
        return stat!=null;
    }

}
