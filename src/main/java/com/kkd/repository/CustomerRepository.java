package com.kkd.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.kkd.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String>{

	public Optional<Customer> findByMobileNo(String mobileNo);
}
