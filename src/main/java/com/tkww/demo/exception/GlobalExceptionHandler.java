/*
 * @(#)GlobalExceptionHandler.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2023年3月10日 wwp-pc          初版
 *
 */
package com.tkww.demo.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tkww.demo.dataset.Result;

/**
 * 全局异常处理 只能处理Controller中抛出的异常
 * 
 * @author wwp-pc
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result error(
        Exception e) {
        Result error = Result.error();
        error.setMessage("发生异常了" + e.toString());
        return error;
    }

    //采用注解权限会被全局异常先捕获
    @ExceptionHandler(value = AccessDeniedException.class)
    public Result accessDeniedException(
        AccessDeniedException accessDeniedException) {
        Result error = Result.error();
        error.setMessage("无权限访问！" + accessDeniedException.toString());
        return error;
    }
}
