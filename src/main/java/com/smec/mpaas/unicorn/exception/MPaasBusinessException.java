package com.smec.mpaas.unicorn.exception;


public class MPaasBusinessException extends RuntimeException {

    public MPaasBusinessException(){

    }

    public MPaasBusinessException(String message){
        super(message);
    }
}
