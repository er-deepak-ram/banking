package com.dpkbank.banking.controller;

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

import com.dpkbank.banking.dto.CashDepositeOrWithdrawDto;
import com.dpkbank.banking.dto.TransactionRequestDto;
import com.dpkbank.banking.dto.TransactionResponseDto;
import com.dpkbank.banking.dto.UpiTransactionRequestDto;
import com.dpkbank.banking.exception.UpiTransactionException;
import com.dpkbank.banking.service.TransactionService;
import com.dpkbank.banking.utils.Constants;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/transaction")
	public ResponseEntity<TransactionResponseDto> performTransaction(@Valid @RequestBody TransactionRequestDto transactionDto) {
		return transactionService.performTransaction(transactionDto);
	}
	
	@PostMapping("/deposit")
	ResponseEntity<TransactionResponseDto> cashDeposit(@Valid @RequestBody CashDepositeOrWithdrawDto depositeOrWithdrawDto) {
		return transactionService.cashDeposit(depositeOrWithdrawDto);
	}
	
	@PostMapping("/withdraw")
	ResponseEntity<TransactionResponseDto> cashWithdrawl(@Valid @RequestBody CashDepositeOrWithdrawDto depositeOrWithdrawDto) {
		return transactionService.cashWithdrawl(depositeOrWithdrawDto);
	}
	
	@GetMapping("/check")
	Boolean checkIfUpiTransactionPossible(@RequestParam("fromMobileNo") @NotNull String fromMobileNo, @RequestParam("toMobileNo") @NotNull String toMobileNo) {
		return transactionService.checkIfUpiTransactionPossible(fromMobileNo, toMobileNo);
	}
	
	@PostMapping("/upi")
	ResponseEntity<String> upiTransaction(@Valid @RequestBody UpiTransactionRequestDto upiTransactionRequestDto) {
		if(checkIfUpiTransactionPossible(upiTransactionRequestDto.getFromMobileNo(), upiTransactionRequestDto.getToMobileNo())) {
			return transactionService.upiTransaction(upiTransactionRequestDto);
		}
		throw new UpiTransactionException(Constants.CUSTOMER_MOBILE_NOT_EXIST);
	}
}
