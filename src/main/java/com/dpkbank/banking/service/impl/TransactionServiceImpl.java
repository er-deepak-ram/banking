package com.dpkbank.banking.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dpkbank.banking.dto.CashDepositeOrWithdrawDto;
import com.dpkbank.banking.dto.TransactionRequestDto;
import com.dpkbank.banking.dto.TransactionResponseDto;
import com.dpkbank.banking.dto.UpiTransactionRequestDto;
import com.dpkbank.banking.entity.Account;
import com.dpkbank.banking.entity.Customer;
import com.dpkbank.banking.entity.TransactionDetails;
import com.dpkbank.banking.exception.InvalidTransactionException;
import com.dpkbank.banking.exception.ResourceNotFoundException;
import com.dpkbank.banking.repository.AccountRepository;
import com.dpkbank.banking.repository.CustomerRepository;
import com.dpkbank.banking.repository.TransactionDetailsRepository;
import com.dpkbank.banking.service.TransactionService;
import com.dpkbank.banking.utils.CommonUtils;
import com.dpkbank.banking.utils.Constants;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private TransactionDetailsRepository transactionDetailsRepository;

	// If customer exist then simply return else throw a custom exception
	public void checkIfCustomerExist(Long accountNo) {
		Optional<Account> result = accountRepository.findById(accountNo);
		if (result.isPresent())
			return;
		else {
			logger.error(Constants.CUSTOMER_NOT_EXIST + accountNo);
			throw new ResourceNotFoundException(Constants.CUSTOMER_NOT_EXIST + accountNo);
		}
	}

	// Simply return if funds are available, if not throw exception
	public void checkIfTransactionAllowed(Long accountNo, TransactionRequestDto transactionDto) {
		int result = customerRepository.transactionAllowed(accountNo, transactionDto.getAmount());
		if (result == 1)
			return;
		else {
			logger.error(Constants.INSUFFICIENT_FUND);
			throw new InvalidTransactionException(Constants.INSUFFICIENT_FUND);
		}
	}

	@Override
	@Transactional
	public ResponseEntity<TransactionResponseDto> performTransaction(
			@Valid TransactionRequestDto transactionRequestDto) {
		// check if both accounts are present or not
		checkIfCustomerExist(transactionRequestDto.getFromAccountNo());
		checkIfCustomerExist(transactionRequestDto.getToAccountNo());
		// check if sufficient funds is available or not
		checkIfTransactionAllowed(transactionRequestDto.getFromAccountNo(), transactionRequestDto);
		TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
		List<TransactionDetails> transactions = new ArrayList<>();

		TransactionDetails transactionDetails = new TransactionDetails();
		transactionDetails.setFromAccountNo(transactionRequestDto.getFromAccountNo());
		transactionDetails.setToAccountNo(transactionRequestDto.getToAccountNo());
		transactionDetails.setAmount(transactionRequestDto.getAmount());
		transactionDetails.setDot(Timestamp.valueOf(LocalDateTime.now()));
		transactionDetails.setDescription(Constants.ONLINE_TRANSFER);
		transactionDetailsRepository.save(transactionDetails);
		transactions.add(transactionDetails);

		Account loggedInAccount = accountRepository.findById(transactionRequestDto.getFromAccountNo())
				.orElseThrow(() -> new ResourceNotFoundException(
						Constants.ACCOUNT_NOT_FOUND + transactionRequestDto.getFromAccountNo()));
		loggedInAccount.setBalanceAmt(loggedInAccount.getBalanceAmt() - transactionRequestDto.getAmount());
		loggedInAccount.getTransactions().add(transactionDetails);
//		loggedInAccount.setTransactions(transactions);
		accountRepository.save(loggedInAccount);

		Account toAccount = accountRepository.findById(transactionRequestDto.getToAccountNo())
				.orElseThrow(() -> new ResourceNotFoundException(
						Constants.ACCOUNT_NOT_FOUND + transactionRequestDto.getToAccountNo()));
		toAccount.setBalanceAmt(toAccount.getBalanceAmt() + transactionRequestDto.getAmount());
		toAccount.getTransactions().add(transactionDetails);
//		toAccount.setTransactions(transactions);
		accountRepository.save(toAccount);

		BeanUtils.copyProperties(loggedInAccount, transactionResponseDto);
		transactionResponseDto.setTransactionId(transactionDetails.getTransactionId());

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("message", Constants.TRANSACTION_SUCCESS);
		return new ResponseEntity<>(transactionResponseDto, responseHeaders, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<TransactionResponseDto> cashDeposit(
			@Valid CashDepositeOrWithdrawDto cashDepositeOrWithdrawDto) {
		checkIfCustomerExist(cashDepositeOrWithdrawDto.getAccountNo());
		TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
		List<TransactionDetails> transactionDetailsList = new ArrayList<>();
		TransactionDetails transactionDetails = new TransactionDetails();
		transactionDetails.setToAccountNo(cashDepositeOrWithdrawDto.getAccountNo());
		transactionDetails.setAmount(cashDepositeOrWithdrawDto.getAmount());
		transactionDetails.setDot(Timestamp.valueOf(LocalDateTime.now()));
		transactionDetails.setDescription(Constants.CASH_DEPOSITE);
		transactionDetailsRepository.save(transactionDetails);
		transactionDetailsList.add(transactionDetails);

		Account account = accountRepository.findById(cashDepositeOrWithdrawDto.getAccountNo())
				.orElseThrow(() -> new ResourceNotFoundException(
						Constants.ACCOUNT_NOT_FOUND + cashDepositeOrWithdrawDto.getAccountNo()));
		account.setBalanceAmt(account.getBalanceAmt() + cashDepositeOrWithdrawDto.getAmount());
		account.setTransactions(transactionDetailsList);
		accountRepository.save(account);

		BeanUtils.copyProperties(account, transactionResponseDto);
		transactionResponseDto.setTransactionId(transactionDetails.getTransactionId());

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("message", Constants.CASH_DEPOSITE_SUCCESS);
		return new ResponseEntity<>(transactionResponseDto, responseHeaders, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<TransactionResponseDto> cashWithdrawl(
			@Valid CashDepositeOrWithdrawDto depositeOrWithdrawDto) {
		checkIfCustomerExist(depositeOrWithdrawDto.getAccountNo());
		TransactionResponseDto transactionResponseDto = new TransactionResponseDto();
		List<TransactionDetails> transactionDetailsList = new ArrayList<>();
		TransactionDetails transactionDetails = new TransactionDetails();
		transactionDetails.setFromAccountNo(depositeOrWithdrawDto.getAccountNo());
		transactionDetails.setAmount(depositeOrWithdrawDto.getAmount());
		transactionDetails.setDot(Timestamp.valueOf(LocalDateTime.now()));
		transactionDetails.setDescription(Constants.WITHDRAWAL);
		transactionDetailsRepository.save(transactionDetails);
		transactionDetailsList.add(transactionDetails);

		Account account = accountRepository.findById(depositeOrWithdrawDto.getAccountNo())
				.orElseThrow(() -> new ResourceNotFoundException(
						Constants.ACCOUNT_NOT_FOUND + depositeOrWithdrawDto.getAccountNo()));
		account.setBalanceAmt(account.getBalanceAmt() - depositeOrWithdrawDto.getAmount());
		account.setTransactions(transactionDetailsList);
		accountRepository.save(account);

		BeanUtils.copyProperties(account, transactionResponseDto);
		transactionResponseDto.setTransactionId(transactionDetails.getTransactionId());

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("message", Constants.WITHDRAW_SUCCESS);
		return new ResponseEntity<>(transactionResponseDto, responseHeaders, HttpStatus.OK);
	}

	public Customer customerLogin(Integer customerId, String password) {
		List<Customer> customers = customerRepository.findByCustomerIdAndPassword(customerId, password);
		if (customers.size() > 0) {
			for (Customer customer : customers) {
				return customer;
			}
		}
		return null;
	}

	@Override
	public Boolean checkIfUpiTransactionPossible(String fromMobileNo, String toMobileNo) {
		Boolean found = false;
		Optional<Customer> customer1 = customerRepository.findByMobileNo(Long.parseLong(fromMobileNo));
		if (customer1.isPresent())
			found = true;
		Optional<Customer> customer2 = customerRepository.findByMobileNo(Long.parseLong(toMobileNo));
		if (customer2.isPresent())
			found = true;
		return found;
	}

	@Override
	@Transactional
	public ResponseEntity<String> upiTransaction(@Valid UpiTransactionRequestDto upiTransactionRequestDto) {
		TransactionRequestDto transactionRequestDto = new TransactionRequestDto();

		Optional<Long> fromAccountNo = accountRepository
				.getAccountNumberOfCustomer(Long.parseLong(upiTransactionRequestDto.getFromMobileNo()));
		fromAccountNo.ifPresentOrElse(fromAccNo -> transactionRequestDto.setFromAccountNo(fromAccNo), () -> {
			throw new ResourceNotFoundException(
					Constants.CUSTOMER_MOBILE_NOT_EXIST + upiTransactionRequestDto.getFromMobileNo());
		});

		Optional<Long> toAccountNo = accountRepository
				.getAccountNumberOfCustomer(Long.parseLong(upiTransactionRequestDto.getToMobileNo()));
		toAccountNo.ifPresentOrElse(toAccNo -> transactionRequestDto.setToAccountNo(toAccNo), () -> {
			throw new ResourceNotFoundException(
					Constants.CUSTOMER_MOBILE_NOT_EXIST + upiTransactionRequestDto.getToMobileNo());
		});
		transactionRequestDto.setAmount(upiTransactionRequestDto.getAmount());
		ResponseEntity<TransactionResponseDto> transactionResponse = performTransaction(transactionRequestDto);
		if (!CommonUtils.isNull(transactionResponse.getBody().getTransactionId()))
			return new ResponseEntity<>(Constants.TRANSACTION_SUCCESS, HttpStatus.OK);
		else {
			logger.error(Constants.TRANSACTION_FAILED);
			return new ResponseEntity<>(Constants.TRANSACTION_FAILED, HttpStatus.FORBIDDEN);
		}
	}
}
