package org.tcat.parent.rpc.bean;

import java.io.Serializable;

/**
 * 封装 RPC 请求.
 * Created by Lin on 2017/4/26.
 */
public class RpcRequest implements Serializable {


    private static final long serialVersionUID = -891623008030698663L;

    /**
     * 请求id.
     */
    private String requestId;

    /**
     * 接口名称.
     */
    private String interfaceName;

    /**
     * 方法名称.
     */
    private String methodName;

    /**
     * 参数类属性.
     */
    private Class<?>[] parameterTypes;

    /**
     * 参数.
     */
    private Object[] parameters;

    /**
     * 请求id.
     *
     * @return 请求id.
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * 请求id.
     *
     * @param requestId 请求id.
     */
    public RpcRequest setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    /**
     * 接口名称.
     *
     * @return 接口名称.
     */
    public String getInterfaceName() {
        return interfaceName;
    }

    /**
     * 接口名称.
     *
     * @param interfaceName 接口名称.
     */
    public RpcRequest setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
        return this;
    }

    /**
     * 方法名称.
     *
     * @return 方法名称.
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * 方法名称.
     *
     * @param methodName 方法名称.
     */
    public RpcRequest setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    /**
     * 参数类属性.
     *
     * @return 参数类属性.
     */
    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    /**
     * 参数类属性.
     *
     * @param parameterTypes 参数类属性.
     */
    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    /**
     * 参数.
     *
     * @return 参数.
     */
    public Object[] getParameters() {
        return parameters;
    }

    /**
     * 参数.
     *
     * @param parameters 参数.
     */
    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

}
