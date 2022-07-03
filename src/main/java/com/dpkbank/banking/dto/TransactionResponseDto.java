package com.dpkbank.banking.dto;

public class TransactionResponseDto {
	
	private Long transactionId;
	private Long accountNo;
	private Long balanceAmt;
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
	public Long getBalanceAmt() {
		return balanceAmt;
	}
	public void setBalanceAmt(Long balanceAmt) {
		this.balanceAmt = balanceAmt;
	}
}
