package org.jsp.events.exceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder // to build object without new keyword
@NoArgsConstructor
public class InvalidEventIdException extends RuntimeException{

	private String message;
	public InvalidEventIdException(String message){
		this.message=message;
	}

	public String getMessage() {
		return this.message;
	}
}
