package com.fangqing.zkrpc;

/**
 * @���� ZK��س���
 *
 *  @author FangQing
 *  @date 2016��6��25�� 
 *  @time ����5:52:09
 */
public interface Constant {

    int ZK_SESSION_TIMEOUT = 5000;

    String ZK_REGISTRY_PATH = "/registry";
    
    String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/data";
}