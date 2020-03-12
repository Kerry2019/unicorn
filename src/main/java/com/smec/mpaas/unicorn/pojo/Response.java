package com.smec.mpaas.unicorn.pojo;

import java.io.Serializable;


public class Response implements Serializable{
    private static final long serialVersionUID = 1L;

    public static final String CODE_OK = "ok";
    public static final String CODE_ERR = "error";

    private String code;
    private String errorMsg;
    private Object data;


    public Response(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public Response(){
        this(CODE_OK,null);
    }

    public static Response fail(String errorMsg){
        return new Response(CODE_ERR, errorMsg);
    }
    public static Response ok() {
        return new Response();
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public Response data(Object data) {
        return this.setData(data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Object getData() {
        return data;
    }
}

