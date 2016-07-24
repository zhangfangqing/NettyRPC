package newlandframework.netty.rpc.model;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @功能 rpc服务请求结构
 *
 *  @author FangQing
 *  @date 2016年7月24日 
 *  @time 上午11:21:03
 */
public class MessageRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private String messageId;
    private String className;
    private String methodName;
    private Class<?>[] typeParameters;
    private Object[] parametersVal;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getTypeParameters() {
        return typeParameters;
    }

    public void setTypeParameters(Class<?>[] typeParameters) {
        this.typeParameters = typeParameters;
    }

    public Object[] getParameters() {
        return parametersVal;
    }

    public void setParameters(Object[] parametersVal) {
        this.parametersVal = parametersVal;
    }

    @Override
    public String toString() {
	return "MessageRequest [messageId=" + messageId + ", className=" + className + ", methodName=" + methodName + ", typeParameters=" + Arrays.toString(typeParameters) + ", parametersVal=" + Arrays.toString(parametersVal) + "]";
    }

   
}