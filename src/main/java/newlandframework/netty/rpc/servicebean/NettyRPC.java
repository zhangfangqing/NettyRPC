package newlandframework.netty.rpc.servicebean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @功能 TODO
 *
 *  @author FangQing
 *  @date 2016年7月24日 
 *  @time 下午12:32:14
 */
public class NettyRPC {
    
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
	new ClassPathXmlApplicationContext("rpc-invoke-config.xml");
    }

}
