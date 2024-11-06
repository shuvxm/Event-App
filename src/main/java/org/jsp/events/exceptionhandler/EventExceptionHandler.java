package org.jsp.events.exceptionhandler;

import org.jsp.events.exceptionclasses.InvalidEventIdException;
import org.jsp.events.exceptionclasses.NoEventFoundException;
import org.jsp.events.responsestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EventExceptionHandler {
	
	@ExceptionHandler(NoEventFoundException.class)
	public ResponseEntity<?> noEventFoundExceptionHandler(NoEventFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().status(HttpStatus.NOT_FOUND.value()).message("No event found").body(e.getMessage()).build());
		
	}
	@ExceptionHandler(InvalidEventIdException.class)
	public ResponseEntity<?> invalidEventIdExceptionHandler(InvalidEventIdException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseStructure.builder().status(HttpStatus.BAD_REQUEST.value()).message("No event found").body(e.getMessage()).build());
		
	}

}
