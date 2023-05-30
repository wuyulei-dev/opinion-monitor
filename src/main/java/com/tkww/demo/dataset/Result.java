/*
 * @(#)R.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  May 13, 2020 li.dg          初版
 *
 */
package com.tkww.demo.dataset;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.tkww.demo.enums.ResultEnum;

/**
 * 统一结果返回
 * 
 * @author li.dg
 * @version 1.0 May 13, 2020
 */
@JsonInclude(Include.NON_NULL)
public class Result implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private Boolean success;

    private String code;

    private String message;

    protected int pageSize;

    protected int pageNum;

    protected long totalPage;

    protected long totalRow = -1;

    private Object jsonData;

    public static Result ok() {
        Result result = new Result();
        result.setSuccess(ResultEnum.SUCCESS.getSuccess());
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        return result;
    }

    public static Result error() {
        Result result = new Result();
        result.setSuccess(ResultEnum.UNKNOW_ERROR.getSuccess());
        result.setCode(ResultEnum.UNKNOW_ERROR.getCode());
        result.setMessage(ResultEnum.UNKNOW_ERROR.getMessage());
        return result;
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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(
        int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(
        int pageNum) {
        this.pageNum = pageNum;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(
        long totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(
        long totalRow) {
        this.totalRow = totalRow;
    }

    public Object getJsonData() {
        return jsonData;
    }

    public void setJsonData(
        Object jsonData) {
        this.jsonData = jsonData;
    }

}
