package newlandframework.netty.rpc.model;
import java.util.Map;

/**
 * @����  RPC����ӿڶ��塢����ӿ�ʵ�ְ󶨹�ϵ�������壬�ṩ��spring��Ϊ����ʹ�á�
 *
 *  @author FangQing
 *  @date 2016��7��24�� 
 *  @time ����11:25:40
 */
public class MessageKeyVal {

    private Map<String, Object> messageKeyVal;

    public void setMessageKeyVal(Map<String, Object> messageKeyVal) {
        this.messageKeyVal = messageKeyVal;
    }

    public Map<String, Object> getMessageKeyVal() {
        return messageKeyVal;
    }
}