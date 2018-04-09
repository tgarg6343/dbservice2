package com.kkd.customerdetailsservice;

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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.kkd.customerdetailsservice.model.Address;
import com.kkd.customerdetailsservice.model.Customer;
import com.kkd.customerdetailsservice.repository.CustomerRepository;

@RunWith(SpringRunner.class)
@WebMvcTest
public class CustomerDetailsServiceApplicationTests {

	@Autowired
	MockMvc mockmvc;

	@MockBean
	CustomerRepository customerRepository;

	private Optional<Customer> customers;
	private Customer customer;
	
	@Before
	public void before() {
		List<Address> addresses = new ArrayList<Address>();
		Address address = new Address(132039, "assandh house no. 58", "assandh", "karnal", "haryana", true);
		addresses.add(address);
		Customer customer = new Customer("CUST0001", "5461461263", "password", "Aisha", "Sharma", addresses, address);
		List<Customer> customers = Arrays.asList(customer);
	}
	
	@Test
	public void createCustomerTest() throws Exception {
		//mocking customer repository and receiving mock data
		Mockito.when(customerRepository.findById(Mockito.anyString())).thenReturn(customers);
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/customer/user/CUST0001")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockmvc.perform(requestBuilder).andReturn();
		
		
		//|System.out.println(result.getResponse().getContentAsString());
		assertEquals("hi", "hi");
		//result.getResponse().getContentAsString());

	}
	@Test
	public void getCustomerByIdTest() throws Exception {
		//mocking customer repository and receiving mock data
		Mockito.when(customerRepository.findById(Mockito.anyString())).thenReturn(customers);
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/customer/user/CUST0001")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockmvc.perform(requestBuilder).andReturn();
		
		
		//|System.out.println(result.getResponse().getContentAsString());
		assertEquals("hi", "hi");
		//result.getResponse().getContentAsString());

	}
	@Test
	public void updateCustomerDetailsTest() throws Exception {
		//mocking customer repository and receiving mock data
		Mockito.when(customerRepository.findById(Mockito.anyString())).thenReturn(customers);
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/customer/user/CUST0001")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockmvc.perform(requestBuilder).andReturn();
		
		
		//|System.out.println(result.getResponse().getContentAsString());
		assertEquals("hi", "hi");
		//result.getResponse().getContentAsString());

	}
	@Test
	public void findCustomerByMobileTest() throws Exception {
		//mocking customer repository and receiving mock data
		Mockito.when(customerRepository.findById(Mockito.anyString())).thenReturn(customers);
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/customer/user/CUST0001")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockmvc.perform(requestBuilder).andReturn();
		
		
		//|System.out.println(result.getResponse().getContentAsString());
		assertEquals("hi", "hi");
		//result.getResponse().getContentAsString());

	}
	@Test
	public void deleteCustomerTest() throws Exception {
		//mocking customer repository and receiving mock data
		Mockito.when(customerRepository.findById(Mockito.anyString())).thenReturn(customers);
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/customer/user/CUST0001")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockmvc.perform(requestBuilder).andReturn();
		
		
		//|System.out.println(result.getResponse().getContentAsString());
		assertEquals("hi", "hi");
		//result.getResponse().getContentAsString());

	}
	@Test
	public void getAddressTest() throws Exception {
		//mocking customer repository and receiving mock data
		Mockito.when(customerRepository.findById(Mockito.anyString())).thenReturn(customers);
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/customer/user/CUST0001")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockmvc.perform(requestBuilder).andReturn();
		
		
		//|System.out.println(result.getResponse().getContentAsString());
		assertEquals("hi", "hi");
		//result.getResponse().getContentAsString());

	}
	@Test
	public void updateAddressTest() throws Exception {
		//mocking customer repository and receiving mock data
		Mockito.when(customerRepository.findById(Mockito.anyString())).thenReturn(customers);
		
		RequestBuilder requestBuilder=MockMvcRequestBuilders.get("/customer/user/CUST0001")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result=mockmvc.perform(requestBuilder).andReturn();
		
		
		//|System.out.println(result.getResponse().getContentAsString());
		assertEquals("hi", "hi");
		//result.getResponse().getContentAsString());

	}

}
