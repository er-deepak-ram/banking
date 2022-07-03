package com.dpkbank.banking.dto;

import javax.validation.constraints.NotNull;

public class TransactionRequestDto {

	@NotNull(message = "from Account number is required")
	private Long fromAccountNo;
	
	@NotNull(message = "to account number is required")
	private Long toAccountNo;
	
	@NotNull(message = "Amount is required")
	private Long amount;

	public Long getFromAccountNo() {
		return fromAccountNo;
	}

	public void setFromAccountNo(Long fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}

	public Long getToAccountNo() {
		return toAccountNo;
	}

	public void setToAccountNo(Long toAccountNo) {
		this.toAccountNo = toAccountNo;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
}
