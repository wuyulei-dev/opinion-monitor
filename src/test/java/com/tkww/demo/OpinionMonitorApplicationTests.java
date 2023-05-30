package com.tkww.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.IOException;
import java.util.Optional;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.tkww.demo.dao.UserRepository;
import com.tkww.demo.entity.User;

@SpringBootTest
class OpinionMonitorApplicationTests {

	@Autowired
	private RestHighLevelClient client;
	private IndexResponse respons;
	
	@Autowired
	private UserRepository userRepository;
	
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

	//Repository 添加
    @Test
    void repositoryAddOne() throws IOException {
        User user = new User();
        user.setId("66");
        user.setName("康有为");
        user.setLoginName("kyw");
        user.setContent("康有为和梁启超");
        user.setPasswd("123");
        User save = userRepository.save(user);
        
    }
    
    //Repository 查询一个
    @Test
    void repositoryGetOne() throws IOException {
       Optional<User> optional = userRepository.findById("66");
       System.out.println(optional.get());
        
    }
    
    //Repository 查询所有
    @Test
    void repositoryGetAll() throws IOException {
        Iterable<User> findAll = userRepository.findAll();
        for (User user : findAll) {
            System.out.println(user);
        }
        
    }
    
    //Repository 更新
    @Test
    void repositoryUpdateOne() throws IOException {
       User user = new User();
       user.setId("nl0ZVogBFsXSE_Yfh8Va");
       user.setName("李四的大哥");
       user.setContent("讲李四改为李四的大哥，皮鞭伺候");
        userRepository.save(user);
        
    }
    
    //Repository 删除一个
    @Test
    void textRepository() throws IOException {
        User user = new User();
        user.setId("66");
        user.setName("李华");
        user.setContent("这是李华的介绍，会被分词");
        User save = userRepository.save(user);
        
    }
}
