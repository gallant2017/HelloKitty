package com.gallant.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 *
 * nio 三大组件
 * Channel(通道),Buffer(缓冲器),Selector(选择器)
 * FileChannel不支持非阻塞,故不能使用Selector
 *
 * Selector:选择器的使用就是为了完成 IO多路复用,一个通道代表一条连接通路,通过玄选择器可以监控同时
 * 监控多个通道IO状况, 选择器和通道之间是监控和被监控关系.
 * 一个单线程处理一个选择器,一个选择器可以监控多通道,减少了线程间上下文切换的开销
 *
 * Created by huangjunhao on 19/11/25.
 */
public class ServerSocketChannelDemo {

    public static void main(String[] args) throws IOException {

        //1:获取Selector选择器
        Selector selector = Selector.open();
        //2:获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //3:设置非阻塞
        serverSocketChannel.configureBlocking(false);
        //4:绑定端口
        serverSocketChannel.bind(new InetSocketAddress(3333));
        System.out.println("server start.....");

        //5:将通道注册到选择器上,并注册的IO事件为：“接收新连接”
        serverSocketChannel.register(selector, SelectionKey.OP_READ);

        //6:轮询感兴趣的I/O就绪事件（选择键集合）
        while (selector.select() > 0) {
            // 7:获取选择键集合
            Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
            while (selectedKeys.hasNext()) {
                // 8、获取单个的选择键，并处理
                SelectionKey selectedKey = selectedKeys.next();
                if (selectedKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("accept is registed.");
                } else if (selectedKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectedKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int length = 0;
                    while ((length = socketChannel.read(byteBuffer)) > 0) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, length));
                        byteBuffer.clear();
                    }
                    socketChannel.close();
                }
            }
            selectedKeys.remove();
        }
    }


}
