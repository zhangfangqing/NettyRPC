package com.fangqing.zkrpc;

import com.fangqing.util.DateUtil;

/**
 * @功能 接口实现类
 *
 * @author FangQing
 * @date 2016年6月25日
 * @time 上午10:52:54
 */
@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
	return name + " , " + DateUtil.getStringDate(DateUtil.YYYYMMDDHHMMSS);
    }

    @Override
    public int add(int a, int b) {
	return a + b;
    }

}
