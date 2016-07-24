package newlandframework.netty.rpc.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;

import newlandframework.netty.rpc.model.MessageKeyVal;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @���� Rpc������ִ��ģ��
 *
 *  @author FangQing
 *  @date 2016��7��24�� 
 *  @time ����11:48:21
 */
public class MessageRecvExecutor implements ApplicationContextAware, InitializingBean {

    private String serverAddress ;
    private final static String DELIMITER = ":";

    private Map<String, Object> handlerMap = new ConcurrentHashMap<String, Object>();

    private static ThreadPoolExecutor threadPoolExecutor;

    public MessageRecvExecutor(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public static void submit(Runnable task) {
        if (threadPoolExecutor == null) {
            synchronized (MessageRecvExecutor.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = (ThreadPoolExecutor) RpcThreadPool.getExecutor(16, -1);
                }
            }
        }
        threadPoolExecutor.submit(task);
    }

    /**
     * 
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        try {
            MessageKeyVal keyVal = (MessageKeyVal) ctx.getBean(Class.forName("newlandframework.netty.rpc.model.MessageKeyVal"));
            Map<String, Object> rpcServiceObject = keyVal.getMessageKeyVal();

            Set s = rpcServiceObject.entrySet();
            Iterator<Map.Entry<String, Object>> it = s.iterator();
            Map.Entry<String, Object> entry;

            while (it.hasNext()) {
                entry = it.next();
                handlerMap.put(entry.getKey(), entry.getValue());
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MessageRecvExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     */
    public void afterPropertiesSet() throws Exception {
        //netty���̳߳�ģ�����ó������̳߳�ģʽ����������Ӧ�Ը߲�������
        //��Ȼnetty��֧�ֵ��̡߳����߳�����IOģ�ͣ����Ը���ҵ�������������
        ThreadFactory threadRpcFactory = new NamedThreadFactory("NettyRPC ThreadFactory");
        
        //�������ص�Java������Ŀ��õĴ���������
        int parallel = Runtime.getRuntime().availableProcessors() * 2;
    
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup(parallel,threadRpcFactory,SelectorProvider.provider());
        
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
                    .childHandler(new MessageRecvChannelInitializer(handlerMap))
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            String[] ipAddr = serverAddress.split(MessageRecvExecutor.DELIMITER);

            if (ipAddr.length == 2) {
                String host = ipAddr[0];
                int port = Integer.parseInt(ipAddr[1]);
                ChannelFuture future = bootstrap.bind(host, port).sync();
                System.out.printf("[author tangjie] Netty RPC Server start success ip:%s port:%d\n", host, port);
                future.channel().closeFuture().sync();
            } else {
                System.out.printf("[author tangjie] Netty RPC Server start fail!\n");
            }
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }
}