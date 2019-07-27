package com.somecomp.xyz.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.somecomp.xyz.exception.SimpleBusinessException;
import com.somecomp.xyz.exception.SimpleBusinessProblem;
import com.somecomp.xyz.exception.SimpleBusinessProblemImpl;
import com.somecomp.xyz.model.XyzSimpleRequest;
import com.somecomp.xyz.model.XyzSimpleResponse;
import com.somecomp.xyz.service.SimpleXyzService;

@RestController
public class SimpleXyzController {

	private static final Logger logger = LogManager.getLogger(SimpleXyzController.class);

	private final SimpleXyzService simpleXyzService;

	public SimpleXyzController(SimpleXyzService simpleXyzService) {
		this.simpleXyzService = simpleXyzService;
	}

	/**
	 * Creates Xyz.
	 * @param xyz   	The object to create.
	 * @return          A ResponseEntity that contains the created Xyz
	 */
	@PostMapping("/xyz/createSimple")
	public ResponseEntity<?> createSimpleXyz(@RequestHeader("x-appCorelationId") String xAppCorelationId,@RequestBody XyzSimpleRequest request) {
		final String METHOD_NAME = "createSimpleXyz";
		logger.info("START : {} {}",METHOD_NAME,xAppCorelationId);

		/** create the Xyz **/
		List<XyzSimpleResponse> xyzSimpleResponseList = null;
		try {
			xyzSimpleResponseList = simpleXyzService.create(request,xAppCorelationId);
			return ResponseEntity.status(201).body(xyzSimpleResponseList);
		} catch (SimpleBusinessException pbe) {
			logger.error("{} , {} END , Create Simple Xyz with xyzId: {} , Errored out with BusinessException {}",METHOD_NAME,xAppCorelationId,request.getXyzId(),pbe);
			/** these are the validation exceptions caught in PL/SQL **/
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(pbe.getProblems());			
		}catch(Exception e) {
			/** this will catch any unexpected exceptions and also if DAO throws SQLException which will come as a runtime exception **/
			logger.error("{} , {} END , Create Simple Xyz with xyzId: {} , Errored out with Unknown EXception {}",METHOD_NAME,xAppCorelationId,request.getXyzId(),e);
			SimpleBusinessProblem problem = new SimpleBusinessProblemImpl("system error", "Please contact System Administrator");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
		}
			

	
}


}
