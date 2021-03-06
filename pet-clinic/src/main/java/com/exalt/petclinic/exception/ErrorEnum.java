package com.exalt.petclinic.exception;

import org.springframework.http.HttpStatus;

public enum ErrorEnum {
	USER_NOT_FOUND("User not foud", HttpStatus.NOT_FOUND),
	WRONG_ID_ENTERED("The Id must be >0", HttpStatus.BAD_REQUEST),
	WORKER_NOT_FOUND("No worker hold this Id", HttpStatus.BAD_REQUEST),
	WRONG_ID_ENTERED_IN_UPDATE("The Id you updated must be >0", HttpStatus.BAD_REQUEST),
	CLIENT_NOT_FOUND("No client with this ID", HttpStatus.NOT_FOUND),
	SCHEDULE_NOT_FOUND("No schedule with this ID", HttpStatus.NOT_FOUND),
	PET_NOT_FOUND("No Pet with this ID", HttpStatus.NOT_FOUND),
	PAGE_INVALID("page must be >=1", HttpStatus.BAD_REQUEST),
	LIMIT_INVALID("limit must be >=1", HttpStatus.BAD_REQUEST), EXIST_EMAIL("email already exist", HttpStatus.CONFLICT),
	EXIST_PHONE_NUMBER("phone number already exist", HttpStatus.CONFLICT),
	PHONE_NUMBER_INVALID("phone number must consist of 10 digits", HttpStatus.BAD_REQUEST),
	EMAIL_INVALID("invalid email address", HttpStatus.BAD_REQUEST),
	EXIST_CLIENT("Client already exist ", HttpStatus.CONFLICT),
	FILE_NOT_FOUND("Wrong file name entered", HttpStatus.BAD_REQUEST),
	CAN_NOT_READ_FILE("cant read the file ", HttpStatus.INTERNAL_SERVER_ERROR),
	UNSUPORTED_ENCODING("unsuporten encoding to UTF-8 ", HttpStatus.INTERNAL_SERVER_ERROR),
	CAN_NOT_WRITE_TO_FILE("Wrong file name entered", HttpStatus.BAD_REQUEST),
	INVALID_WORKING_FIELD("Working field must be oe of 3 (Owner,Admin,Worker ", HttpStatus.INTERNAL_SERVER_ERROR);

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
