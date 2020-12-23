package com.hrbwmxx.framework.dao;

public interface ICrudDao {
	
	public boolean isExist(Object entity);
	
	public <T>T find(T entity);

	public <T>T insert(T entity);
	
	public boolean update(Object entity);
	
	public boolean update(Object entity, String mask);
	
	public boolean delete(Object entity);
	
	public <T> T updateFetchResult(T entity);
//	public int physicalDelete(Object entity);
	
}
