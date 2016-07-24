package newlandframework.netty.rpc.core;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @功能 rpc线程池封装
 *
 *  @author FangQing
 *  @date 2016年7月24日 
 *  @time 上午11:30:00
 */
public class RpcThreadPool {

    /**
     * @功能 
     * 独立出线程池主要是为了应对复杂耗I/O操作的业务，不阻塞netty的handler线程而引入;
     * 当然如果业务足够简单，把处理逻辑写入netty的handler（ChannelInboundHandlerAdapter）也未尝不可
     *
     *  @author FangQing
     *  @date 2016年7月24日 
     *  @time 上午11:35:47
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
