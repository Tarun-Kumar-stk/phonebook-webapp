package com.phonebook.crud.exceptions;

public class NoDataFoundException extends RuntimeException {
	
	public NoDataFoundException() {
		
	}
	
	public NoDataFoundException(String msg) {
		super(msg);
	}

}
