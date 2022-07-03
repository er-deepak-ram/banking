package com.dpkbank.banking.dto;

import java.time.LocalDateTime;

public class TransactionListDto {
	
	private LocalDateTime transactionDate;
	private Long transactionId;
	private String transactionType;
	private Long amount;
	private String accountInvolved;
	
	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getAccountInvolved() {
		return accountInvolved;
	}
	public void setAccountInvolved(String accountInvolved) {
		this.accountInvolved = accountInvolved;
	}
}
