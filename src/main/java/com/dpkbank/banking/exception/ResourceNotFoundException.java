package com.dpkbank.banking.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -9170990600746293020L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
