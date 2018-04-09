package com.kkd.customerdetailsservice.model;

import java.util.List;

import javax.annotation.Generated;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer")
public class CustomerBean {

	@Id
	private String customerId;
	private String mobileNo;
	private String password;
	private String firstName;
	private String lastName;
	private List<AddressBean> addresses;
	private AddressBean primaryAddress;

	public CustomerBean() {
		super();
	}

	public CustomerBean(String customerId, String mobileNo, String password, String firstName, String lastName,
			List<AddressBean> addresses, AddressBean primaryAddress) {
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
		this.customerId = customerId;
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

	public List<AddressBean> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressBean> addresses) {
		this.addresses = addresses;
	}

	public AddressBean getPrimaryAddress() {
		return primaryAddress;
	}

	public void setPrimaryAddress(AddressBean primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", mobileNo=" + mobileNo + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", addresses=" + addresses
				+ ", primaryAddress=" + primaryAddress + "]";
	}
}
