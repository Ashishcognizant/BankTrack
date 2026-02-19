package com.cts.exception;
 
public class AccountNotFoundException extends RuntimeException {
	public AccountNotFoundException(String message) {
		super(message);
		}
	}