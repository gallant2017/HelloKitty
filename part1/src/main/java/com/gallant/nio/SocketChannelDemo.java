package com.gallant.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by huangjunhao on 19/11/27.
 */
public class SocketChannelDemo {

    public static void main(String[] args) {

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 3333));
            //不断自旋
            while (!socketChannel.finishConnect()) {
                //可以干点别的,打打飞机...
            }
            System.out.println("连接成功,找到了炮友");
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(args[0].getBytes()); //跟炮友说点啥
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            socketChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
