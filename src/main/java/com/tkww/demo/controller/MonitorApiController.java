/*
 * @(#)BookApiController.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  Jun 5, 2020 li.dg          初版
 *
 */
package com.tkww.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tkww.demo.dataset.Result;
import com.tkww.demo.service.TwitterService;

/**
 * 书架相关API
 * 
 * @author li.dg
 * @version 1.0 Jun 5, 2020
 */
@RestController
@RequestMapping("/api")
public class MonitorApiController {
    
    @Autowired
    private TwitterService twitterService;
    
    /**
     * excel上传到es
     * 
     * @param bookId 刊物ID
     * @param publishDate 发行日期。格式：20200601
     * @return
     */
    @RequestMapping("/uploadExcel")
    public Result uploadExcel(
        @RequestParam(name = "sheet", required = true) String sheet) {
        try {
            twitterService.uploadTwitterData("E:\\1231684804635.xlsx", sheet);
        }
        catch (Exception e) {
           return Result.error();
        }
        return Result.ok();
    }
    
    
  @RequestMapping("/aa")
  public void  aa() {
      System.out.println("aa接口");
  }
}
