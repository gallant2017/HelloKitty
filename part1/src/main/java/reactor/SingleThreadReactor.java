package reactor;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 单线程Reactor
 * <p>
 * Created by huangjunhao on 19/11/27.
 */
public class SingleThreadReactor implements Runnable {

    public static void main(String[] args) {
        System.out.println("123");
    }

    Selector selector;
    ServerSocketChannel serverSocketChannel;

    public SingleThreadReactor() throws IOException {
        serverSocketChannel = serverSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(3334));
        serverSocketChannel.configureBlocking(false);
        selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new AcceptorHandler());
    }

    @Override
    public void run() {

        while (Thread.interrupted()) {
            try {
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext()) {
                    SelectionKey sk = (SelectionKey) it.next();
                    dispatch(sk);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void dispatch(SelectionKey sk) {
        Runnable handler = (Runnable) sk.attachment();
        if (handler != null) {
            handler.run();
            ;
        }
    }

    class AcceptorHandler implements Runnable {

        @Override
        public void run() {
            System.out.println("AcceptorHandler.run.......");
        }
    }
}