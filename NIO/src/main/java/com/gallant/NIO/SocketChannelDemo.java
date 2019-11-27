package com.gallant.NIO;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by huangjunhao on 19/11/18.
 */
public class SocketChannelDemo {

    public static void main(String[] args) {
        try {
            clientSend(args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * socketchannel test
     * @throws IOException
     */
    private static void clientSend(String msg) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 3333));
        socketChannel.configureBlocking(false);

        //不断自旋,等待连接
        while (!socketChannel.finishConnect()) {
            //do some thinks
        }

        System.out.println("客户端连接成功!");
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(msg.getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        socketChannel.close();

    }


}
