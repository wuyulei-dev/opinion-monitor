/*
 * @(#)asdfjsdf.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2023年5月23日 wwp-pc          初版
 *
 */
package com.tkww.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.monitorjbl.xlsx.StreamingReader;

public class asdfjsdf {

    public static void main(
        String[] args) throws Exception {
        long start = System.currentTimeMillis();
        InputStream is;
        try {
            is = new FileInputStream(new File("E:\\大量数据.xlsx"));
        }
        catch (FileNotFoundException e) {
            throw new Exception("TODO", e);
        }
        Workbook workbook = StreamingReader.builder()
                .rowCacheSize(100)    // 读取时内存中的行数 (defaults to 10)
                .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
                .open(is);            // 文件流或文件
     
        Sheet sheet = workbook.getSheet("Twitter");
//        System.out.println(sheet.getSheetName());
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
//          for (Cell c : r) {
//            System.out.println(c.getStringCellValue());
//          }
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

}
