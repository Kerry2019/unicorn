package com.smec.mpaas.unicorn.exception;

import com.smec.mpaas.unicorn.pojo.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: kerry.wu
 * @since: 2020/3/6 23:00
 * @history: 1.2020/3/6 created by kerry.wu
 */
@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(MPaasBusinessException.class)
    @ResponseBody
    public Response businessExceptionHandler(Exception e){
        return Response.fail(e.getMessage());
    }
}