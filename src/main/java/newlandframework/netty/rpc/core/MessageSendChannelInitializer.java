package newlandframework.netty.rpc.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @���� Rpc�ͻ��˹ܵ���ʼ��
 *
 *  @author FangQing
 *  @date 2016��7��24�� 
 *  @time ����11:47:09
 */
public class MessageSendChannelInitializer extends ChannelInitializer<SocketChannel> {

    //ObjectDecoder �ײ�Ĭ�ϼ̳а��������LengthFieldBasedFrameDecoder����ճ�������ʱ��
    //��Ϣͷ��ʼ��Ϊ�����ֶΣ�ռ��4���ֽڡ�������ڱ��ּ��ݵĿ���
    final public static int MESSAGE_LENGTH = 4;

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //ObjectDecoder�Ļ�����������LengthFieldBasedFrameDecoder�ı��ĸ�ʽ���ּ��ݡ���Ϊ�ײ�ĸ���LengthFieldBasedFrameDecoder
        //�ĳ�ʼ��������Ϊsuper(maxObjectSize, 0, 4, 0, 4);
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, MessageSendChannelInitializer.MESSAGE_LENGTH, 0, MessageSendChannelInitializer.MESSAGE_LENGTH));
        //����LengthFieldPrepender�����ObjectDecoder��Ϣ����ͷ
        pipeline.addLast(new LengthFieldPrepender(MessageSendChannelInitializer.MESSAGE_LENGTH));
        pipeline.addLast(new ObjectEncoder());
        //���ǵ��������ܣ�����weakCachingConcurrentResolver������ԡ�һ�����ʹ��:cacheDisabled����
        pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
        pipeline.addLast(new MessageSendHandler());
    }
}