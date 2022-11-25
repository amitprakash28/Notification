package com.amit.exception;

public class UserNotExistException extends RuntimeException{
	
	public UserNotExistException() {}
	
	public UserNotExistException(String message) {
		super(message); 
	}
	
}
