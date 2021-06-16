package com.tudor.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFound extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

	public StudentNotFound() {
    }
	
	public StudentNotFound(String message) {
        super(message);
    }

    public StudentNotFound(String message, Throwable cause) {
        super(message, cause);
    }
	
	
	
}

   


