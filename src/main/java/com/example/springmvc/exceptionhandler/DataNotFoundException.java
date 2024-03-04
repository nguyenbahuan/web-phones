package com.example.springmvc.exceptionhandler;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataNotFoundException(String message) {
	    super(message);
	  }
 
}
