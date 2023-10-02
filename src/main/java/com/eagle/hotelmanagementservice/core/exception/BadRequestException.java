package com.eagle.hotelmanagementservice.core.exception;

public class BadRequestException extends RuntimeException {
	public BadRequestException(String message) {
		super (message);
	}
}