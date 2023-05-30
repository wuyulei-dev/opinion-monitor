/*
 * @(#)UserDetail.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2022年8月22日 wwp-pc          初版
 *
 */
package com.tkww.demo.dataset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.tkww.demo.entity.User;

public class UserDetail implements UserDetails {

    //包装实体类,来获取数据库查询到的用户信息
    private User sysUser;

    //数据库用户权限列表
    private List<String> permissions;

    //secrity需要的权限列表
    private List<GrantedAuthority> grantedAuthorities;

    public UserDetail() {
    }

    public UserDetail(User sysUser, List<String> permissions) {
        this.sysUser = sysUser;
        this.permissions = permissions;
        getAuthorities();
    }

    // 返回当前用户的权限集合
    //Authentication 中的 getAuthorities() 实际是由 UserDetails 的 getAuthorities() 传递而形成的
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (grantedAuthorities != null)
            return this.grantedAuthorities;

        // 从 roleInfoList 中取出当前用户拥有的权限，填充进数组
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (String permission : permissions) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permission);
            grantedAuthorities.add(simpleGrantedAuthority);
        }
        this.grantedAuthorities = grantedAuthorities;
        return this.grantedAuthorities;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(
        List<String> permissions) {
        this.permissions = permissions;
    }

    public User getSysUser() {
        return sysUser;
    }

    public void setSysUser(
        User sysUser) {
        this.sysUser = sysUser;
    }

    //获取用户密码(校验密码时调用该方法获取密码)
    @Override
    public String getPassword() {
        return sysUser.getPasswd();
    }

    //获取用户名
    @Override
    public String getUsername() {
        return sysUser.getLoginName();
    }

    //是否过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //是否被锁
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }

}
