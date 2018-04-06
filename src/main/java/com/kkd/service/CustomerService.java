package com.kkd.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkd.model.Customer;
import com.kkd.repository.CustomerRepository;

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
}
