package com.exalt.petclinic.exception;

import org.springframework.http.HttpStatus;

public enum ErrorEnum {
	USER_NOT_FOUND("User not foud",  HttpStatus.NOT_FOUND),
	PAGE_INVALID("page must be >1",HttpStatus.BAD_REQUEST),
	LIMIT_INVALID("limit must be >1",HttpStatus.BAD_REQUEST),
	EXIST_EMAIL("email already exist",HttpStatus.CONFLICT),
	EXIST_PHONE_NUMBER("phone number already exist",HttpStatus.CONFLICT),
	PHONE_NUMBER_INVALID("phone number must consist of 10 digits",HttpStatus.UNPROCESSABLE_ENTITY),
	EMAIL_INVALID("invalid email address",HttpStatus.UNPROCESSABLE_ENTITY),
	EXIST_CLIENT("Client already exist ",HttpStatus.CONFLICT);
	private String message;
	private HttpStatus status;
	ErrorEnum(String message, HttpStatus status) {
		this.message = message;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
}
