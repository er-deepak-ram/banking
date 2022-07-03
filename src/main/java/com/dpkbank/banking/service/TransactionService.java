package com.dpkbank.banking.service;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.dpkbank.banking.dto.CashDepositeOrWithdrawDto;
import com.dpkbank.banking.dto.TransactionRequestDto;
import com.dpkbank.banking.dto.TransactionResponseDto;
import com.dpkbank.banking.dto.UpiTransactionRequestDto;

public interface TransactionService {

	ResponseEntity<TransactionResponseDto> performTransaction(@Valid TransactionRequestDto transactionDto);
	
	ResponseEntity<TransactionResponseDto> cashDeposit(@Valid CashDepositeOrWithdrawDto depositeOrWithdrawDto);
	
	ResponseEntity<TransactionResponseDto> cashWithdrawl(@Valid CashDepositeOrWithdrawDto depositeOrWithdrawDto);

	Boolean checkIfUpiTransactionPossible(String fromMobileNo, String toMobileNo);

	ResponseEntity<String> upiTransaction(@Valid UpiTransactionRequestDto upiTransactionRequestDto);
}
