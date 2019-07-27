package com.somecomp.xyz.exception;

import org.springframework.util.StringUtils;

public class SimpleSystemException  extends RuntimeException{
	private static final long serialVersionUID = 201906290000L;
	
	private String exceptionId;
	
	public SimpleSystemException(){
		super();
		generateExceptionID("",null);
	}
	
	public SimpleSystemException(String message){
		super(message);
		generateExceptionID(message,null);
	}
	
	public SimpleSystemException(Throwable cause){
		super(cause);
		generateExceptionID(cause.getMessage(),cause);
	}
	
	public SimpleSystemException(String message,Throwable cause){
		super(message,cause);
		generateExceptionID(message,cause);
	}
	
	private void generateExceptionID(String message,Throwable cause) {
		if(!(cause instanceof SimpleSystemException) ) { //only generate one ID
			exceptionId = String.valueOf(System.currentTimeMillis()); // simple approach to ID for now
		}
	}
	
	public String getMessage() {
		StringBuffer buffy = new StringBuffer();
		
		String superMessage = super.getMessage();
		if(!StringUtils.isEmpty(superMessage)) {
			if(superMessage.indexOf("exception id") != -1){
				return superMessage;				
			}else {
				buffy.append(superMessage);
			}				
		}
		buffy.append("\n exception id: ");
		buffy.append(exceptionId);
		return buffy.toString();
	}
	
	public String getExceptionId() {
		return (getCause() instanceof SimpleSystemException)
				? ((SimpleSystemException) getCause()).getExceptionId()
				: exceptionId;
	}
	
	/**
	 * Return the throwable that originated this exception - the lowest level root cause of this exception
	 * @return Throwable that is the root cause of this exception
	 */
	public Throwable getRootCause() {
		Throwable lastCause = this.getCause();
		Throwable cause = lastCause;
		while(cause != null) {
			cause = lastCause.getCause();
			if(cause != null) {
				lastCause = cause;
			}			
		}
		return lastCause;
	}
}
