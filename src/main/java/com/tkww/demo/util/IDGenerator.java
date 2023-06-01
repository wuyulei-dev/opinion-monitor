///*
// * @(#)IDGenerator.java
// * Copyright (C) 2020 Neusoft Corporation All rights reserved.
// *
// * VERSION        DATE       BY              CHANGE/COMMENT
// * ----------------------------------------------------------------------------
// * @version 1.00  May 12, 2020 li.dg          初版
// *
// */
//package com.tkww.demo.util;
//
//import com.neusoft.dgwh.epaper.config.properties.AppProperties;
//import cn.hutool.core.convert.Convert;
//import cn.hutool.core.lang.Singleton;
//import cn.hutool.extra.spring.SpringUtil;
//
///**
// * ID生成器
// * 
// * @author li.dg
// * @version 1.0 May 12, 2020
// */
//public class IDGenerator {
//
//    public static String nextId() {
//        AppProperties appProperties = SpringUtil.getBean(AppProperties.class);
//        //终端ID 数据中心ID
//        Long workerId = Convert.toLong(appProperties.getWorkerId(), 1L);
//        Long dataCenterId = Convert.toLong(appProperties.getDataCenterId(), 1L);
//        UnsafeSnowflake snowflake = Singleton.get(UnsafeSnowflake.class, workerId, dataCenterId, true);
//        return snowflake.nextIdStr();
//    }
//}
