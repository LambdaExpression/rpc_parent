package org.tcat.parent.rpc.rpc;

import com.google.gson.Gson;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.tcat.parent.rpc.bean.RpcRequest;
import org.tcat.parent.rpc.bean.RpcResponse;

import javax.net.ssl.SSLException;

/**
 * Created by Lin on 2017/4/26.
 */
public class RpcClient {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

    public static RpcResponse sendRequest(RpcRequest rpcRequest) {

        // 配置SSL.
        final SslContext sslCtx;
        if (SSL) {
            try {
                sslCtx = SslContextBuilder.forClient()
                        .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
            } catch (SSLException e) {
                throw new RuntimeException("SLL 异常 ", e);
            }
        } else {
            sslCtx = null;
        }

        EventLoopGroup group = new NioEventLoopGroup();
        RpcResponse rpcResponse;
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new RpcClientInitializer(sslCtx));

            Channel channel = b.connect(HOST, PORT).sync().channel();
            channel.writeAndFlush(rpcRequest);
            channel.closeFuture().sync().channel();
            RpcClientHandler handler = (RpcClientHandler) channel.pipeline().last();
            Object result = handler.getMsg();
            if (result instanceof RpcResponse) {
                rpcResponse = (RpcResponse) result;
            } else {
                throw new RuntimeException("result 类型异常 \nresultClass:=" + result.getClass()
                        + "\nresult=" + new Gson().toJson(result));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("客户端请求中断", e);
        } finally {
            group.shutdownGracefully();
        }
        return rpcResponse;
    }

}
