package com.somecomp.xyz.exception;

public class SimpleBusinessProblemImpl implements SimpleBusinessProblem  {
	
	private final String errorKey;
	private final String errorMessage;
	
	public String getErrorKey() {
		return errorKey;
	}

	public String getErrorMessage() {
		return errorMessage;
	}	
	
	public SimpleBusinessProblemImpl(String errorKey,String errorMessage) {
		this.errorKey = errorKey;
		this.errorMessage = errorMessage;
	}
	
	@Override
	public String toString() {
		return "SimpleBusinessProblemImpl [errorKey=" + errorKey + ", errorMessage=" + errorMessage + "]";
	}

}
