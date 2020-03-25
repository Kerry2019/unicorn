package com.smec.mpaas.unicorn.comm.exception;

import com.smec.mpaas.unicorn.comm.pojo.ErrorResponse;
import com.smec.mpaas.unicorn.comm.pojo.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;


@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(MPaasBusinessException.class)
   // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse businessExceptionHandler(HttpServletResponse response,MPaasBusinessException e){
        response.setStatus(MPaasBusinessException.STATUS_CODE);
        return ErrorResponse.error(e.getMessage());
    }
}