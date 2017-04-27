package org.tcat.parent.rpc.bean;

import java.io.Serializable;

/**
 * 封装 rpc 响应.
 * Created by Lin on 2017/4/26.
 */
public class RpcResponse implements Serializable {

    private static final long serialVersionUID = 3148769939363451768L;

    /**
     * 请求id.
     */
    private String requestId;

    /**
     * 异常返回.
     */
    private Exception exception;

    /**
     * 响应结果.
     */
    private Object result;

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
    public RpcResponse setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    /**
     * 异常返回.
     *
     * @return 异常返回.
     */
    public Exception getException() {
        return exception;
    }

    /**
     * 异常返回.
     *
     * @param exception 异常返回.
     */
    public RpcResponse setException(Exception exception) {
        this.exception = exception;
        return this;
    }

    /**
     * 响应结果.
     *
     * @return 响应结果.
     */
    public Object getResult() {
        return result;
    }

    /**
     * 响应结果.
     *
     * @param result 响应结果.
     */
    public RpcResponse setResult(Object result) {
        this.result = result;
        return this;
    }
}
