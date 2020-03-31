package com.smec.mpaas.unicorn.comm.exception;

/**
 * RESTful 异常类
 */
public class RPaasBusinessException extends RuntimeException {
    /**
     * 自定义异常，状态码
     */
    public static final int STATUS_CODE=700;

    public RPaasBusinessException(){

    }
    public RPaasBusinessException(String message){
        super(message);
    }

}
