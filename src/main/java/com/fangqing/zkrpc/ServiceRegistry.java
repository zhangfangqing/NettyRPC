package com.fangqing.zkrpc;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @���� ����ZKע�����ģ���������ע��Ŀ¼
 *
 * @author FangQing
 * @date 2016��6��25��
 * @time ����5:47:20
 */
public class ServiceRegistry {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRegistry.class);

    private CountDownLatch latch = new CountDownLatch(1);

    private String registryAddress;

    public ServiceRegistry(String registryAddress) {
	this.registryAddress = registryAddress;
    }

    /**
     * @���� TODO
     *
     * @author FangQing
     * @date 2016��6��25��
     * @time ����5:47:38
     */
    public void register(String data) {
	if (data != null) {
	    ZooKeeper zk = connectServer();
	    if (zk != null) {
		createNode(zk, data);
	    }
	}
    }

    /**
     * @���� TODO
     *
     * @author FangQing
     * @date 2016��6��25��
     * @time ����5:47:41
     */
    private ZooKeeper connectServer() {
	ZooKeeper zk = null;
	try {
	    zk = new ZooKeeper(registryAddress, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
		@Override
		public void process(WatchedEvent event) {
		    // �ж��Ƿ�������ZK,���Ӻ�������ݼ�.
		    if (event.getState() == Event.KeeperState.SyncConnected) {
			latch.countDown();
		    }
		}
	    });
	    // ����������Ϊ0,��ȴ�.
	    latch.await();
	} catch (IOException | InterruptedException e) {
	    LOGGER.error("", e);
	}
	return zk;
    }

    /**
     * @���� TODO
     *
     * @author FangQing
     * @date 2016��6��25��
     * @time ����5:47:41
     */
    private void createNode(ZooKeeper zk, String data) {
	try {
	    byte[] bytes = data.getBytes();
	    String path = zk.create(Constant.ZK_DATA_PATH, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
	    LOGGER.debug("create zookeeper node ({} => {})", path, data);
	} catch (KeeperException | InterruptedException e) {
	    LOGGER.error("", e);
	}
    }
}