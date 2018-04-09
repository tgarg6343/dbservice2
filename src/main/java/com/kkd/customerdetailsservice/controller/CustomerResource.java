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

import com.kkd.customerdetailsservice.model.Address;
import com.kkd.customerdetailsservice.model.Customer;
import com.kkd.customerdetailsservice.repository.CustomerRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/customer")
public class CustomerResource {
	@Autowired
	private CustomerRepository customerRepository;

	/* add customer in the collection */
	@PostMapping("/user")
	@HystrixCommand(fallbackMethod = "fallbackStatus")
	public ResponseEntity<HttpStatus> createCustomer(@RequestBody Customer customer) {
		Optional<Customer> findByMobileNo = customerRepository.findByMobileNo(customer.getMobileNo());
		if (findByMobileNo.isPresent()) {
			customerRepository.save(customer);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	/* Get a customer from collection */
	@GetMapping("/user/{customer_id}")
	@HystrixCommand(fallbackMethod = "defaultCustomer")
	public Optional<Customer> findCustomer(@PathVariable String customer_id) {
		return customerRepository.findById(customer_id);
	}

	/* Update a customer detail */
	@PutMapping("/user/{customer_id}")
	@HystrixCommand(fallbackMethod = "fallbackStatus")
	public ResponseEntity<HttpStatus> updateCustomerDetails(@RequestBody Customer customer) {
		customerRepository.save(customer);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/* Get a customer by mobile number */
	@GetMapping("/user/mobile/{mobile}")
	@HystrixCommand(fallbackMethod = "defaultCustomer")
	public Optional<Customer> findCustomerByMobile(@PathVariable String mobile) {
		return customerRepository.findByMobileNo(mobile);
	}

	/*
	 * Hystrix fallback for getmapping "/customer/{customer_id}" and get
	 * mapping"/customer/{mobile}"
	 */
	public Optional<Customer> defaultCustomer(String id) {
		return Optional.of(new Customer("fallback", "fallback", "fallback", "fallback", "fallback", null, null));
	}

	/* delete a customer by customer id */
	@DeleteMapping("/user/{customer_id}")
	@HystrixCommand(fallbackMethod = "fallbackStatus")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable String customer_id) {
		Optional<Customer> customer = customerRepository.findById(customer_id);
		if (customer.isPresent()) {
			customerRepository.delete(customer.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/user/{customer_id}/addresses")
	@HystrixCommand(fallbackMethod = "fallbackAddress")
	public List<Address> getAddresses(@PathVariable String customer_id) {
		Optional<Customer> findByCustomerId = customerRepository.findById(customer_id);
		if (findByCustomerId.isPresent()) {
			return findByCustomerId.get().getAddresses();
		}
		return null;
	}

	/* provide fallback addresses */
	public List<Address> fallbackAddress() {
		return Arrays.asList(new Address(999999, "fallback", "fallback", "fallback", "fallback", false));
	}

	@PutMapping("/user/{customer_id}/addresses")
	@HystrixCommand(fallbackMethod = "fallbackStatus")
	public ResponseEntity<HttpStatus> addAddress(@PathVariable String customer_id, @RequestBody Address address) {
		Optional<Customer> findByCustomerId = customerRepository.findById(customer_id);
		if (findByCustomerId.isPresent()) {
			Customer customer = findByCustomerId.get();
			List<Address> addresses = customer.getAddresses();
			addresses.add(address);
			customer.setAddresses(addresses);
			customerRepository.save(customer);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}

	/* hystrix fallback method for default customer creation */
	public ResponseEntity<HttpStatus> fallbackStatus(Customer customer) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<HttpStatus> fallbackStatus(String id) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<HttpStatus> fallbackStatus(String id, Address address) {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}