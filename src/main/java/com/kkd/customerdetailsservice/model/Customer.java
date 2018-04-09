package com.kkd.customerdetailsservice.model;

import java.util.List;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class Customer {

	static Integer start = 1000;
	@Id
	private String customerId;
	private String mobileNo;
	private String password;
	private String firstName;
	private String lastName;
	private List<Address> addresses;
	private Address primaryAddress;

	public Customer() {
		super();
	}

	public Customer(String customerId, String mobileNo, String password, String firstName, String lastName,
			List<Address> addresses, Address primaryAddress) {
		super();
		this.customerId = customerId;
		this.mobileNo = mobileNo;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addresses = addresses;
		this.primaryAddress = primaryAddress;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = "CUST" + start++;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address getPrimaryAddress() {
		return primaryAddress;
	}

	public void setPrimaryAddress(Address primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", mobileNo=" + mobileNo + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", addresses=" + addresses
				+ ", primaryAddress=" + primaryAddress + "]";
	}
}
