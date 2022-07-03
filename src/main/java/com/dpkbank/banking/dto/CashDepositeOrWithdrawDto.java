package com.dpkbank.banking.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CashDepositeOrWithdrawDto {

	@NotNull(message = "Account number is required")
	private Long accountNo;
	
	@NotNull(message = "Amount is required")
	@Min(value = 1, message = "Amount cannot be 0 or negative")
	private Long amount;

	public CashDepositeOrWithdrawDto() {
	}

	public CashDepositeOrWithdrawDto(@NotNull(message = "Account number is required") Long accountNo,
			@NotNull(message = "Amount is required") @Min(value = 1, message = "Amount cannot be 0 or negative") Long amount) {
		this.accountNo = accountNo;
		this.amount = amount;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
}