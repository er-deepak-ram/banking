package com.dpkbank.banking.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class CustomerRegistrationDto {

	@NotNull(message = "firstName is required")
	@Size(min = 2, message = "firstName should have atleast 2 charecters")
	private String firstName;
	
	@NotNull(message = "lastName is required")
	@Size(min = 2, message = "lastName should have atleast 2 charecters")
	private String lastName;
	
	@NotNull(message = "Date of birth is required")
	@Past
	private Date dob;
	
	@NotNull(message = "aadharNo is required")
	@Min(value = 100000000000L, message = "Aadhar number must be of 12 digits")
	@Max(value = 999999999999L, message = "Aadhar number must be of 12 digits")
	private Long aadharNo;
	
	private String panNo;
	
	@NotNull(message = "addressLine is required")
	private String addressLine;
	
	@NotNull(message = "City is required")
	private String city;
	
	@NotNull(message = "State is required")
	private String state;
	
	@NotNull(message = "Zip code is required")
	private Integer zipCode;
	
	private Long mobileNo;
	private String occupation;
	
	@NotNull(message = "Password is required")
	private String password;
	
	@NotBlank
	@Email
	private String emailId;
	
	@NotNull(message = "Amount is required")
	@Min(value = 0, message = "Amount cannot be less than 0")
	private Long balanceAmt;
	
	private String accType;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		firstName.trim();
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Long getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(Long aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	public Long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Long getBalanceAmt() {
		return balanceAmt;
	}
	public void setBalanceAmt(Long balanceAmt) {
		this.balanceAmt = balanceAmt;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
}