package com.dpkbank.banking.exception;

public class UpiTransactionException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UpiTransactionException(String message) {
		super(message);
	}

}
