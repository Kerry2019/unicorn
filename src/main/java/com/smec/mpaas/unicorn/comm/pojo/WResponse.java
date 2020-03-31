package com.smec.mpaas.unicorn.comm.pojo;

import java.io.Serializable;

/**
 * wrapper response ，统一响应封装类
 */
public class WResponse implements Serializable{
    private static final long serialVersionUID = 1L;

    public static final String CODE_OK = "ok";
    public static final String CODE_ERR = "error";

    private String code;
    private String errorMsg;
    private Object data;


    public WResponse(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public WResponse(){
        this(CODE_OK,null);
    }

    public static WResponse fail(String errorMsg){
        return new WResponse(CODE_ERR, errorMsg);
    }
    public static WResponse ok() {
        return new WResponse();
    }

    public WResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public WResponse data(Object data) {
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

