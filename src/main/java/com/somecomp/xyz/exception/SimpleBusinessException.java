package com.somecomp.xyz.exception;

import java.util.ArrayList;
import java.util.List;

public class SimpleBusinessException extends Exception{
	
	private static final long serialVersionUID = 201906290000L;
	
	List<SimpleBusinessProblem> problems = new ArrayList<SimpleBusinessProblem>();
	
	protected SimpleBusinessException() {
		super();
	}	

	public SimpleBusinessException(List<SimpleBusinessProblem> problemList) {
		problems.addAll(problemList);
	}
	
	public List<SimpleBusinessProblem> getProblems() {
		return problems;
	}
	
	@Override
	public String toString() {
		return "SimpleBusinessException [problems=" + problems + "]";
	}
}
