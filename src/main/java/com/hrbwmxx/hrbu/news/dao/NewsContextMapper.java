package com.hrbwmxx.hrbu.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.querydsl.binding.QuerydslPredicate;

import com.hrbwmxx.hrbu.news.entity.NewsContext;

public interface NewsContextMapper {
	
	public List<NewsContext> getNRListByID(@Param("id")String id);
	public void saveContext(NewsContext newsContext);
	public void deleteContext(@Param("id")String id);
}
