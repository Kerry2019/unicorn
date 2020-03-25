package com.smec.mpaas.unicorn.comm.exception;


public class MPaasBusinessException extends RuntimeException {
    /**
     * 自定义异常，状态码
     */
    public static final int STATUS_CODE=1000;

    public MPaasBusinessException(){

    }

    public MPaasBusinessException(String message){
        super(message);
    }


}
