package com.fangqing.zkrpc;

/**
 * @功能 TODO
 *
 * @author FangQing
 * @date 2016年6月25日
 * @time 上午10:50:52
 */
public interface HelloService {

    /**
     * @功能 输出字符串
     *
     * @author FangQing
     * @date 2016年6月25日
     * @time 上午10:51:17
     */
    public String hello(String name);

    /**
     * @功能 计算两个数的和
     *
     * @author FangQing
     * @date 2016年6月25日
     * @time 上午10:51:35
     */
    public int add(int a, int b);

}
