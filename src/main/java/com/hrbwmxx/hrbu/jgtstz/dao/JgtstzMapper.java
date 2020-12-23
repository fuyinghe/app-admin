package com.hrbwmxx.hrbu.jgtstz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.hrbu.jgtstz.vo.Jgbm;
import com.hrbwmxx.hrbu.jgtstz.vo.Jglsb;
import com.hrbwmxx.hrbu.jgtstz.vo.Jgxx;
import com.hrbwmxx.hrbu.jgtstz.vo.TsTz;



public interface JgtstzMapper {
	List<Jgbm> queryJgbm();
	int queryZgjg(@Param("obj")Jgxx obj);
	List<TsTz> queryTsbmdm(@Param("obj")TsTz obj);
	List<Jgxx> queryJgxx(@Param("obj")Jgxx obj);
	void saveJglsb(@Param("obj")  Jglsb obj);
	void updateTstzzt(@Param("obj")TsTz obj);
	List<Jglsb> queryYdjg(@Param("obj")  Jglsb obj);
	int queryYdZs(@Param("obj")Jglsb obj);
	List<Jglsb> queryWdjg(@Param("obj")  Jglsb obj);
	int queryWdZs(@Param("obj")Jglsb obj);
	int updateTszt(@Param("obj")TsTz obj);

}
