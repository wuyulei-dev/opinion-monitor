/*
 * @(#)Twitter.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2023年6月1日 wwp-pc          初版
 *
 */
package com.tkww.demo.entity;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import com.fasterxml.jackson.annotation.JsonFormat;

@Document(indexName = "twitter", createIndex = true)
public class Twitter {

        @Id
        private String id;

        @Field(type = FieldType.Keyword)
        private String mediaName;

        //Date转化为 json字符串时的 格式jackson在序列化时间时是按照国际标准时间GMT进行格式化的
        @Field(type = FieldType.Date,format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
        private Date date;

     
        @Field(type = FieldType.Keyword)
        private String title;
        
        @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
        private String content;

        @Field(type = FieldType.Keyword)
        private String link;
        
        @Field(type = FieldType.Keyword)
        private String imgUrl;
        
        //创建时间
        @Field(type = FieldType.Date,format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
        private Date createDate;
        
        //修改时间
        @Field(type = FieldType.Date,format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
        private Date updateDate;

        public String getId() {
            return id;
        }

        public void setId(
            String id) {
            this.id = id;
        }

        public String getMediaName() {
            return mediaName;
        }

        public void setMediaName(
            String mediaName) {
            this.mediaName = mediaName;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(
            Date date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(
            String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(
            String content) {
            this.content = content;
        }

        public String getLink() {
            return link;
        }

        public void setLink(
            String link) {
            this.link = link;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(
            String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public Date getCreateDate() {
            return createDate;
        }

        public void setCreateDate(
            Date createDate) {
            this.createDate = createDate;
        }

        public Date getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(
            Date updateDate) {
            this.updateDate = updateDate;
        }
}
