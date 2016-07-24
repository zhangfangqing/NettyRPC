package com.fangqing.zkrpc;

/**
 * @功能 ZK相关常量
 *
 *  @author FangQing
 *  @date 2016年6月25日 
 *  @time 下午5:52:09
 */
public interface Constant {

    int ZK_SESSION_TIMEOUT = 5000;

    String ZK_REGISTRY_PATH = "/registry";
    
    String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/data";
}