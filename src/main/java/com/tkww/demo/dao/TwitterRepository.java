/*
 * @(#)UserRepository.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2023年5月26日 wwp-pc          初版
 *
 */
package com.tkww.demo.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import com.tkww.demo.entity.Twitter;
import com.tkww.demo.entity.User;

public interface TwitterRepository extends ElasticsearchRepository<Twitter, String> {

}
