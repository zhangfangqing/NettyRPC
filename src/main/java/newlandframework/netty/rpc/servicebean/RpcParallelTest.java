package newlandframework.netty.rpc.servicebean;

import java.util.concurrent.CountDownLatch;

import newlandframework.netty.rpc.core.MessageSendExecutor;

import org.apache.commons.lang3.time.StopWatch;

/**
 * @����  rpc�������Դ���
 *
 *  @author FangQing
 *  @date 2016��7��24�� 
 *  @time ����12:15:37
 */
public class RpcParallelTest {

    public static void main(String[] args) throws Exception {
        final MessageSendExecutor executor = new MessageSendExecutor("127.0.0.1:18888");
        //���ж�10000
        int parallel = 10000;

        //��ʼ��ʱ
        StopWatch sw = new StopWatch();
        sw.start();

        CountDownLatch signal = new CountDownLatch(1);
        CountDownLatch finish = new CountDownLatch(parallel);

        for (int index = 0; index < parallel; index++) {
            CalcParallelRequestThread client = new CalcParallelRequestThread(executor, signal, finish, index);
            new Thread(client).start();
        }
        
        //10000�������߳�˲�䷢���������
        signal.countDown();
        finish.await();
        
        sw.stop();

        String tip = String.format("RPC�����ܹ���ʱ: [%s] ����", sw.getTime());
        System.out.println(tip);

        executor.stop();
    }
}