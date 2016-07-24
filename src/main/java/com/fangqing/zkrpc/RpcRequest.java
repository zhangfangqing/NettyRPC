package com.fangqing.zkrpc;

/**
 * @功能 RPC请求
 *
 *  @author FangQing
 *  @date 2016年6月25日 
 *  @time 下午5:53:15
 */
public class RpcRequest {

    private String requestId;

    private String className;

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] parameters;

    public String getRequestId() {
	return requestId;
    }

    public void setRequestId(String requestId) {
	this.requestId = requestId;
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

    public Class<?>[] getParameterTypes() {
	return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
	this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
	return parameters;
    }

    public void setParameters(Object[] parameters) {
	this.parameters = parameters;
    }
}