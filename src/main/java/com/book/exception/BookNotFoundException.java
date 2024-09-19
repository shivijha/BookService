package com.book.exception;

public class BookNotFoundException extends RuntimeException {
	String resourceName;
	String fieldName;
	long fieldValue;
	
	public BookNotFoundException(String resourceName,String fieldName,long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
}
