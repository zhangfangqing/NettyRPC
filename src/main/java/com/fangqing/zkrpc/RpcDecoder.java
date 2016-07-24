package com.fangqing.zkrpc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @功能 RPC解码
 *
 *  @author FangQing
 *  @date 2016年6月25日 
 *  @time 下午5:52:34
 */
public class RpcDecoder extends ByteToMessageDecoder {

    private Class<?> genericClass;

    public RpcDecoder(Class<?> genericClass) {
	this.genericClass = genericClass;
    }

    @Override
    public void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
	if (in.readableBytes() < 4) {
	    return;
	}
	in.markReaderIndex();
	int dataLength = in.readInt();
	if (dataLength < 0) {
	    ctx.close();
	}
	if (in.readableBytes() < dataLength) {
	    in.resetReaderIndex();
	    return;
	}
	byte[] data = new byte[dataLength];
	in.readBytes(data);

	Object obj = SerializationUtil.deserialize(data, genericClass);
	out.add(obj);
    }
}