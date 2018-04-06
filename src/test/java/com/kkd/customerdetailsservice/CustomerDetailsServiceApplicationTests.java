package com.kkd.customerdetailsservice;


import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.kkd.customerdetailsservice.model.Address;
import com.kkd.customerdetailsservice.model.Customer;
import com.kkd.customerdetailsservice.repository.CustomerRepository;
import com.kkd.customerdetailsservice.service.CustomerService;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerDetailsServiceApplicationTests {

	@Autowired
	MockMvc mockmvc;
	
	@MockBean
	CustomerService customerService;
	
	@Test
	public void contextLoads() throws Exception {
		
		List<Address> addresses=new ArrayList<Address>();
		Address address=new Address(132039, "assandh house no. 58", "assandh", "karnal", "haryana",true);
		addresses.add(address);
		Customer customer=new Customer("CUST0001", "5461461263", "password","Aisha", "Sharma",addresses,
				address);
		List<Customer> customers=new ArrayList<Customer>();
		customers.add(customer);
		
		// studentService.addCourse to respond back with mockCourse
		Mockito.when(
				customerService.getCustomer(Mockito.anyString())).
				thenReturn(customers);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("customer/user/testing/id")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockmvc.perform(requestBuilder).andReturn();

		Customer expected=customer;
		assertEquals(expected, result.getResponse()
				.getContentAsString());

	}
		
	}

