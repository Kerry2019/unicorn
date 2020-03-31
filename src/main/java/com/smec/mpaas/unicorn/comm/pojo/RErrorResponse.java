package com.smec.mpaas.unicorn.comm.pojo;



import java.io.Serializable;

/**
 * RESTful Error 返回类
 */
public class RErrorResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String error;


    public static RErrorResponse error(String error){
        return new RErrorResponse(error);
    }
    public RErrorResponse(){

    }

    public RErrorResponse(String error){
        this.error=error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
