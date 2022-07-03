package com.dpkbank.banking.exception;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@ExceptionHandler(InvalidTransactionException.class)
	public ResponseEntity<ErrorDetails> handleInvalidTransactionException(InvalidTransactionException exception, WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NO_CONTENT);
	}
	
	@ExceptionHandler(ArithmeticException.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(ArithmeticException exception, WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorDetails> handleNullPointerException(NullPointerException exception, WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorDetails> handleDataIntegrityViolationException(DataIntegrityViolationException exception, WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DateTimeException.class)
	public ResponseEntity<ErrorDetails> handleDateTimeException(DateTimeException exception, WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UpiTransactionException.class)
	public ResponseEntity<ErrorDetails> handleUpiTransactionException(UpiTransactionException exception, WebRequest webRequest) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
}