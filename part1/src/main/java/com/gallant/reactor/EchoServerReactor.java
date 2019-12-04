package com.gallant.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by huangjunhao on 19/12/2.
 */
public class EchoServerReactor implements Runnable {

    public static void main(String[] args) throws IOException {
        new Thread(new EchoServerReactor()).start();
    }

    Selector selector;
    ServerSocketChannel serverSocketChannel;

    public EchoServerReactor() throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(3333));
        serverSocketChannel.configureBlocking(false);
        selector = Selector.open();
        SelectionKey sk = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new AcceptorHandler());
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                selector.select();
                Set<SelectionKey> selected = selector.selectedKeys();
                Iterator<SelectionKey> it = selected.iterator();
                while (it.hasNext()) {
                    SelectionKey sk = it.next();
                    dispatch(sk);
                }
                selected.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void dispatch(SelectionKey sk) {
        Runnable attachment = (Runnable) sk.attachment();
        if (attachment != null) {
            attachment.run();
        }
    }

    class AcceptorHandler implements Runnable {

        @Override
        public void run() {
            System.out.println("接受新连接");
            System.out.println("读写操作");
        }
    }
}
