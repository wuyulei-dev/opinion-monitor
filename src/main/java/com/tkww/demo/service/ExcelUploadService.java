/*
 * @(#)ExcelUploadService.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2023年6月1日 wwp-pc          初版
 *
 */
package com.tkww.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.tkww.demo.dao.UserRepository;

public class ExcelUploadService {
    @Autowired
    private UserRepository userRepository;
}
