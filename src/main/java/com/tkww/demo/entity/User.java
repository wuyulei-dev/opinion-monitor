/*
 * @(#)User.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2023年5月26日 wwp-pc          初版
 *
 */
package com.tkww.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "userindex", createIndex = true)
public class User {
    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content;

    /**
     * 密码
     */
    @Field(type = FieldType.Keyword)
    private String passwd;

    @Field(type = FieldType.Keyword)
    private String loginName;

    public String getId() {
        return id;
    }

    public void setId(
        String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(
        String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(
        String content) {
        this.content = content;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(
        String passwd) {
        this.passwd = passwd;
    }


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(
        String loginName) {
        this.loginName = loginName;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", content=" + content + ", getId()=" + getId() + ", getName()="
                + getName() + ", getContent()=" + getContent() + ", getClass()=" + getClass() + ", hashCode()="
                + hashCode() + ", toString()=" + super.toString() + "]";
    }

}
