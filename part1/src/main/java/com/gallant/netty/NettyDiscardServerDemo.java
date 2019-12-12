package com.gallant.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by huangjunhao on 19/12/11.
 */
public class NettyDiscardServerDemo {

    private final int serverPort;
    ServerBootstrap bootstrap = new ServerBootstrap();

    public NettyDiscardServerDemo(int port) {
        this.serverPort = port;
    }

    public void runServer() throws InterruptedException {
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workLoopGroup = new NioEventLoopGroup(1);
        //设置反应器线程组
        bootstrap.group(bossLoopGroup, workLoopGroup);
        //设置Nio类型的通道
        bootstrap.channel(NioServerSocketChannel.class);
        //设置监听端口
        bootstrap.localAddress(serverPort);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        //装配子通道流水线
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new NettyDiscardHandler());
            }
        });
        ChannelFuture channelFuture = bootstrap.bind().sync();
        ChannelFuture closeFuture = channelFuture.channel().closeFuture();
        closeFuture.sync();
    }

    public static void main(String[] args) {
        try {
            new NettyDiscardServerDemo(3333).runServer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
