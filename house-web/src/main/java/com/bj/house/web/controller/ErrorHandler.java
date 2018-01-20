package com.bj.house.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理器
 * Created by BJ on 2018/1/20.
 */

@ControllerAdvice//Controller的辅助类
public class ErrorHandler {

    public static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    //实际上只需定义一个Exception.class就可以，不过这个value是可以定义多个异常的
    @ExceptionHandler(value = {Exception.class,RuntimeException.class})
    public String error500(HttpServletRequest request,Exception e){
        logger.error(e.getMessage(),e);
        logger.error(request.getRequestURL()+" encounter 500");
        return "error/500";
    }

}
