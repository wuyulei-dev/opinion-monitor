/*
 * @(#)UserService.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2023年5月30日 wwp-pc          初版
 *
 */
package com.tkww.demo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.monitorjbl.xlsx.StreamingReader;
import com.tkww.demo.dao.TwitterRepository;
import com.tkww.demo.entity.Twitter;
import cn.hutool.core.util.ObjectUtil;

@Service
public class TwitterService {

    private final String INDEX="Twitter";
    @Autowired
    private RestHighLevelClient client;
    
    
    @Autowired
    private TwitterRepository twitterRepository;
    
    public void uploadTwitterData(String filePath,String sheetName) throws Exception {

        try {
            long start = System.currentTimeMillis();
            InputStream inputStream = new FileInputStream(new File(filePath));
 
            Workbook workbook = StreamingReader.builder()
                    .rowCacheSize(100)    // 读取时内存中的行数 (defaults to 10)
                    .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
                    .open(inputStream);            // 文件流或文件

            Sheet sheet = workbook.getSheet(sheetName);
            List<Twitter> twitterList=new ArrayList<>(5);
            for (Row r : sheet) {
                if(r.getRowNum()==0) {
                    continue;
                }
                //行
                Twitter twitter = new Twitter();
                twitter.setCreateDate(new Date());
                twitter.setUpdateDate(new Date());
                twitter.setId(Math.random()+"");
                for (int i = 0; i < r.getLastCellNum(); i++) {
                    //列
                    String cellValue="";
                    Cell cell = r.getCell(i);
                    if(!ObjectUtil.isEmpty(cell)) {
                        cellValue=cell.getStringCellValue();
                    }
                    
                    if(i==0) {
                        twitter.setMediaName(cellValue);
                    }
                    if(i==1) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = simpleDateFormat.parse(cellValue);
                        twitter.setDate(date);
                    }
                    if(i==2) {
                        twitter.setTitle(cellValue);
                    }
                    if(i==3) {
                        twitter.setContent(cellValue);
                    }
                    if(i==4) {
                        twitter.setLink(cellValue);
                    }
                    if(i==5) {
                        twitter.setImgUrl(cellValue);
                    } 
                }
               
                twitterList.add(twitter);
                
                if(twitterList.size()>5) {
                    Iterable<Twitter> saveAll = twitterRepository.saveAll(twitterList);
                    twitterList.clear();
                }
            }
            
            //导入剩下的数据
            if(twitterList.size()>0) {
                Iterable<Twitter> saveAll = twitterRepository.saveAll(twitterList);
                twitterList.clear();
            }
            
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
