package com.smec.mpaas.unicorn.comm.exception;

import com.smec.mpaas.unicorn.comm.pojo.WResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class WPaasBusinessExceptionHandler {

    @ExceptionHandler(WPaasBusinessException.class)
    @ResponseBody
    public WResponse businessExceptionHandler(RPaasBusinessException e){
        return WResponse.fail(e.getMessage());
    }
}
