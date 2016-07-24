package newlandframework.netty.rpc.core;
import java.lang.reflect.Proxy;

/**
 * @���� Rpc�ͻ���ִ��ģ��
 *  �����RPC�ͻ���ʵ���ϣ��Ƕ�̬������MessageSendProxy����Ȼ������Ӧ����JDKԭ���Ķ�̬����ʵ�֣��㻹���Ըĳ�CGLIB��Code Generation Library����ʽ��
 *  �������˲�����һ��CGLIB��ʽ���ڸ߲���������������ֿ�ָ���쳣������ͬ���������JDKԭ���Ķ�̬����ȴû�����⡣�����̶Ȳ��ߵ�������棬���ִ���ʽ������������
 *  
 *  @author FangQing
 *  @date 2016��7��24�� 
 *  @time ����11:39:44
 */
public class MessageSendExecutor {

    private RpcServerLoader loader = RpcServerLoader.getInstance();

    public MessageSendExecutor(String serverAddress) {
        loader.load(serverAddress);
    }

    public void stop() {
        loader.unLoad();
    }

    /**
     * @���� TODO
     *
     *  @author FangQing
     *  @date 2016��7��24�� 
     *  @time ����12:17:41
     */
    @SuppressWarnings("unchecked")
    public static <T> T execute(Class<T> rpcInterface) {
        return (T) Proxy.newProxyInstance(
                rpcInterface.getClassLoader(),
                new Class<?>[]{rpcInterface},
                new MessageSendProxy<T>(rpcInterface)
        );
    }
}