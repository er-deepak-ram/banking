package com.dpkbank.banking.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MonthlyStatementResponseDto {

	private LocalDate date;
	private LocalTime time;
	private Long accountNo;
	private Long availableBalance;
	private List<TransactionListDto> transactions;
	private String info;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}

	public Long getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(Long availableBalance) {
		this.availableBalance = availableBalance;
	}

	public List<TransactionListDto> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<TransactionListDto> transactions) {
		this.transactions = transactions;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
