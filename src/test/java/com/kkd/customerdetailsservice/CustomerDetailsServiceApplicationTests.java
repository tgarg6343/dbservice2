package com.kkd.customerdetailsservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.kkd.customerdetailsservice.model.AddressBean;
import com.kkd.customerdetailsservice.model.CustomerBean;
import com.kkd.customerdetailsservice.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CustomerDetailsServiceApplicationTests {

	@Autowired
	MockMvc mockmvc;

	@MockBean
	CustomerRepository customerRepository;

	private List<CustomerBean> customers;
	private CustomerBean customer;
	String exampleJson;
	String objectCustomer;

	@Before
	public void before() {
		List<AddressBean> addresses = new ArrayList<AddressBean>();
		AddressBean address = new AddressBean(132039, "assandh house no. 58", "assandh", "karnal", "haryana", true);
		addresses.add(address);
		customer = new CustomerBean("CUST0001", "5461461263", "password", "Aisha", "Sharma", addresses, address);
		customers = Arrays.asList(customer);
		exampleJson = "[{\"customerId\":\"CUST0001\",\"mobileNo\":\"5461461263\",\"password\":\"password\",\"firstName\":\"Aisha\",\"lastName\":\"Sharma\",\"addresses\":[{\"pincode\":132039,\"addressLine\":\"assandh house no. 58\",\"city\":\"assandh\",\"district\":\"karnal\",\"state\":\"haryana\",\"primary\":true}],\"primaryAddress\":{\"pincode\":132039,\"addressLine\":\"assandh house no. 58\",\"city\":\"assandh\",\"district\":\"karnal\",\"state\":\"haryana\",\"primary\":true}}]";
		System.out.println("hi1");
		objectCustomer = "{\"customerId\":\"CUST0001\",\"mobileNo\":\"5461461263\",\"password\":\"password\",\"firstName\":\"Aisha\",\"lastName\":\"Sharma\",\"addresses\":[{\"pincode\":132039,\"addressLine\":\"assandh house no. 58\",\"city\":\"assandh\",\"district\":\"karnal\",\"state\":\"haryana\",\"primary\":true}],\"primaryAddress\":{\"pincode\":132039,\"addressLine\":\"assandh house no. 58\",\"city\":\"assandh\",\"district\":\"karnal\",\"state\":\"haryana\",\"primary\":true}}";
	}

	@Test
	public void createCustomerTest() throws Exception {
		// mocking customer repository and receiving mock data
		Mockito.when(customerRepository.findByMobileNo(Mockito.anyString())).thenReturn(Optional.empty());
		Mockito.when(customerRepository.save(Mockito.any(CustomerBean.class))).thenReturn(customer);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer/user").accept(MediaType.APPLICATION_JSON)
				.content(objectCustomer).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		assertThat(result.getResponse().getStatus()).isEqualTo(201);

	}

	@Test
	public void getCustomerByIdPosTest() throws Exception {
		// mocking customer repository and receiving mock data
		Mockito.when(customerRepository.findById(Mockito.anyString())).thenReturn(Optional.of(customer));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/customer/user/CUST0001")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockmvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		String expected = "{\"customerId\":\"CUST0001\",\"mobileNo\":\"5461461263\",\"password\":\"password\",\"firstName\":\"Aisha\",\"lastName\":\"Sharma\",\"addresses\":[{\"pincode\":132039,\"addressLine\":\"assandh house no. 58\",\"city\":\"assandh\",\"district\":\"karnal\",\"state\":\"haryana\",\"primary\":true}],\"primaryAddress\":{\"pincode\":132039,\"addressLine\":\"assandh house no. 58\",\"city\":\"assandh\",\"district\":\"karnal\",\"state\":\"haryana\",\"primary\":true}}";
		assertEquals(expected, result.getResponse().getContentAsString());

	}
	
	@Test public void getAllCustTest() throws Exception {
		//mocking customer repository and receiving mock data
	  Mockito.when(customerRepository.findAll()).thenReturn(customers);
	  RequestBuilder requestBuilder =MockMvcRequestBuilders.get("/customer/user/testing/CUST0001")
			  						.accept(MediaType.APPLICATION_JSON);
	  MvcResult result = mockmvc.perform(requestBuilder).andReturn();
	  System.out.println(result.getResponse().getContentAsString());
	  assertEquals(exampleJson, result.getResponse().getContentAsString());
	  
	}
}
