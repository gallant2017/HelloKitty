package com.gallant.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: Huang Junhao
 * @Date: 2020/1/2 10:57
 */
public class ClientSync implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static ZooKeeper zk = null;
    private static Stat stat = new Stat();

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        String path="/xxoo";
        zk=new ZooKeeper("192.168.210.129",5000,
                new ClientSync());
        connectedSemaphore.await();
        System.out.println(new String(zk.getData(path,true,stat)));
        Thread.sleep(300000);
    }

    public void process(WatchedEvent event) {
        if(Event.KeeperState.SyncConnected==event.getState()){
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                connectedSemaphore.countDown();
            } else if (event.getType() == Event.EventType.NodeDataChanged) {  //zk目录节点数据变化通知事件
                try {
                    System.out.println("配置已修改，新值为：" + new String(zk.getData(event.getPath(), true, stat)));
                } catch (Exception e) {
                }
            }
        }
    }
}
