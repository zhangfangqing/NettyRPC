package com.fangqing.zkrpc;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @功能 RPC服务启动入口
 *
 * @author FangQing
 * @date 2016年6月25日
 * @time 下午5:37:22
 */
public class RpcBootstrap {

    /**
     * @功能 TODO
     *
     * @author FangQing
     * @date 2016年6月25日
     * @time 下午5:37:14
     */
    @SuppressWarnings("resource")
    public static void main(String[] args) {
	new ClassPathXmlApplicationContext("spring-zk-rpc-server.xml");
    }
}