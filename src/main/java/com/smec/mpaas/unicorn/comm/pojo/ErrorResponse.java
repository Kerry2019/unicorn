package com.smec.mpaas.unicorn.comm.pojo;

import java.io.Serializable;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2020/3/24 22:33
 * @history: 1.2020/3/24 created by kerry.wu
 */
public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String error;


    public static ErrorResponse error(String error){
        return new ErrorResponse(error);
    }
    public ErrorResponse(){

    }

    public ErrorResponse(String error){
        this.error=error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
