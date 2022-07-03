package com.dpkbank.banking.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpkbank.banking.dto.CustomerRegistrationDto;
import com.dpkbank.banking.entity.Account;
import com.dpkbank.banking.entity.Customer;
import com.dpkbank.banking.repository.AccountRepository;
import com.dpkbank.banking.service.CustomerService;
import com.dpkbank.banking.utils.Constants;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Override
	@Transactional
	public ResponseEntity<String> customerRegistration(@Valid CustomerRegistrationDto customerDto) {
		Customer customer = new Customer();
		Account account = new Account();
		BeanUtils.copyProperties(customerDto, customer);
		BeanUtils.copyProperties(customerDto, account);
		account.setAccOpenDt(Date.valueOf(LocalDate.now()));
		account.setAccStatus(true);
		account.setCustId(customer);
		accountRepository.save(account);
		return new ResponseEntity<>(Constants.REGISTRATION_SUCCESS, HttpStatus.CREATED);
	}

	@Override
	public Optional<Long> getAccountNumber(@NotNull Long mobileNo) {
		return accountRepository.getAccountNumberOfCustomer(mobileNo);
	}
}
