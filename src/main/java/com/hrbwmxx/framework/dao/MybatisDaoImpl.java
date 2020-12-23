package com.hrbwmxx.framework.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.mybatis.spring.SqlSessionTemplate;

import com.hrbwmxx.framework.dao.sql.SQLBuilder;
import com.hrbwmxx.framework.dao.sql.SqlText;
import com.hrbwmxx.framework.dao.util.BasicRowProcessor;
import com.hrbwmxx.framework.dao.util.RowProcessor;
import com.hrbwmxx.framework.exception.BusinessException;
import com.hrbwmxx.framework.exception.LackOfPrimaryKeyException;
import com.hrbwmxx.framework.model.LogicalDeleteAble;
import com.hrbwmxx.framework.model.TimestampAwareBean;


public class MybatisDaoImpl implements ICrudDao{
	
	private static final RowProcessor rowProcessor = new BasicRowProcessor();
	
	protected SqlSessionTemplate sqlSessionTemplate;

	public MybatisDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public boolean isExist(Object entity) {
		SqlText sqlText = SQLBuilder.parsePKExistSQL(entity);
		Integer count = (Integer)executeQuery(sqlText, Integer.class , entity);
		if (count != null && count > 0) {
			return true;
		}
		return false;
	}

	public <T> T find(T entity) {
		SqlText sqlText = SQLBuilder.parseSelectSQL(entity);
		final Class<?> clazz = entity.getClass();
		T queryResult = (T) executeQuery(sqlText, clazz, entity);
		return queryResult;
	}

	public <T> T insert(T entity) {

		SqlText sqlText = SQLBuilder.parseInsertSQL(entity);
		int rowCount = executeUpdate(sqlText, entity);
		if (rowCount == 1) {
			if (SQLBuilder.isAutoIncrement(entity)) {
				sqlText = SQLBuilder.parseSelectLastInsertIdSQL();
				Integer lastId = (Integer)executeQuery(sqlText, Integer.class , null);
				SQLBuilder.setAutoIncrementFieldValue(entity, lastId);
			}
			return entity;
		} else {
			throw new BusinessException("error.insertFaild");
		}
		
	}

	public boolean update(Object entity) {

		SqlText sqlText = SQLBuilder.parseUpdateSQL(entity);
		int rowCount = executeUpdate(sqlText, entity);		
		if (rowCount > 1) {
			throw new LackOfPrimaryKeyException();
		} else if (rowCount < 1){
			throw new BusinessException("error.noDataFound");
		}
		return rowCount == 1;
	}
	
	public boolean update(Object entity, String mask) {
		
		int rowCount = 0;
		if (mask == null || mask.isEmpty() ) {
			return false;
		}
		SqlText sqlText = SQLBuilder.parsePartlyUpdateSQL(entity, false, mask);
		
		if (entity instanceof TimestampAwareBean) {
			TimestampAwareBean timeatampaware = (TimestampAwareBean)entity;
			timeatampaware.setModifiedTimestamp(new Timestamp(new Date().getTime()));
		}
		
		rowCount = executeUpdate(sqlText, entity);		
		if (rowCount > 1) {
			throw new LackOfPrimaryKeyException();
		} else if (rowCount < 1){
			throw new BusinessException("error.noDataFound");
		}
		return rowCount == 1;
	}
	
	public boolean delete(Object entity) {
		
		SqlText sqlText;
		int rowCount = 0;
		if (entity instanceof LogicalDeleteAble) {
			sqlText = SQLBuilder.parseLogicalDeleteSQL(entity);
			rowCount = executeUpdate(sqlText, entity);
			if (rowCount > 1) {
				throw new LackOfPrimaryKeyException();
			} else if (rowCount < 1) {
				throw new BusinessException("error.noDataFound");
			}
		} else {
			sqlText =  SQLBuilder.parseDeleteSQL(entity);
			rowCount = executeUpdate(sqlText, entity);
			if (rowCount > 1){
				throw new LackOfPrimaryKeyException();
			} else if (rowCount < 1){
				throw new BusinessException("error.noDataFound");
			}
		}
		
//		if (SQLBuilder.isAnnonatedDeleteRelatedTables(entity)) {
//			SqlText[] sqlTexts = SQLBuilder.parseDeleteRelatedTablesSQL(entity);
//			for (SqlText sqlTextDelete : sqlTexts) {
//				executeUpdate(sqlTextDelete, entity);
//			}
//		}
				
		return rowCount == 1;
	}
	
	public <T> T updateFetchResult(T entity) {
		T result = null;
		boolean successful = update(entity);
		if (successful) {
			result = find(entity);
			if (result == null) {
				throw new BusinessException("没找到数据");
			}
		} 
		return result;
	}
	
	protected Object executeQuery(final SqlText sql, final Class<?> resultClazz, final Object parameterObject){
		try {
			Connection conn = sqlSessionTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.getText());
			ResultSet result = null;
			try {
				if (sql.getParameterMappings() != null && parameterObject != null) {
					Object[] parameters = SQLBuilder.getParameterData(sql.getParameterMappings(), parameterObject);
					SQLBuilder.setStatementParameters(pstmt, sql.getParameterMappings(), parameters);
				}
				result = pstmt.executeQuery();
				if (result.next()) {
					return rowProcessor.toBean(result,resultClazz);
				} return null;
			} finally {
				try {
					if (result != null)	result.close();
				} catch (SQLException e) {
					// ignore
				}
				try {
					if (pstmt != null) pstmt.close();
				} catch (SQLException e) {
					// ignore
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected int executeUpdate(final SqlText sql, final Object parameterObject ){
		try {
			Connection conn = sqlSessionTemplate.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.getText());
			Object[] parameters = SQLBuilder.getParameterData(sql.getParameterMappings(), parameterObject);
			SQLBuilder.setStatementParameters(pstmt, sql.getParameterMappings(), parameters);
			try {
				return pstmt.executeUpdate();
			} finally {
				try {
					if (pstmt != null) pstmt.close();
		        } catch (SQLException e) {
		          // ignore
		        }
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
