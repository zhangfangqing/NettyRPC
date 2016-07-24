package newlandframework.netty.rpc.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import java.util.Map;

/**
 * @���� Rpc����˹ܵ���ʼ��
 *
 *  @author FangQing
 *  @date 2016��7��24�� 
 *  @time ����11:49:02
 */
public class MessageRecvChannelInitializer extends ChannelInitializer<SocketChannel> {

    //ObjectDecoder �ײ�Ĭ�ϼ̳а��������LengthFieldBasedFrameDecoder����ճ�������ʱ��
    //��Ϣͷ��ʼ��Ϊ�����ֶΣ�ռ��4���ֽڡ�������ڱ��ּ��ݵĿ���
    final public static int MESSAGE_LENGTH = 4;
    private Map<String, Object> handlerMap = null;

    MessageRecvChannelInitializer(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //ObjectDecoder�Ļ�����������LengthFieldBasedFrameDecoder�ı��ĸ�ʽ���ּ��ݡ���Ϊ�ײ�ĸ���LengthFieldBasedFrameDecoder
        //�ĳ�ʼ��������Ϊsuper(maxObjectSize, 0, 4, 0, 4); 
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, MessageRecvChannelInitializer.MESSAGE_LENGTH, 0, MessageRecvChannelInitializer.MESSAGE_LENGTH));
        //����LengthFieldPrepender�����ObjectDecoder��Ϣ����ͷ
        pipeline.addLast(new LengthFieldPrepender(MessageRecvChannelInitializer.MESSAGE_LENGTH));
        pipeline.addLast(new ObjectEncoder());
        //���ǵ��������ܣ�����weakCachingConcurrentResolver������ԡ�һ�����ʹ��:cacheDisabled����
        pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
        pipeline.addLast(new MessageRecvHandler(handlerMap));
    }
}