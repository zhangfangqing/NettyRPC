package newlandframework.netty.rpc.servicebean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @���� TODO
 *
 *  @author FangQing
 *  @date 2016��7��24�� 
 *  @time ����12:32:14
 */
public class NettyRPC {
    
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
	new ClassPathXmlApplicationContext("rpc-invoke-config.xml");
    }

}
