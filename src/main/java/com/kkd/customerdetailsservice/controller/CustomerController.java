package com.kkd.customerdetailsservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkd.customerdetailsservice.model.AddressBean;
import com.kkd.customerdetailsservice.model.CustomerBean;
import com.kkd.customerdetailsservice.repository.CustomerRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;

	/* add customer in the collection */
	@PostMapping("/user")
	@HystrixCommand(fallbackMethod = "fallbackStatus")
	public ResponseEntity<HttpStatus> createCustomer(@RequestBody CustomerBean customer) {
		Optional<CustomerBean> findByMobileNo = customerRepository.findByMobileNo(customer.getMobileNo());
		if (!findByMobileNo.isPresent()) {
			customerRepository.save(customer);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@GetMapping("/user/testing/{customer_id}")
	public List<CustomerBean> getAllCustomers(@PathVariable String customer_id) {
		System.out.println("---------------------------------------");
		return customerRepository.findAll();
	}

	/* Get a customer from collection */
	@GetMapping("/user/{customer_id}")
	@HystrixCommand(fallbackMethod = "defaultCustomer")
	public Optional<CustomerBean> findCustomer(@PathVariable String customer_id) {
		return customerRepository.findById(customer_id);
	}

	/* Update a customer detail */
	@PutMapping("/user/{customer_id}")
	@HystrixCommand(fallbackMethod = "fallbackStatus")
	public ResponseEntity<HttpStatus> updateCustomerDetails(@RequestBody CustomerBean customer) {
		customerRepository.save(customer);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/user/{mobile}")
	@HystrixCommand(fallbackMethod = "fallbackStatus")
	public ResponseEntity<HttpStatus> updateCustomerPassword(@RequestBody String password,
			@PathVariable("mobile") String mobile) {
		Optional<CustomerBean> findByMobileNo = customerRepository.findByMobileNo(mobile);
		if (findByMobileNo.isPresent()) {
			CustomerBean customer = findByMobileNo.get();
			customerRepository.save(customer);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	/* Get a customer by mobile number */
	@GetMapping("/user/mobile/{mobile}")
	@HystrixCommand(fallbackMethod = "defaultCustomer")
	public Optional<CustomerBean> findCustomerByMobile(@PathVariable String mobile) {
		return customerRepository.findByMobileNo(mobile);
	}

	/*
	 * Hystrix fallback for getmapping "/customer/{customer_id}" and get
	 * mapping"/customer/{mobile}"
	 */
	public Optional<CustomerBean> defaultCustomer(String id) {
		return Optional.of(new CustomerBean("fallback", "fallback", "fallback", "fallback", "fallback", null, null));
	}

	/* delete a customer by customer id */
	@DeleteMapping("/user/{customer_id}")
	@HystrixCommand(fallbackMethod = "fallbackStatus")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable String customer_id) {
		Optional<CustomerBean> customer = customerRepository.findById(customer_id);
		if (customer.isPresent()) {
			customerRepository.delete(customer.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user/{customer_id}/addresses")
	@HystrixCommand(fallbackMethod = "fallbackAddress")
	public List<AddressBean> getAddresses(@PathVariable String customer_id) {
		Optional<CustomerBean> findByCustomerId = customerRepository.findById(customer_id);
		if (findByCustomerId.isPresent()) {
			return findByCustomerId.get().getAddresses();
		}
		return null;
	}

	/* provide fallback addresses */
	public List<AddressBean> fallbackAddress() {
		return Arrays.asList(new AddressBean(999999, "fallback", "fallback", "fallback", "fallback", false));
	}

	@PutMapping("/user/{customer_id}/addresses")
	@HystrixCommand(fallbackMethod = "fallbackStatus")
	public ResponseEntity<HttpStatus> addAddress(@PathVariable String customer_id, @RequestBody AddressBean address) {
		Optional<CustomerBean> findByCustomerId = customerRepository.findById(customer_id);
		if (findByCustomerId.isPresent()) {
			CustomerBean customer = findByCustomerId.get();
			List<AddressBean> addresses = customer.getAddresses();
			addresses.add(address);
			customer.setAddresses(addresses);
			customerRepository.save(customer);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	/* hystrix fallback method for default customer creation */
	public ResponseEntity<HttpStatus> fallbackStatus(CustomerBean customer) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<HttpStatus> fallbackStatus(String id) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<HttpStatus> fallbackStatus(String id, AddressBean address) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}