package com.smec.mpaas.unicorn.exception;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2020/3/6 22:59
 * @history: 1.2020/3/6 created by kerry.wu
 */
public class MPaasBusinessException extends RuntimeException {

    public MPaasBusinessException(){

    }

    public MPaasBusinessException(String message){
        super(message);
    }
}
