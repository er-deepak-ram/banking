package com.dpkbank.banking.service;

import org.springframework.http.ResponseEntity;

import com.dpkbank.banking.dto.MonthlyStatementResponseDto;

public interface StatementService {

	ResponseEntity<MonthlyStatementResponseDto> getMonthlyTransaction(long accountNo, int month, int year);
}
