package com.smec.mpaas.unicorn.comm.exception;

/**
 * Wrapper Response Exception
 */
public class WPaasBusinessException extends RuntimeException {

    public WPaasBusinessException(){
    }
    public WPaasBusinessException(String message){
        super(message);
    }

}