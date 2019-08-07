package com.epam.jdbc.exception;

public class DbConnectionFailedException extends RuntimeException {
	
	public DbConnectionFailedException(String message) {
		super(message);
	}
}
