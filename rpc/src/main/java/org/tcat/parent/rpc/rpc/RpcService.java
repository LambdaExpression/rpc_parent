package org.tcat.parent.rpc.rpc;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import javax.net.ssl.SSLException;
import java.security.cert.CertificateException;

/**
 * RPC 服务器
 * Created by Lin on 2017/4/26.
 */
public class RpcService {

    private static final boolean SSL = System.getProperty("ssl") != null;
    private static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

    public static void statr() {
        // 配置SSL.
        final SslContext sslCtx;
        if (SSL) {
            try {
                SelfSignedCertificate ssc = new SelfSignedCertificate();
                sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
            } catch (CertificateException e) {
                throw new RuntimeException("连接证书异常", e);
            } catch (SSLException e) {
                throw new RuntimeException("SLL 异常 ", e);
            }
        } else {
            sslCtx = null;
        }

        EventLoopGroup bossGroup = new NioEventLoopGroup(50);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new RpcServiceInitializer(sslCtx));

            // 绑定并开始接收连接.
            b.bind(PORT).sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException("服务端响应中断", e);
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
