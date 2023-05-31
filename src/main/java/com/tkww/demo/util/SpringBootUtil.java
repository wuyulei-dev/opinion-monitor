/*
 * @(#)SpringUtil.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  May 26, 2020 li_yi_neu          初版
 *
 */
package com.tkww.demo.util;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * springboot工具类
 * 
 * @author li_yi_neu
 * @version 1.0 May 25, 2020
 */
@Component
public class SpringBootUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(
        ApplicationContext applicationContext) throws BeansException {
        if (SpringBootUtil.applicationContext == null) {
            SpringBootUtil.applicationContext = applicationContext;
        }
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(
        String name) {
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(
        Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(
        String name,
        Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 获取当前Request
     * 
     * @return HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        if ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes() != null) {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }
        else {
            return null;
        }
    }

}
