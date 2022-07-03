package com.dpkbank.banking.controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dpkbank.banking.dto.MonthlyStatementResponseDto;
import com.dpkbank.banking.service.StatementService;

@RestController
@RequestMapping("/statements")
public class StatementController {
	
	@Autowired
	private StatementService statementService;
	
	@GetMapping("/{accountNo}/{month}/{year}")
	public ResponseEntity<MonthlyStatementResponseDto> getMonthlyTransaction2(@PathVariable @NotBlank long accountNo, @PathVariable @Min(1) @Max(12) int month, @PathVariable int year) {
		return statementService.getMonthlyTransaction(accountNo, month, year);
	}
}
