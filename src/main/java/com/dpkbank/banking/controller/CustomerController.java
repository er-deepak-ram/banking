package com.dpkbank.banking.controller;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dpkbank.banking.dto.CustomerRegistrationDto;
import com.dpkbank.banking.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@PostMapping
	public ResponseEntity<String> customerRegistration(@Valid @RequestBody CustomerRegistrationDto customerDto) {
		return customerService.customerRegistration(customerDto);
	}
	
	@GetMapping("/account")
	public Optional<Long> getAccountNumber(@RequestParam @NotNull Long mobileNo) {
		return customerService.getAccountNumber(mobileNo); 
	}
}
