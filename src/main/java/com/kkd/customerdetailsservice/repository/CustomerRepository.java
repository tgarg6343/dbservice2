package com.kkd.customerdetailsservice.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.kkd.customerdetailsservice.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

	public Optional<Customer> findByMobileNo(String mobileNo);
}
