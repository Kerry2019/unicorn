package com.smec.mpaas.unicorn.comm.exception;


public class MPaasBusinessException extends RuntimeException {

    public MPaasBusinessException(){

    }

    public MPaasBusinessException(String message){
        super(message);
    }
}
