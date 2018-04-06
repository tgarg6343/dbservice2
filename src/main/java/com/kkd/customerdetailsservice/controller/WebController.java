package com.kkd.customerdetailsservice.controller;

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
import com.kkd.customerdetailsservice.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class WebController {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerService customerService;
	
	/* Get all the Customer details */
	@GetMapping("/all")
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}
	
	/* Delete all the Customer details */
	@DeleteMapping("/all")
	public void deleteAll() {
		customerRepository.deleteAll();
	}

	/* add customer in the collection */
	
	@PostMapping("/user")
	public ResponseEntity<HttpStatus> createUser(@RequestBody Customer customer) {
		/* If Customer exists with same mobile no. */
		if(!customerService.exists(customer.getMobileNo())) {
			customerRepository.save(customer);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	/* Get a customer from collection */
	@GetMapping("/user/{customer_id}")
	public Optional<Customer> findCustomer(@PathVariable String customer_id) {
		return customerRepository.findById(customer_id);
	}
	
	/* Update a customer detail */
	@PutMapping("/user/{customer_id}")
	public ResponseEntity<HttpStatus> updateCustomer(@RequestBody Customer customer) {
		Customer save = customerRepository.save(customer);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/* Get a customer by mobile number */
	@GetMapping("/user/{mobile}")
	public Optional<Customer> findCustomerByMobile(@PathVariable String mobile) {
		return customerRepository.findByMobileNo(mobile);
	}
	
	/* delete a customer by customer id*/
	@DeleteMapping("/user/{customer_id}")
	public void deleteCustomer(@PathVariable String customer_id) {
		Optional<Customer> customer=customerRepository.findById(customer_id);
		if(customer.isPresent()) {
			customerRepository.delete(customer.get());
		}
	}
	@GetMapping("/user/{customer_id}/addresses")
	public List<Address> getAddresses(@PathVariable String customer_id) {
		Optional<Customer> findByCustomerId = customerRepository.findById(customer_id);
		if(findByCustomerId.isPresent()) {
			return findByCustomerId.get().getAddresses();
		}
		return null;
	}
	@PutMapping("/user/{customer_id}/addresses")
	public ResponseEntity<HttpStatus> getAddresses(@PathVariable String customer_id, @RequestBody Address address) {
		Optional<Customer> findByCustomerId = customerRepository.findById(customer_id);
		if(findByCustomerId.isPresent()) {
			Customer customer=findByCustomerId.get();
			List<Address> addresses=customer.getAddresses();
			addresses.add(address);
			customer.setAddresses(addresses);
			customerRepository.save(customer);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
	
	
	@GetMapping("/user/testing/{id}")
	public List<Customer> getCustomers(@PathVariable String id){
		return customerService.getCustomer(id);
	}
}