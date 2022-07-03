package com.dpkbank.banking.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dpkbank.banking.dto.CustomerRegistrationDto;
import com.dpkbank.banking.service.CustomerService;
import com.dpkbank.banking.utils.Constants;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class CustomerControllerTest {
	
	@Mock
	CustomerService customerService;
	
	@InjectMocks
	CustomerController customerController;
	
	static CustomerRegistrationDto customerDto;
	
	@BeforeAll
	public static void setup() {
		customerDto = new CustomerRegistrationDto();
		customerDto.setAadharNo(123456784321L);
		customerDto.setAccType("Saving");
		customerDto.setAddressLine("Bhagwan nagar");
		customerDto.setBalanceAmt(50000L);
		customerDto.setCity("Nagpur");
		customerDto.setDob(new Date(1990, 05, 27));
		customerDto.setEmailId("deepak.ram@hcl.com");
		customerDto.setFirstName("Deepak");
		customerDto.setLastName("Ram");
		customerDto.setMobileNo(9881634256L);
		customerDto.setOccupation("Engineer");
		customerDto.setPassword("Deepak@123");
		customerDto.setState("Maharashtra");
		customerDto.setZipCode(440027);
	}

	@Test
	@DisplayName("Customer Registration: Positive Scenario")
	void testCustomerRegistration() {

		// given or context
		when(customerService.customerRegistration(customerDto))
				.thenReturn(new ResponseEntity<String>(Constants.REGISTRATION_SUCCESS, HttpStatus.CREATED));

		// when or event
		ResponseEntity<String> registered = customerController.customerRegistration(customerDto);

		// then or outcome
		verify(customerService).customerRegistration(customerDto);
		assertEquals(registered.getBody(), Constants.REGISTRATION_SUCCESS);
	}

}
