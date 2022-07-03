package com.dpkbank.banking.exception;

public class InvalidTransactionException extends RuntimeException {

	private static final long serialVersionUID = 5681714239308139474L;

	public InvalidTransactionException(String message) {
		super(message);
	}
}
