package com.hrbwmxx.system.dao;
 
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.system.vo.GNCustom;
import com.hrbwmxx.system.vo.YHCustom;
/**
 * 
* @title: YHMapper 
* @description：TODO
* @author： 刘威巍
* @date： 2018年1月12日 下午5:34:44
 */
public interface YHMapper {
	//用户列表
	List<YHCustom> queryYHListPage(@Param("page") Page page,@Param("obj")YHCustom obj);
    //用户总条数
	int queryYHListPageCount(@Param("page") Page page,@Param("obj") YHCustom obj);
	//返回单个对象
	YHCustom queryYHByField(@Param("obj") YHCustom obj);
	//保存对象
	void saveYH(@Param("obj")YHCustom obj);

	//保存单个
	void updateYH(@Param("obj")YHCustom obj);
	//删除用户
	int deleteYH(@Param("obj")YHCustom obj);
	//根据用户ID 分页查询已选功能
	List<GNCustom> findSelectedMenuByYhIdForPage(@Param("page")Page page, @Param("yh")YHCustom yh);
	//根据用户ID 分页查询已选功能 总条数
	int findSelectedMenuByYhIdForCount(@Param("page")Page page, @Param("yh")YHCustom yh);
	//根据用户ID 分页查询未选功能
	List<GNCustom> findUnSelectMenuByYhIdForPage(@Param("page")Page page, @Param("yh")YHCustom yh);
	//根据用户ID 分页查询未选功能 总条数
	int findUnSelectMenuByYhIdForCount(@Param("page")Page page, @Param("yh")YHCustom yh);
	// 添加未选菜单
	int addUnSelectMenu(@Param("gn")GNCustom gn,  @Param("yh")YHCustom yh);
	// 删除已选菜单
	int deleteSelectMenu(@Param("gn")GNCustom gn,  @Param("yh")YHCustom yh);
	// 根据用户ID 删除用户角色中间表
	int deleteYHJS( @Param("yh")YHCustom yh);
	
	//根据token查找用户，登录调用
	YHCustom queryYH(@Param("obj")YHCustom obj);
	//登录调用，入库用户token
	void updateYhTokenByUserName(@Param("obj")YHCustom obj);
	//根据用户名查询
	String querySfByUserName(@Param("obj") String userName);
	//同步学生对象
	int synchronizationYH(@Param("obj")YHCustom obj);
	
	//修改密码
	int updatePassWord(@Param("yhId")String yhId,@Param("oldPass")String oldPass,@Param("newPass")String newPass);
	//根基用户Id找密码
	String VerifitPass(@Param("userId") String userId);
	//修改密码
	int UpdatePassWord(@Param("userId") String userId,@Param("passWord") String passWord);
}
