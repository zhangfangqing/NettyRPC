package newlandframework.netty.rpc.core;
import java.lang.reflect.Proxy;

/**
 * @功能 Rpc客户端执行模块
 *  这里的RPC客户端实际上，是动态代理了MessageSendProxy，当然这里是应用了JDK原生的动态代理实现，你还可以改成CGLIB（Code Generation Library）方式。
 *  不过本人测试了一下CGLIB方式，在高并发的情况下面会出现空指针异常，但是同样的情况，JDK原生的动态代理却没有问题。并发程度不高的情况下面，两种代理方式都运行正常。
 *  
 *  @author FangQing
 *  @date 2016年7月24日 
 *  @time 上午11:39:44
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
     * @功能 TODO
     *
     *  @author FangQing
     *  @date 2016年7月24日 
     *  @time 下午12:17:41
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