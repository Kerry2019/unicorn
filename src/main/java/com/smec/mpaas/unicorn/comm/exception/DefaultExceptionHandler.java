package com.smec.mpaas.unicorn.comm.exception;

import com.smec.mpaas.unicorn.comm.pojo.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(MPaasBusinessException.class)
    @ResponseBody
    public Response businessExceptionHandler(Exception e){
        return Response.fail(e.getMessage());
    }
}