package com.hrbwmxx.framework.dao.factory;

import java.io.IOException;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.NestedIOException;

public class AdvanceSqlSessionFactoryBean extends SqlSessionFactoryBean{
	
	protected SqlSessionFactory buildSqlSessionFactory(){
		
		try {
			
			return super.buildSqlSessionFactory();  
			
		} catch (NestedIOException e) {  
		    e.printStackTrace(); // XML 有错误时打印异常。
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		    ErrorContext.instance().reset();  
		}
		
		return null;
	}
}
