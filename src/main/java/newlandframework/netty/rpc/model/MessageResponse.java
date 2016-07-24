package newlandframework.netty.rpc.model;
import java.io.Serializable;

/**
 * @功能 RPC应答消息结构
 *
 *  @author FangQing
 *  @date 2016年7月24日 
 *  @time 上午11:23:56
 */
public class MessageResponse implements Serializable {

    private static final long serialVersionUID = -2707468829758720039L;
    private String messageId;
    private String error;
    private Object resultDesc;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getResult() {
        return resultDesc;
    }

    public void setResult(Object resultDesc) {
        this.resultDesc = resultDesc;
    }

    @Override
    public String toString() {
	return "MessageResponse [messageId=" + messageId + ", error=" + error + ", resultDesc=" + resultDesc + "]";
    }

}