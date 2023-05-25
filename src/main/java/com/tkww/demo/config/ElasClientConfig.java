package com.tkww.demo.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class ElasClientConfig extends AbstractElasticsearchConfiguration{

	@Override
	@Bean
	public RestHighLevelClient elasticsearchClient() {
		  String username="elastic";
		  String password="123456";
		 final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
	                .connectedTo("10.80.0.60:9200")
	                .withBasicAuth(username, password)
	                .build();
	    return RestClients.create(clientConfiguration).rest();
	}

}
