package cn.valjean.gateway.outbound.netty4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Slf4j
public class NettyHttpClientOutboundHandler extends ChannelInboundHandlerAdapter {

    private URI uri;

    public NettyHttpClientOutboundHandler(URI uri) {
        this.uri = uri;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, uri.toASCIIString());
        request.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        request.headers().add(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        ctx.writeAndFlush(request);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        log.info("#msg : {}", msg);
        System.out.println("msg = " + msg);
        if (msg instanceof FullHttpResponse) {
            FullHttpResponse response = (FullHttpResponse) msg;
            ByteBuf content = response.content();
            String s = content.toString(StandardCharsets.UTF_8);
            System.out.println("s = " + s);
        } else {
            System.out.println("msg = " + msg);

        }
    }
}
