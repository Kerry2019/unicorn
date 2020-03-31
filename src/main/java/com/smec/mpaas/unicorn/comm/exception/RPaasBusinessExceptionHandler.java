package com.smec.mpaas.unicorn.comm.exception;

import com.smec.mpaas.unicorn.comm.pojo.RErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


@ControllerAdvice
public class RPaasBusinessExceptionHandler {

    @ExceptionHandler(RPaasBusinessException.class)
    @ResponseBody
    public RErrorResponse businessExceptionHandler(HttpServletResponse response, RPaasBusinessException e){
        response.setStatus(RPaasBusinessException.STATUS_CODE);
        return RErrorResponse.error(e.getMessage());
    }
}