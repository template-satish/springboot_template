package com.somecomp.xyz.model;

public class XyzSimpleResponse {
	private String xyzComponentId;
	private String xyzHdrId;
	
	public XyzSimpleResponse() {		
	}
	
	public XyzSimpleResponse(String xyzHdrId,String xyzComponentId) {	
		super();
		this.xyzHdrId = xyzHdrId;
		this.xyzComponentId = xyzComponentId;
	}
	
	public String getXyzComponentId() {
		return xyzComponentId;
	}

	public void setXyzComponentId(String xyzComponentId) {
		this.xyzComponentId = xyzComponentId;
	}

	public String getXyzHdrId() {
		return xyzHdrId;
	}

	public void setXyzHdrId(String xyzHdrId) {
		this.xyzHdrId = xyzHdrId;
	}

	@Override
	public String toString() {
		return "XyzSimpleResponse [xyzComponentId=" + xyzComponentId + ", xyzHdrId=" + xyzHdrId + "]";
	}
}
