package com.fangqing.zkrpc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


/**
 * @功能 RPC编码
 *
 *  @author FangQing
 *  @date 2016年6月25日 
 *  @time 下午5:51:49
 */
@SuppressWarnings("rawtypes")
public class RpcEncoder extends MessageToByteEncoder {

    private Class<?> genericClass;

    public RpcEncoder(Class<?> genericClass) {
	this.genericClass = genericClass;
    }

    @Override
    public void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
	if (genericClass.isInstance(in)) {
	    byte[] data = SerializationUtil.serialize(in);
	    out.writeInt(data.length);
	    out.writeBytes(data);
	}
    }
}