package com.hrbwmxx.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.system.model.GN;
import com.hrbwmxx.system.vo.GNCustom;

public interface GNMapper {
	// 分页查询
	List<GNCustom> findGNListForPage(@Param("page")Page page, @Param("gn")GN gn);
	//分页查询总条数
	int findGNListForCount(@Param("page")Page page, @Param("gn")GN gn);
	//修改
	int updateGn(@Param("gn")GNCustom gn);
	// 根据所选菜单级别 ，查出所有上级菜单
	List<GNCustom> findGnListByPid(@Param("gn")GNCustom gn);
	//保存
	int saveGN(@Param("gn")GNCustom gn);
	//根据主键，查询是否有子菜单
	List<GNCustom> findChildGNByKey(@Param("gn")GNCustom gn);
	//删除
	int deleteGNByKey(@Param("gn")GNCustom gn);
	//根据主键，分页查询子菜单
	List<GNCustom> findChildGNListFroPage(@Param("page")Page page,@Param("gn")GNCustom gn);
	//根据主键，分页查询子菜单 总条数
	int findChildGNByKeyForCount(@Param("page")Page page,@Param("gn")GNCustom gn);
	// 查询菜单列表 ， 用于主页面左侧菜单展示
	List<GNCustom> findGNList(@Param("gn")GNCustom gn);
	List<GNCustom> findFirstGNList(@Param("gn")GNCustom gn);
	List<GNCustom> findGNOne(@Param("page")Page page, @Param("gn")GN gn);
}
