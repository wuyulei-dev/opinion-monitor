/*
 * @(#)TokenFilter.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2023年5月31日 wwp-pc          初版
 *
 */
package com.tkww.demo.filter;

import java.io.IOException;
import java.util.ArrayList;
import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tkww.demo.dataset.Result;
import com.tkww.demo.dataset.UserDetail;
import com.tkww.demo.entity.User;
import com.tkww.demo.enums.ResultEnum;
import com.tkww.demo.service.UserService;
import com.tkww.demo.util.JwtUtils;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import io.jsonwebtoken.Claims;

public class TokenFilter extends OncePerRequestFilter {

    private UserService userService;

    //注入userService
    public TokenFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("authenticateAction");
        if (StrUtil.isEmpty(token)) {
            //没有token，走其他过滤器，未认证会阻止访问
            filterChain.doFilter(request, response);

            //过滤器链执行完毕后，直接返回
            return;
        }
        //解析token
        Claims claims = JwtUtils.parseToken(token);
        
        //解析失败不会被全局异常捕获，亦不会被security捕获异常处理 ，直接返回错误信息即可
        if (ObjectUtil.isEmpty(claims)) {
            ObjectMapper objectMapper = new ObjectMapper();
            Result error = Result.error();
            error.setMessage("token非法！");
            response.setStatus(HttpStatus.HTTP_NOT_AUTHORITATIVE);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("utf-8");
            String errorValue = objectMapper.writeValueAsString(error);
            //todo 给用户颁发token
            response.getWriter().print(errorValue);
            return;
        }

        //获取用户信息
        String userId = (String) claims.get("userId");
        User currentUser = userService.getUserById(userId);
        if (ObjectUtil.isEmpty(currentUser)) {
            //解析失败,抛出异常
            ObjectMapper objectMapper = new ObjectMapper();
            Result error = Result.error();
            error.setMessage("账户不可用！");
            response.setStatus(HttpStatus.HTTP_NOT_AUTHORITATIVE);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("utf-8");
            String errorValue = objectMapper.writeValueAsString(error);
            //todo 给用户颁发token
            response.getWriter().print(errorValue);
            return;
        }
        
        //存入SecurityContext，后面过滤器放行
        UserDetail userDetail = new UserDetail(currentUser, new ArrayList<String>());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetail,
                userDetail.getPassword(), userDetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        
        filterChain.doFilter(request, response);
    }

}
