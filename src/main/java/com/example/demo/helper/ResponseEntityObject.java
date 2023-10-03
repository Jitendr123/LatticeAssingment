package com.example.demo.helper;

import org.springframework.http.HttpStatus;

import lombok.Data;

public @Data class ResponseEntityObject<T> {
	
	private Boolean status;
	private String message;
	private T object;
	private Long totalItems;
	private HttpStatus httpStatus;
	private int httpCode;

	public ResponseEntityObject() {
	}

	
	public ResponseEntityObject(HttpStatus httpStatus,int httpCode, boolean status, String message, T object,Long totalItems) {
		super();
		this.status = status;
		this.message = message;
		this.object = object;
		this.totalItems = totalItems;
		this.httpStatus = httpStatus;
		this.httpCode = httpCode;
	}
	
	
	public ResponseEntityObject(HttpStatus httpStatus,int httpCode, boolean status, String message, T object) {
		super();
		this.status = status;
		this.message = message;
		this.object = object;
		this.httpStatus = httpStatus;
		this.httpCode = httpCode;
	}
	
	public ResponseEntityObject( boolean status, String message, T object, Long totalItems) {
		super();
		this.status = status;
		this.message = message;
		this.object = object;
		this.totalItems = totalItems;
	}
	
	public ResponseEntityObject(boolean status, String message, Long totalItems) {
		super();
		this.status = status;
		this.message = message;
		this.totalItems = totalItems;
	}
	
	public ResponseEntityObject( boolean status, String message, T object) {
		super();
		this.status = status;
		this.message = message;
		this.object = object;
	
		
	}

	public ResponseEntityObject( boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
		
		
	}
}


