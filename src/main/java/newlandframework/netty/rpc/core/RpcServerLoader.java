package newlandframework.netty.rpc.core;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.net.InetSocketAddress;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @���� rpc���������ü���
 *
 *  @author FangQing
 *  @date 2016��7��24�� 
 *  @time ����11:44:03
 */
public class RpcServerLoader {

    private volatile static RpcServerLoader rpcServerLoader;
    private final static String DELIMITER = ":";

    //�������ص�Java������Ŀ��õĴ���������
    private final static int parallel = Runtime.getRuntime().availableProcessors() * 2;
    //netty nio�̳߳�
    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup(parallel);
    private static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) RpcThreadPool.getExecutor(16, -1);
    private MessageSendHandler messageSendHandler = null;

    //�ȴ�Netty�������·����֪ͨ�ź�
    private Lock lock = new ReentrantLock();
    private Condition signal = lock.newCondition();

    private RpcServerLoader() {
    }

    //����˫������
    public static RpcServerLoader getInstance() {
        if (rpcServerLoader == null) {
            synchronized (RpcServerLoader.class) {
                if (rpcServerLoader == null) {
                    rpcServerLoader = new RpcServerLoader();
                }
            }
        }
        return rpcServerLoader;
    }

    public void load(String serverAddress) {
        String[] ipAddr = serverAddress.split(RpcServerLoader.DELIMITER);
        if (ipAddr.length == 2) {
            String host = ipAddr[0];
            int port = Integer.parseInt(ipAddr[1]);
            final InetSocketAddress remoteAddr = new InetSocketAddress(host, port);

            threadPoolExecutor.submit(new MessageSendInitializeTask(eventLoopGroup, remoteAddr, this));
        }
    }

    public void setMessageSendHandler(MessageSendHandler messageInHandler) {
        try {
            lock.lock();
            this.messageSendHandler = messageInHandler;
            //�������еȴ��ͻ���RPC�߳�
            signal.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public MessageSendHandler getMessageSendHandler() throws InterruptedException {
        try {
            lock.lock();
            //Netty�������·û�н������֮ǰ���ȹ���ȴ�
            if (messageSendHandler == null) {
                signal.await();
            }
            return messageSendHandler;
        } finally {
            lock.unlock();
        }
    }

    public void unLoad() {
        messageSendHandler.close();
        threadPoolExecutor.shutdown();
        eventLoopGroup.shutdownGracefully();
    }

}