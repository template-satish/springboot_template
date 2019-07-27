package com.somecomp.xyz.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.somecomp.xyz.exception.SimpleBusinessException;
import com.somecomp.xyz.exception.SimpleBusinessProblem;
import com.somecomp.xyz.exception.SimpleBusinessProblemImpl;
import com.somecomp.xyz.exception.SimpleSystemException;
import com.somecomp.xyz.model.XyzSimpleRequest;
import com.somecomp.xyz.model.XyzSimpleResponse;

@Repository
public class SimpleXyzDaoImpl implements SimpleXyzDao {
	
	private static final Logger logger = LogManager.getLogger(SimpleXyzDaoImpl.class);
	
	@Autowired
	DataSource dataSource;
	
	@Override
	public 	List<XyzSimpleResponse> create(XyzSimpleRequest xyzRequest,String xAppCorelationId) throws SimpleBusinessException{
		final String METHOD_NAME = "create";
		logger.info("aaa");
		List<XyzSimpleResponse> xyzSimpleResponseList = new ArrayList<XyzSimpleResponse>();
		Connection conn = null;
		CallableStatement cStmt = null;
		
		XyzSimpleResponse response = null;
		
		String PACKAGE_CALL = "? := XYZ;";
		
		StringBuffer buffer = new StringBuffer(200);
		buffer.append("DECLARE\n");
		buffer.append("BEGIN\n");
		buffer.append(PACKAGE_CALL).append("\n");
		buffer.append("END;");
		
		try {
			conn = dataSource.getConnection();
			cStmt = conn.prepareCall(buffer.toString());
			cStmt.registerOutParameter(1, Types.VARCHAR);
			cStmt.setNull(1, Types.VARCHAR);
			
			cStmt.setInt(2, Integer.parseInt(xyzRequest.getXyzId()));
			
			cStmt.registerOutParameter(3, Types.VARCHAR);
			cStmt.setNull(3, Types.VARCHAR);
			
			cStmt.registerOutParameter(4, Types.VARCHAR);
			cStmt.setNull(4, Types.VARCHAR);
			
			cStmt.executeUpdate();
			
			/** there could be some validation errors from stored proc **/
			String errorOpVal = cStmt.getString(3);
			/** if everything is good we will get back a list of xyz comp detail id **/
			String successOpVal = cStmt.getString(4);
			
			/** if there are any validation errors throw a BusinessException **/
			if(null != errorOpVal && errorOpVal.trim().length() > 0 && errorOpVal.contains("Invalid")) {
				logger.info("we got an error in response {}",errorOpVal);
				
				SimpleBusinessProblem problem = new SimpleBusinessProblemImpl("invalid_xyz_id",errorOpVal) ;
				List<SimpleBusinessProblem> problemList = new ArrayList<SimpleBusinessProblem>();
				problemList.add(problem);
				throw new SimpleBusinessException(problemList);									
			}
			
			if(null != successOpVal && successOpVal.trim().length() > 0 ) {
				logger.info("we have following xyz comp id in response {}",successOpVal);
			}
			response = new XyzSimpleResponse();
			response.setXyzComponentId(successOpVal);
			response.setXyzHdrId(xyzRequest.getXyzId());
			xyzSimpleResponseList.add(response);
		}catch(SQLException sqle) {
			/** all database related operations could run into this exception so have this catch block **/
			sqle.printStackTrace();
			if(logger.isDebugEnabled()) {
				StringBuffer errorMessage = new StringBuffer();
				errorMessage.append("SQL Package call failed: " + buffer + "\n");
				errorMessage.append("SQL Exception");
				errorMessage.append(sqle.toString());
				errorMessage.append("\n\twith errorCode: ");
				errorMessage.append(sqle.getErrorCode());
				errorMessage.append("\n\twith SqlState: ");
				errorMessage.append(sqle.getSQLState());
				errorMessage.append("\n\twith nextException: ");
				errorMessage.append(sqle.getNextException());
				logger.debug(errorMessage.toString(),sqle);
			}
			logger.error("{},{},{} something wrong ! ",SimpleXyzDaoImpl.class,METHOD_NAME,sqle);
			/** now wrap this SQLException info a business exception **/
			throw new SimpleSystemException(sqle.toString(),sqle);
		}finally {
			try {
				cStmt.close();
				conn.close();
			}catch(SQLException e) {
				logger.error("{},{},{} something wrong while closing db resources ! ",SimpleXyzDaoImpl.class,METHOD_NAME,e);
			}
		}
		logger.info("{} END {}",METHOD_NAME,xAppCorelationId);
		return xyzSimpleResponseList;
	}

}
