package com.somecomp.xyz.model;

public class XyzSimpleRequest {
	String xyzId;
	String xyzCompId;
	
	public XyzSimpleRequest() {		
	}
	
	public XyzSimpleRequest(String xyzId,String xyzCompId) {	
		super();
		this.xyzId = xyzId;
		this.xyzCompId = xyzCompId;
	}

	public String getXyzId() {
		return xyzId;
	}

	public void setXyzId(String xyzId) {
		this.xyzId = xyzId;
	}

	public String getXyzCompId() {
		return xyzCompId;
	}

	public void setXyzCompId(String xyzCompId) {
		this.xyzCompId = xyzCompId;
	}

	@Override
	public String toString() {
		return "XyzSimpleRequest [xyzId=" + xyzId + ", xyzCompId=" + xyzCompId + "]";
	}
}
