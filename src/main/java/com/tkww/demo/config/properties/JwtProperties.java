/*
 * @(#)JwtProperties.java
 * Copyright (C) 2020 Neusoft Corporation All rights reserved.
 *
 * VERSION        DATE       BY              CHANGE/COMMENT
 * ----------------------------------------------------------------------------
 * @version 1.00  2023年5月31日 wwp-pc          初版
 *
 */
package com.tkww.demo.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretyKey;

    public String getSecretyKey() {
        return secretyKey;
    }

    public void setSecretyKey(
        String secretyKey) {
        this.secretyKey = secretyKey;
    }
}
