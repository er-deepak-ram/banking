package com.dpkbank.banking.dto;

import com.dpkbank.banking.entity.Account;

public class CustomerLoginDto {
	
	private String firstName;
	private String lastName;
	private Account customerAccount;
	private Integer customerId;
	private String password;
	private long count;
	
	public CustomerLoginDto() {
	}

	public CustomerLoginDto(String firstName, String lastName, Account customerAccount, Integer customerId,
			String password, long count) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerAccount = customerAccount;
		this.customerId = customerId;
		this.password = password;
		this.count = count;
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

	public Account getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(Account customerAccount) {
		this.customerAccount = customerAccount;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CustomerLoginDto [firstName=" + firstName + ", lastName=" + lastName + ", customerAccount="
				+ customerAccount + ", count=" + count + "]";
	}
	
	
}