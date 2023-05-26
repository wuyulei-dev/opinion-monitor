package com.tkww.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import com.tkww.demo.config.ElasClientConfig;

@SpringBootApplication
public class OpinionMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpinionMonitorApplication.class, args);
	
		
	}

}
