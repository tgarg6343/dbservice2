package com.kkd.customerdetailsservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

import com.kkd.customerdetailsservice.model.Address;
import com.kkd.customerdetailsservice.model.Customer;
import com.kkd.customerdetailsservice.repository.CustomerRepository;

@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
@SpringBootApplication
public class CustomerDetailsServiceApplication implements CommandLineRunner{

	@Autowired
	CustomerRepository customerRepository;
	public static void main(String[] args) {
		SpringApplication.run(CustomerDetailsServiceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("starting -------------------------------------------------------");
		customerRepository.save(new Customer("hgbhj", "hgbhj","hgbhj","hgbhj", "hgbhj", null, null));
		List<Address> addresses=new ArrayList<Address>();
		Address address=new Address(132039, "assandh house no. 58", "assandh", "karnal", "haryana",true);
		addresses.add(address);
		customerRepository.save(new Customer("CUST0001", "5461461263", "password","Aisha", "Sharma",addresses,
				address));
		
		//customerRepository.save(new Cust)
	}
}
