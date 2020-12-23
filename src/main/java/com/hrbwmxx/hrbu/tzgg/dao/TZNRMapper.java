package com.hrbwmxx.hrbu.tzgg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.querydsl.binding.QuerydslPredicate;

import com.hrbwmxx.hrbu.tzgg.model.TZNR;


public interface TZNRMapper {
	
	public List<TZNR> getNRListByID(@Param("tzid")String id);
	public void saveContext(TZNR newsContext);
	public void deleteContext(@Param("tzid")String id);
}
