package com.ibellar.calendar;

public class IBLException extends Exception {
	
	private int errorcode;
	private String errorMessage;
	
	public IBLException(int code) {
		super(IBLErrorCode.codeToString(code));
		errorcode = code;
		errorMessage = IBLErrorCode.codeToString(code);
	}

	public int getErrorcode() {
		return errorcode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}


	
	
	
}
