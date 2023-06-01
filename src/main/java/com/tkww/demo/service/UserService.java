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

import java.io.IOException;
import java.util.Optional;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tkww.demo.dao.UserRepository;
import com.tkww.demo.entity.User;
import cn.hutool.json.JSONUtil;

@Service
public class UserService {

    private final String INDEX="userindex";
    @Autowired
    private RestHighLevelClient client;
    
    
    @Autowired
    private UserRepository userRepository;
    
    @SuppressWarnings("deprecation")
   public void contextLoads() throws IOException {
        IndexRequest indexRequest = new IndexRequest("test");
        
        String param = "{\"name\":\"张33\",\"age\":\"999\"}";
        
        indexRequest.id("100")
                    .source(param,XContentType.JSON);
        IndexResponse respons = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(respons.toString());
    }

    //Repository 添加
    public void repositoryAddOne() throws IOException {
        User user = new User();
        user.setName("李四");
        user.setContent("这是李四的介绍，李四未指定id，有es默认生成");
        User save = userRepository.save(user);
        
    }
    
    //根据loginName获取用户
    public User getUserByName(String loginName) throws IOException {
      User user = null; 
      SearchRequest searchRequest = new SearchRequest(INDEX);
      searchRequest.types("_doc");
      SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();
      TermQueryBuilder termQuery = QueryBuilders.termQuery("loginName", loginName);
      sourceBuilder.query(termQuery);
      searchRequest.source(sourceBuilder);
      SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
      SearchHit[] hits = response.getHits().getHits();
      for (SearchHit hit : hits) {
          user = JSONUtil.toBean(hit.getSourceAsString(), User.class);
      }
        
      return user;
    }
    
  //根据uerId 获取用户信息
    public User getUserById(String userId) throws IOException {
        Optional<User> optional = userRepository.findById(userId);
        return optional.get();
    }
    
    //Repository 查询所有
    public void repositoryGetAll() throws IOException {
        Iterable<User> findAll = userRepository.findAll();
        for (User user : findAll) {
            System.out.println(user);
        }
        
    }
    
    //Repository 更新
    public void repositoryUpdateOne() throws IOException {
       User user = new User();
       user.setId("nl0ZVogBFsXSE_Yfh8Va");
       user.setName("李四的大哥");
       user.setContent("讲李四改为李四的大哥，皮鞭伺候");
        userRepository.save(user);
        
    }
    
    //Repository 删除一个
    public void textRepository() throws IOException {
        User user = new User();
        user.setId("66");
        user.setName("李华");
        user.setContent("这是李华的介绍，会被分词");
        User save = userRepository.save(user);
        
    }
}
