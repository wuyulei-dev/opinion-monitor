/*
 * @(#)ExcelUploadUtil.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2023年5月30日 wwp-pc          初版
 *
 */
package com.tkww.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.monitorjbl.xlsx.StreamingReader;

/**
 * excel工具类
 * 
 * @author wwp-pc
 */
public class ExcelUploadUtil {

      public static void uploadSheetData(String filePath,String sheetName) throws Exception  {

            try {
                long start = System.currentTimeMillis();
                InputStream inputStream = new FileInputStream(new File(filePath));
         
                Workbook workbook = StreamingReader.builder()
                        .rowCacheSize(100)    // 读取时内存中的行数 (defaults to 10)
                        .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
                        .open(inputStream);            // 文件流或文件
       
                Sheet sheet = workbook.getSheet(sheetName);
                List<Row> rowData=new ArrayList<>(5);
                for (Row r : sheet) {
                    for (int i = 0; i < r.getLastCellNum(); i++) {
                        String value = r.getCell(i).getStringCellValue();
                    }
                    rowData.add(r);
                    
                    if(rowData.size()>5) {
                        System.out.println("存储数据");
                        rowData.clear();
                    }
                }
        
                rowData.forEach((r)->{
                    for (int i = 0; i < r.getLastCellNum(); i++) {
                        String value = r.getCell(i).getStringCellValue();
                        System.out.println(value);
                    }
                });
                
                workbook.close();
                
                long end = System.currentTimeMillis();
                System.out.println("用时： ===="+((end-start)/1000));
            }
            catch (FileNotFoundException e) {
                throw new Exception("TODO", e);
            }
            catch (IOException e) {
                throw new Exception("TODO", e);
            }
        
        }
}
