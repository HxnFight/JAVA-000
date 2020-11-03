package cn.valjean.gateway.outbound.netty4;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.URI;

public class NettyHttpClient {
    private URI uri;

    public static void main(String[] args) throws Exception {
        NettyHttpClient client = new NettyHttpClient();
        client.uri = new URI("/hello");
        client.connect("localhost", 8080);
    }

    public void connect(String host, int port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<Channel>() {
                @Override
                public void initChannel(Channel ch) throws Exception {

                    ch.pipeline().addLast(new HttpResponseDecoder());
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(new HttpClientCodec());
                    ch.pipeline().addLast(new HttpObjectAggregator(65536));
                    ch.pipeline().addLast(new HttpContentDecompressor());

                    ch.pipeline().addLast(new NettyHttpClientOutboundHandler(uri));
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();
//            f.channel().flush();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }
}
