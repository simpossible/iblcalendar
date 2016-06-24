package com.ibellar.calendar;

public class IBLException extends Exception {
	public IBLException(int code) {
		super(IBLErrorCode.codeToString(code));
	}
}
