package com.fangqing.zkrpc;

import com.fangqing.util.DateUtil;

/**
 * @���� �ӿ�ʵ����
 *
 * @author FangQing
 * @date 2016��6��25��
 * @time ����10:52:54
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
