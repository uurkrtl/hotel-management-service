package com.eagle.hotelmanagementservice.core.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomizedExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleNotException(NotFoundException exc){
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException exc){
		ErrorResponse error = new ErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse>handleMethodArgumentNotValid(MethodArgumentNotValidException exc){
		List<ObjectError>errors=exc.getAllErrors();
		String errorMessage="Errors: ";
		for (ObjectError fieldError:errors) {
			errorMessage=errorMessage + " - " + fieldError.getDefaultMessage();
		}
		ErrorResponse error=new ErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(errorMessage);
		error.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception exc){
		ErrorResponse error=new ErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage("Something went wrong: "+exc.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.BAD_REQUEST);
	}
}