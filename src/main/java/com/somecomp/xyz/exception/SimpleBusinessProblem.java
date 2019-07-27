package com.somecomp.xyz.exception;

public interface SimpleBusinessProblem {
	static final long serialVersionUID = 201906290000L;
	
	public String getErrorKey();
	
	public String getErrorMessage();
}
