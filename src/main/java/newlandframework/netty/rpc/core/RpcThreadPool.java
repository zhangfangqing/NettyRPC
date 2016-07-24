package newlandframework.netty.rpc.core;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @���� rpc�̳߳ط�װ
 *
 *  @author FangQing
 *  @date 2016��7��24�� 
 *  @time ����11:30:00
 */
public class RpcThreadPool {

    /**
     * @���� 
     * �������̳߳���Ҫ��Ϊ��Ӧ�Ը��Ӻ�I/O������ҵ�񣬲�����netty��handler�̶߳�����;
     * ��Ȼ���ҵ���㹻�򵥣��Ѵ����߼�д��netty��handler��ChannelInboundHandlerAdapter��Ҳδ������
     *
     *  @author FangQing
     *  @date 2016��7��24�� 
     *  @time ����11:35:47
     */
    public static Executor getExecutor(int threads, int queues) {
        String name = "RpcThreadPool";
        return new ThreadPoolExecutor(
        	threads, 
        	threads, 
        	0, 
        	TimeUnit.MILLISECONDS,
        	queues == 0 ? new SynchronousQueue<Runnable>(): (queues < 0 ? new LinkedBlockingQueue<Runnable>() : new LinkedBlockingQueue<Runnable>(queues)),
                new NamedThreadFactory(name, true),
                new AbortPolicyWithReport(name));
    }
}
