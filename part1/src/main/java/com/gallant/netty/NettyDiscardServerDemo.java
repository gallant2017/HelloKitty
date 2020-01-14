package com.gallant.netty;

import com.google.common.base.Stopwatch;
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
        //创建反应器线程组
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workLoopGroup = new NioEventLoopGroup(1);


        try {
            //设置反应器线程组
            bootstrap.group(bossLoopGroup, workLoopGroup);
            //设置Nio类型的通道
            bootstrap.channel(NioServerSocketChannel.class);
            //设置监听端口
            bootstrap.localAddress(serverPort);
            //设置通道参数
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            //装配子通道流水线
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

                //有连接到达时创建一个通道
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    //向子通道流水线中添加handler处理器
                    socketChannel.pipeline().addLast(new NettyDiscardHandler());

                }
            });
            //开始绑定服务器
            //通过调用sync同步方法阻塞直到绑定成功
            ChannelFuture channelFuture = bootstrap.bind().sync();
            System.out.println("after----bootstrap.bind().sync()");
            //服务监听通道会一直等待通道关闭的异步任务结束
            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
            closeFuture.sync();
            System.out.println("after----channelFuture.channel().closeFuture()");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //关闭EventLoopGroup
            //释放所有资源包括创建的线程
            workLoopGroup.shutdownGracefully();
            bossLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        try {
            new NettyDiscardServerDemo(4000).runServer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
