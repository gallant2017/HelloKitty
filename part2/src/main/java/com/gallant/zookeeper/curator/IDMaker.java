package com.gallant.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.common.StringUtils;

/**
 * @Author: Huang Junhao
 * @Date: 2020/1/6 20:37
 */
public class IDMaker {

    private final String ZK_ADDRESS = "192.168.210.129";
    //Zk客户端
    CuratorFramework client = null;

    public void init() {
        client = ClientFactory.createSimple(ZK_ADDRESS);
        client.start();
    }

    public void destroy() {
        if (null != client) {
            client.close();
        }
    }

    private String createSqlNote(String pathPefix) {
        String descPath = null;
        try {
            descPath = client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(pathPefix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return descPath;
    }

    public String makeId(String noteName) {
        String str = createSqlNote(noteName);
        if (str == null)
            return null;
        int index = str.lastIndexOf(noteName);
        if (index >= 0) {
            index += noteName.length();
            return index <= str.length() ? str.substring(index) : "";
        }
        return str;
    }

    public static void main(String[] args) {
        IDMaker maker=new IDMaker();
        maker.init();
        String noteName="/zkpro/idtest/ID-";
        for (int i=0;i<1000;i++) {
            String id=maker.makeId(noteName);
            System.out.println("第" + i + "个创建的Id为：" + id);
        }
    }
}
