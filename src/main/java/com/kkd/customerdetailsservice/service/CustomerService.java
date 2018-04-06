package com.kkd.customerdetailsservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkd.customerdetailsservice.model.Customer;
import com.kkd.customerdetailsservice.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	public Boolean exists(String mobile) {
		Optional<Customer> findByMobileNo = customerRepository.findByMobileNo(mobile);
		if(findByMobileNo.isPresent()) {
			return true;
		}
		else
			return false;
	}
	public List<Customer> getCustomer(String id) {
	return customerRepository.findAll();
	}
	
}
