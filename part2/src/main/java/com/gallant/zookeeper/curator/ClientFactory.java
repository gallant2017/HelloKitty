package com.gallant.zookeeper.curator;


import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Curator作为zookeeper优秀的客户端
 *
 * @Author: Huang Junhao
 * @Date: 2020/1/6 20:04
 */
public class ClientFactory {

    public static CuratorFramework createSimple(String connectionString) {
        //重试策略 1000毫秒 3次
        //第一次重试等待 1000ms 第二次重试等待2000ms 第三次重试等待4000ms
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        return CuratorFrameworkFactory.newClient(connectionString, retryPolicy);
    }

    public static CuratorFramework createWithOptions(String connectionString, RetryPolicy retryPolicy, int connectionTimeOut,
                                                     int sessionTimeOut) {
        return CuratorFrameworkFactory.builder().connectString(connectionString).retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectionTimeOut)
                .sessionTimeoutMs(sessionTimeOut)
                .build();
    }

    public static void main(String[] args) {
        CuratorFramework client = ClientFactory.createSimple("192.168.16.132");
        client.start();
        try {
            String data = "fuck you.";
            byte[] payload = data.getBytes("UTF-8");
            String zkPath = "/zkpro/note-1";
            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(zkPath, payload);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
