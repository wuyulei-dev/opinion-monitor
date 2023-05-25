package com.tkww.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpinionMonitorApplicationTests {

	@Autowired
	private RestHighLevelClient client;
	private IndexResponse respons;
	
	@SuppressWarnings("deprecation")
	@Test
	void contextLoads() throws IOException {
		IndexRequest indexRequest = new IndexRequest("test");
		
		String param = "{\"name\":\"张33\",\"age\":\"999\"}";
		
		indexRequest.id("100")
					.source(param,XContentType.JSON);
		respons = client.index(indexRequest, RequestOptions.DEFAULT);
		System.out.println(respons.toString());
	}

}
