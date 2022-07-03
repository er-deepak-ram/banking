package com.dpkbank.banking.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dpkbank.banking.dto.MonthlyStatementResponseDto;
import com.dpkbank.banking.dto.TransactionListDto;
import com.dpkbank.banking.entity.Account;
import com.dpkbank.banking.entity.TransactionDetails;
import com.dpkbank.banking.exception.ResourceNotFoundException;
import com.dpkbank.banking.repository.AccountRepository;
import com.dpkbank.banking.repository.TransactionDetailsRepository;
import com.dpkbank.banking.service.StatementService;
import com.dpkbank.banking.utils.CommonUtils;
import com.dpkbank.banking.utils.Constants;

@Service
public class StatementServiceImpl implements StatementService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionDetailsRepository transactionDetailsRepository;

		
	public void checkIfCustomerExist(Long accountNo) {
		Optional<Account> result = accountRepository.findById(accountNo);
		if(result.isPresent())
			return;
		else
			throw new ResourceNotFoundException(Constants.CUSTOMER_NOT_EXIST+accountNo);
	}

	@Override
	public ResponseEntity<MonthlyStatementResponseDto> getMonthlyTransaction(long accountNo, int month, int year) {
		checkIfCustomerExist(accountNo);
		MonthlyStatementResponseDto monthlyStatementResponseDto = new MonthlyStatementResponseDto();
		monthlyStatementResponseDto.setTransactions(new ArrayList<>());

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		monthlyStatementResponseDto.setDate(LocalDate.now());
		monthlyStatementResponseDto.setTime(LocalTime.parse(LocalTime.now().format(dtf)));
		monthlyStatementResponseDto.setAccountNo(accountNo);
		LocalDate startLocalDate = LocalDate.of(year, month, 1);
		LocalDate endLocalDate = startLocalDate.with(TemporalAdjusters.lastDayOfMonth());
		List<TransactionDetails> transactions = transactionDetailsRepository
				.findByFromAccountNoOrToAccountNoAndDotBetween(accountNo, accountNo, Date.valueOf(startLocalDate),
						Date.valueOf(endLocalDate));
		transactions.forEach(transaction -> {
			TransactionListDto transactionListDto = new TransactionListDto();
			transactionListDto.setTransactionId(transaction.getTransactionId());
			transactionListDto.setTransactionDate(transaction.getDot().toLocalDateTime());
			transactionListDto.setAmount(transaction.getAmount());
			if (!CommonUtils.isNull(transaction.getFromAccountNo())
					&& !CommonUtils.isNull(transaction.getToAccountNo())) {
				transactionListDto.setTransactionType(
						Long.valueOf(accountNo).equals(transaction.getFromAccountNo()) ? Constants.DEBIT
								: Constants.CREDIT);
				transactionListDto.setAccountInvolved(Long.valueOf(accountNo).equals(transaction.getFromAccountNo())
						? transaction.getToAccountNo().toString()
						: transaction.getFromAccountNo().toString());
			} else {
				transactionListDto.setTransactionType(transaction.getDescription());
				transactionListDto.setAccountInvolved(Constants.SELF);
			}
			monthlyStatementResponseDto.getTransactions().add(transactionListDto);
		});
		Account account = accountRepository.findById(accountNo)
				.orElseThrow(() -> new ResourceNotFoundException(Constants.ACCOUNT_NOT_FOUND + accountNo));
		monthlyStatementResponseDto.setAvailableBalance(account.getBalanceAmt());
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("message", Constants.MONTHLY_STATEMENT_SUCCESS);
		return new ResponseEntity<>(monthlyStatementResponseDto, responseHeaders, HttpStatus.OK);
	}

}
