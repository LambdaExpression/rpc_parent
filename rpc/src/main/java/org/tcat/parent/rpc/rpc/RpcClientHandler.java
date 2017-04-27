package org.tcat.parent.rpc.rpc;

import com.google.gson.GsonBuilder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Lin on 2017/4/26.
 */
public class RpcClientHandler extends ChannelInboundHandlerAdapter {

    private BlockingQueue<Object> answer = new LinkedBlockingQueue<>();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        if (msg instanceof Map) {
            System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(msg));
            answer.offer(msg);
            ctx.write(msg);
        }
        ctx.close();
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

    public Object getMsg() {
        boolean interrupted = false;
        try {
            while (true) {
                try {
                    return answer.take();
                } catch (InterruptedException ignore) {
                    interrupted = true;
                }
            }
        } finally {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
