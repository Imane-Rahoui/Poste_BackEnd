package com.pfa.jee.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.pfa.jee.responses.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(value={Exception.class})
	public ResponseEntity<Object> HandleException(Exception ex, WebRequest request) {
		
		ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public ResponseEntity<Object> HandleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) 
	{
		Map<String, String> errors = new HashMap<>();
		 
	    ex.getBindingResult().getFieldErrors().forEach(error -> 
	        errors.put(error.getField(), error.getDefaultMessage()));
		
		return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
}
