/*
 * @(#)WebSecurityConfig.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2022年6月21日 wwp-pc          初版
 *
 */
package com.tkww.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.tkww.demo.filter.AjaxAuthenticationFilter;
import com.tkww.demo.filter.TokenFilter;
import com.tkww.demo.security.AjaxAuthenticationEntryPoint;
import com.tkww.demo.service.UserService;

//开启基于注解的权限控制
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 不需要权限过滤资源定义
     */
    public static final String[] IGNORING_RESOURCES = { "/static/**" //静态资源
    };

    @Autowired
    private AjaxAuthenticationEntryPoint ajaxAuthenticationEntryPoint;

    @Autowired
    private UserService userService;

    //其他地方要使用时 定义
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(
        WebSecurity web) throws Exception {
        //静态资源配置，不经secrity过滤器，不被拦截
        web.ignoring().antMatchers("/js/**");
    }

    @Override
    protected void configure(
        HttpSecurity http) throws Exception {

        //前后端分离
        http.authorizeRequests() //开始权限配置
                .antMatchers("/doLogin").permitAll() //单点登录接口放行（所匹配的URL 任何人都允许访问）
                .antMatchers("/api/*").permitAll()
                .anyRequest().authenticated() //除以上配置,其他所有访问都需要认证，都需登录后才能访问
                .and()
                //不通过session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();

        http.exceptionHandling().authenticationEntryPoint(ajaxAuthenticationEntryPoint);

        //ajax Filter加入过滤器链
        http.addFilterBefore(ajaxAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(tokenFilter(), AjaxAuthenticationFilter.class);
    }

    @Bean
    public AjaxAuthenticationFilter ajaxAuthenticationFilter() {
        AjaxAuthenticationFilter ajaxAuthenticationFilter = new AjaxAuthenticationFilter();
        return ajaxAuthenticationFilter;
    }

    @Bean
    public TokenFilter tokenFilter() {
        TokenFilter tokenFilter = new TokenFilter(userService);
        return tokenFilter;
    }
}
