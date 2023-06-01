/*
 * @(#)MyUserDetailService.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2022年7月28日 wwp-pc          初版
 *
 */
package com.tkww.demo.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.tkww.demo.dataset.UserDetail;
import com.tkww.demo.entity.User;

//用户请求登录接口时会调用
@Service
public class AjaxUserDetailService implements UserDetailsService{

  
    @Autowired
    private UserService userService;
    
    @Override
    public UserDetails loadUserByUsername(
        String loginName) throws UsernameNotFoundException {
        User user;
        try {
            user = userService.getUserByName(loginName);
        }
        catch (IOException e) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        
        //如果查询不到数据就抛出异常，由全局异常处理
        if(Objects.isNull(user)) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        //封装UserDetails对象并返回
        List<String> permissions = new ArrayList<String>();
        UserDetail userDetail = new UserDetail(user,permissions);
        return userDetail;
    }

}
