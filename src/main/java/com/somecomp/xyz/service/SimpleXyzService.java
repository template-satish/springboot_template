package com.somecomp.xyz.service;

import java.util.List;

import com.somecomp.xyz.exception.SimpleBusinessException;
import com.somecomp.xyz.model.XyzSimpleRequest;
import com.somecomp.xyz.model.XyzSimpleResponse;

public interface SimpleXyzService {
	public List<XyzSimpleResponse> create(XyzSimpleRequest request,String xAppCorelationId) throws SimpleBusinessException;
}
