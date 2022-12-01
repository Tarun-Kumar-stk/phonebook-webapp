package com.phonebook.crud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(value = NoDataFoundException.class)
	public ResponseEntity<String> handleNoDataFoundException(NoDataFoundException ndfe){
		String exmsg = ndfe.getMessage();
		
		return new ResponseEntity<>(exmsg, HttpStatus.BAD_REQUEST);
	}

}
