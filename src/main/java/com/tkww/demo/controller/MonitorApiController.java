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

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 书架相关API
 * 
 * @author li.dg
 * @version 1.0 Jun 5, 2020
 */
@RestController
@RequestMapping("/api")
public class MonitorApiController {
    
    /**
     * PDF_JPG上传到oss
     * 
     * @param bookId 刊物ID
     * @param publishDate 发行日期。格式：20200601
     * @return
     */
//    @RequestMapping("/uploadExcel")
//    @BSLog(value = "PDF_JPG上传到oss", storeDb = true)
//    public Result epaperUpload(
//        @RequestParam(name = "bookId", required = true) String Excel) {
//        try {
//            Result uploadR = Result.ok();
//            if (StringUtils.equalsIgnoreCase(bookId, EPapaerConstant.BOOK_TYPE_WWP)) {
//                uploadR = fileUploadService
//                        .epaperUploadByWWP(bookId, publishDate, EPapaerConstant.EPAPER__FORCE_UPDATE_YES);
//            }
//          
//            return uploadR;
//        }
//        catch (Exception e) {
//            return Result.error().message(e.toString());
//        }
//    }
    
    
  @RequestMapping("/aa")
  public void  aa() {
      System.out.println("aa接口");
  }
}
