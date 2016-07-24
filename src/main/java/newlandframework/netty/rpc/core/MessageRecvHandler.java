package newlandframework.netty.rpc.core;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.Map;
import newlandframework.netty.rpc.model.MessageRequest;
import newlandframework.netty.rpc.model.MessageResponse;

/**
 * @���� Rpc��������Ϣ����
 *
 *  @author FangQing
 *  @date 2016��7��24�� 
 *  @time ����11:52:19
 */
public class MessageRecvHandler extends ChannelInboundHandlerAdapter {

    private final Map<String, Object> handlerMap;

    public MessageRecvHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageRequest request = (MessageRequest) msg;
        MessageResponse response = new MessageResponse();
        MessageRecvInitializeTask recvTask = new MessageRecvInitializeTask(request, response, handlerMap, ctx);
        //��Ҫ����nio�̣߳����ӵ�ҵ���߼�����ר�ŵ��̳߳�
        MessageRecvExecutor.submit(recvTask);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //�������쳣Ҫ�ر�ͨ��
        ctx.close();
    }
}