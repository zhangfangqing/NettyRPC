package com.fangqing.zkrpc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @���� RPC�����������
 *
 * @author FangQing
 * @date 2016��6��25��
 * @time ����5:37:22
 */
public class RpcBootstrap {

    /**
     * @���� TODO
     *
     * @author FangQing
     * @date 2016��6��25��
     * @time ����5:37:14
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {
	new ClassPathXmlApplicationContext("spring-zk-rpc-server.xml");
    }
}