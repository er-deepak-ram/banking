package com.dpkbank.banking.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpiTransactionRequestDto {
	
	@NotNull
	@Size(min = 10, max = 10)
	private String fromMobileNo;
	
	@NotNull
	@Size(min = 10, max = 10)
	private String toMobileNo;
	
	@NotNull
	@Min(value = 1)
	private Long amount;
}
