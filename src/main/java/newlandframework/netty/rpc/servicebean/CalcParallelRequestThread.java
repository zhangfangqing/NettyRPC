package newlandframework.netty.rpc.servicebean;
import newlandframework.netty.rpc.core.MessageSendExecutor;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @����   �����߳�ģ��
 *
 *  @author FangQing
 *  @date 2016��7��24�� 
 *  @time ����12:14:42
 */
public class CalcParallelRequestThread implements Runnable {

    private CountDownLatch signal;
    private CountDownLatch finish;
    private MessageSendExecutor executor;
    private int taskNumber = 0;

    public CalcParallelRequestThread(MessageSendExecutor executor, CountDownLatch signal, CountDownLatch finish, int taskNumber) {
        this.signal = signal;
        this.finish = finish;
        this.taskNumber = taskNumber;
        this.executor = executor;
    }

    /**
     * 
     */
    @SuppressWarnings("static-access")
    public void run() {
        try {
            signal.await();
            for(int i=0 ; i<1000 ; i++){
                Calculate calc = executor.execute(Calculate.class);
                int add = calc.add(taskNumber, taskNumber);
                System.out.println("calc add result:[" + add + "]");
            }
            finish.countDown();
        } catch (InterruptedException ex) {
            Logger.getLogger(CalcParallelRequestThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}