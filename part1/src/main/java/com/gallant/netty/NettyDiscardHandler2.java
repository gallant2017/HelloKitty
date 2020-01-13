package com.gallant.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: Huang Junhao
 * @Date: 2020/1/9 20:37
 */
public class NettyDiscardHandler2 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("收到信息 Handler#2");
//        while (in.isReadable()) {
//            System.out.print((char) in.readByte());
//        }
    }
}
