package newlandframework.netty.rpc.core;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

import newlandframework.netty.rpc.model.MessageRequest;

/**
 * @���� Rpc�ͻ�����Ϣ����
 *
 *  @author FangQing
 *  @date 2016��7��24�� 
 *  @time ����11:42:24
 */
public class MessageSendProxy<T> implements InvocationHandler {

    private Class<T> cls;

    public MessageSendProxy(Class<T> cls) {
        this.cls = cls;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MessageRequest request = new MessageRequest();
        request.setMessageId(UUID.randomUUID().toString());
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setTypeParameters(method.getParameterTypes());
        request.setParameters(args);

        MessageSendHandler handler = RpcServerLoader.getInstance().getMessageSendHandler();
        MessageCallBack callBack = handler.sendRequest(request);
        return callBack.start();
    }
}