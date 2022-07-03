package com.dpkbank.banking.service;

import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;

import com.dpkbank.banking.dto.CustomerRegistrationDto;

public interface CustomerService {

	ResponseEntity<String> customerRegistration(@Valid CustomerRegistrationDto customerDto);

	Optional<Long> getAccountNumber(@NotNull Long mobileNo);
}
