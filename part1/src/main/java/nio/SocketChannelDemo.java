package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
