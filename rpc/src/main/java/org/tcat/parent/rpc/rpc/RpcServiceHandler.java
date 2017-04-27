package org.tcat.parent.rpc.rpc;

import com.google.gson.GsonBuilder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.tcat.parent.rpc.bean.RpcRequest;
import org.tcat.parent.rpc.bean.RpcResponse;

/**
 * Created by Lin on 2017/4/26.
 */
public class RpcServiceHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 处理接收到的对象，并返回结果对象给客户端.
        if (msg instanceof RpcRequest) {
            System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(msg));
            ctx.write(new RpcResponse());
        }
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
