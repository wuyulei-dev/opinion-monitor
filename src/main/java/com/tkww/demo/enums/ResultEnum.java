/*
 * @(#)ResultEnum.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2023年5月30日 wwp-pc          初版
 *
 */
package com.tkww.demo.enums;

public enum ResultEnum {
    SUCCESS(true,"200","成功"),
    UNKNOW_ERROR(false,"20001", "未知错误"),
    UPLOAND_ERROR(false,"20002", "上传错误"),
    NULLPOINT_ERROR(false,"20003", "空指针异常");
    
    private Boolean success;

    private String code;

    private String message;
    
    
    private ResultEnum(Boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(
        Boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(
        String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(
        String message) {
        this.message = message;
    }
    
    
}
