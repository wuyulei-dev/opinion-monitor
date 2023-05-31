/*
 * @(#)AjaxAuthenticationFilter.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2023年5月30日 wwp-pc          初版
 *
 */
package com.tkww.demo.filter;

import java.io.IOException;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tkww.demo.dataset.Result;
import com.tkww.demo.dataset.UserDetail;
import com.tkww.demo.util.JwtUtils;
import cn.hutool.http.HttpStatus;

/**
 * 自定义ajax、form登录认证
 * 
 * @author wwp-pc
 */
public class AjaxAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private boolean postOnly = true;
    private static final String USER_NAME = "userName";
    private static final String PASS_WORD = "passWord";

    public AjaxAuthenticationFilter() {
        this.setPostOnly(postOnly);
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/doLogin", "POST"));
    }

    @SuppressWarnings("finally")
    @Override
    public Authentication attemptAuthentication(
        HttpServletRequest request,
        HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authRequest = null;

        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String type = request.getContentType();
        if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
            //json登录认证
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                ServletInputStream inputStream = request.getInputStream();
                Map<String, String> paramValue = objectMapper.readValue(inputStream, Map.class);
                String username = paramValue.get(USER_NAME);
                String password = paramValue.get(PASS_WORD);
                username = (null != username) ? username.trim() : "";
                password = (null != password) ? password.trim() : "";
                authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
            }
            catch (Exception e) {
                e.printStackTrace();
                authRequest = new UsernamePasswordAuthenticationToken("", "");
            }
            finally {
                setDetails(request, authRequest);
                return this.getAuthenticationManager().authenticate(authRequest);
            }
        }
        else {
            //form表格登录认证
            return super.attemptAuthentication(request, response);
        }
    }

    //自定义认证成功处理
    @Override
    protected void successfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain,
        Authentication authResult) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        Result ok = Result.ok();
        ok.setMessage("用户登录成功");
        //todo 给用户颁发token
        UserDetail userDetail = (UserDetail) authResult.getPrincipal();
        String token = JwtUtils.getToken(userDetail.getSysUser().getId());
        ok.setJsonData(token);

        response.setStatus(HttpStatus.HTTP_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        String result = objectMapper.writeValueAsString(ok);
        response.getWriter().print(result);
    }

    //自定义认证失败处理
    @Override
    protected void unsuccessfulAuthentication(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException failed) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        Result error = Result.error();
        error.setMessage("用户登录失败！unsuccessfulAuthentication");
        response.setStatus(HttpStatus.HTTP_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        String errorValue = objectMapper.writeValueAsString(error);

        //todo 给用户颁发token
        response.getWriter().print(errorValue);
    }

    //  否则启动报错：authenticationManager must be specified
    @Autowired
    @Override
    public void setAuthenticationManager(
        AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

}
