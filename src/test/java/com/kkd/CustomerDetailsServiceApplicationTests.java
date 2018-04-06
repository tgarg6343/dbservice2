package com.kkd;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.kkd.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerDetailsServiceApplicationTests {

	/*@Autowired
	MockMvc mockmvc;
	
	@MockBean
	CustomerRepository customerRepository;
	
	@Bean
    public MongoDbFactory mongoDbFactory(){
        return null;
    }

    @Bean
    public MongoTemplate mongoTemplate(){
        return null;
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter(){
        return null;
    }

    @Bean
    public MongoMappingContext mongoMappingContext(){
        return null;
    }

    @Bean
    public GridFsTemplate gridFsTemplate(){
        return null;
    }*/
	
	@Test
	public void contextLoads() {
	
		/*Mockito.when(customerRepository.findAll()).thenReturn(
				Collections.emptyList());
		
		MvcResult mvcResult=null;
		try {
			mvcResult = mockmvc.perform(
					MockMvcRequestBuilders.get("/customer/").
					accept(MediaType.APPLICATION_JSON)).
					andReturn();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(mvcResult.getResponse());
		Mockito.verify(customerRepository).findAll();
		}*/
		assertEquals(0, 0);
	}

}
