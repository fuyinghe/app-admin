package com.hrbwmxx.hrbu.tstz.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.hrbu.tstz.vo.TsTz;
import com.hrbwmxx.hrbu.tstz.vo.XsBj;
import com.hrbwmxx.hrbu.tstz.vo.XsXx;
import com.hrbwmxx.hrbu.tstz.vo.Xslsb;

/**
 * 
* @title: TstzMapper 
* @description：菜单dao
* @author： shijiajun
* @date： 2018年5月11日 
 */
public interface TstzMapper {
	List<XsBj> queryBj(@Param("page")Page page);
	int queryCountBj(@Param("page")Page page);
	List<TsTz> queryTsBjRy(@Param("obj")TsTz obj);
	XsXx queryXszs(@Param("obj")XsXx obj);
	List<XsXx> queryXsxx(@Param("obj")XsXx obj);
	void saveXslsb(@Param("obj")  Xslsb obj);
	void updateXslsbzt(@Param("obj")  Xslsb obj);
	List<Xslsb> queryXslsbzt(@Param("obj")  Xslsb obj);
	Xslsb queryYdrs(@Param("obj")Xslsb obj);
	int updateTszt(@Param("obj")TsTz obj);
	int queryZgxs(@Param("obj")XsXx obj);
	List<Xslsb> queryYdxs(@Param("obj")  Xslsb obj);
	Integer queryYdZs(@Param("obj")Xslsb obj);
	List<Xslsb> queryWdxs(@Param("obj")  Xslsb obj);
	int queryWdZs(@Param("obj")Xslsb obj);
	Map<String, Object> queryCs(@Param("obj")Xslsb obj);
	void updateYdrs(@Param("obj")TsTz obj);
	void updateTstzzt(@Param("obj")TsTz obj);

}
