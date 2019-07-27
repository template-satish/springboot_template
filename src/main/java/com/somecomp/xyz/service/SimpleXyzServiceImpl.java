package com.somecomp.xyz.service;

import java.util.List;

import com.somecomp.xyz.dao.SimpleXyzDao;
import com.somecomp.xyz.exception.SimpleBusinessException;
import com.somecomp.xyz.model.XyzSimpleRequest;
import com.somecomp.xyz.model.XyzSimpleResponse;

public class SimpleXyzServiceImpl  implements SimpleXyzService{
	
	private final SimpleXyzDao simpleXyzDao;
	
	
	public SimpleXyzServiceImpl(SimpleXyzDao simpleXyzDao) {
		this.simpleXyzDao = simpleXyzDao;
	}

	@Override
	public List<XyzSimpleResponse> create(XyzSimpleRequest request,String xAppCorelationId) throws SimpleBusinessException{
		List<XyzSimpleResponse> response = simpleXyzDao.create(request, xAppCorelationId);
		return response;
	}
}
