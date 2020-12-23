package com.hrbwmxx.framework.fj.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrbwmxx.framework.fj.dao.FJCSMapper;
import com.hrbwmxx.framework.fj.dao.FJMapper;
import com.hrbwmxx.framework.fj.model.FJ;
import com.hrbwmxx.framework.fj.model.FJCS;
import com.hrbwmxx.framework.fj.vo.FJCustom;
import com.hrbwmxx.framework.util.Constant;
import com.hrbwmxx.framework.util.PropertiesUtil;
import com.hrbwmxx.framework.util.TimeUtil;
/**
 * 
* @title: FJServiceImpl 
* @description：附件上传，入库接口
* @author： 李静雨
* @date： 2018年1月15日 下午3:18:12
 */
@Service
public class FJServiceImpl implements IFJService {
	@Autowired
	private FJMapper fjMapper;
	@Autowired
	private FJCSMapper fjcsMapper;
	/**
	 * 
	* @MethodName: upload 
	* @description : 附件上传并入库(1校验 2上传处理 3 保存入库)
	* @author：lijingyu
	* @date： 2018年1月15日 下午3:20:32
	* @param file
	* @param obj
	* @return json字符串
	* @throws Exception String
	 */
	public String upload(MultipartFile file, FJCustom obj) throws Exception {
		//0.0定义返回参数
		String errmsg="上传成功";
		String jsonError="";
		//0.1判断文件大小,文件不存在
		if(file.getSize()<=0){
			errmsg="无效文件";
			jsonError="{\"errcode\":\"200\",\"errmsg\":\""+errmsg+"\"}";
			return  jsonError;
  	    }
		//0.2判断文件代码是否在参数表中配置
		if(!checkFJCSParam(obj)) {
			errmsg="文件参数不存在，请去参数表配置(t_fj_cs)!";
			jsonError="{\"errcode\":\"200\",\"errmsg\":\""+errmsg+"\"}";
			return  jsonError;
		}
		
		//0.3判断文件文件后缀
		if(!checkFJType(file.getOriginalFilename(),obj)){
			errmsg="上传文件类型可能影响系统安全，上传失败！";
			jsonError="{\"errcode\":\"200\",\"errmsg\":\""+errmsg+"\"}";
			return  jsonError;
		}
		//1.1文件上传
		obj = transferToDir(file,obj);
		//2.1文件入库
		transferToDb(file,obj);
		//3.1返回参数到前台
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(  obj );
	}
	/**
	 * 
	* @MethodName: transferToDb 
	* @description : 文件持久化到数据中
	* @author：lijingyu 
	* @date： 2018年1月22日 下午1:57:57
	* @param file
	* @param obj void
	 */
	private void transferToDb(MultipartFile file, FJCustom obj) {
		//1.1保存数据库（上传时候作无效，保存时候要更改为有效）
		String attrId=Constant.getUUID();
		//1.2判断是否是图片，拼凑图片路径
		String[] temp1=obj.getYmc().split("\\.");
		String filefix=temp1[temp1.length-1];
		int a=Constant.IMAGETYPE.indexOf(filefix);
		if(a!=-1){obj.setTplj(Constant.showImg2Url+attrId);}
		obj.setXzlj( Constant.downUrl+attrId);//下载路径（应该考虑不同服务器之间引用）
		obj.setZt(Constant.SYS_STATE_0+"");//是否启用Y/N
		obj.setFjId(attrId);//主键id
		fjMapper.saveFJ(obj);
		
	}
	/**
	 * 
	* @MethodName: transferToDir 
	* @description : 文件上传到指定文件夹中
	* @author：lijingyu 
	* @date： 2018年1月22日 下午1:32:19
	* @param file
	* @param obj
	* @throws IOException void
	 */
	private FJCustom transferToDir(MultipartFile file, FJCustom obj) throws IOException {
		String oriName = file.getOriginalFilename();
		//1.1获得文件根目录配置
		Properties properties=PropertiesUtil.getKey("system.properties");
		String rootPath=properties.getProperty("fileDir");
		String basePath=properties.getProperty("basePath");
		//1.2获得文件分割符
		String separator =PropertiesUtil.SEPARATOR;
		//1.3创建相对路径
		String relativePath= TimeUtil.getDay();
		File fileDir = new File(rootPath +separator+obj.getDm()  +separator +relativePath );
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		
		//1.3获得新文件名字
		String suffix = oriName.substring(oriName.lastIndexOf("."));
		String newName = System.nanoTime() + suffix;
		//spring框架创建新文件
		File newFile = new File(fileDir + separator + newName);
		newFile.createNewFile();
		file.transferTo(newFile);
		//2.1spring框架创建新文件
		obj.setWjqlj(rootPath +separator+obj.getDm()  +separator +relativePath);
		obj.setXmc(newName);//时间戳名字
		obj.setYmc(oriName);//原始文件名字
		obj.setXmlj(basePath );//http根路径，用于拓展分布式环境  rootPath
		return obj;
	}
	/**
	 * 
	* @MethodName: checkFJType 
	* @description : 判断文件文件后缀
	* @author：lijingyu 
	* @date： 2018年1月22日 下午1:13:08
	* @param obj
	* @return boolean
	 */
	private boolean checkFJType(String oriName,FJCustom obj) {
		boolean bool=false;
		String suffix = oriName.substring(oriName.lastIndexOf("."));
		if(".exe".equals(suffix) || ".bat".equals(suffix)){
			bool=true;
		}
		return bool;
	}
	/**
	 * 
	* @MethodName: checkFJCSParam 
	* @description : 校验是否在参数表中配置
	* @author：lijingyu 
	* @date： 2018年1月22日 下午1:06:37
	* @param obj
	* @return boolean
	 */
	private boolean checkFJCSParam(FJCustom obj) {
		boolean bool=true;
		FJCS queryObj=new FJCS();
		queryObj.setFjdm(  obj.getDm() );
		queryObj=fjcsMapper.queryFJCSByField(queryObj);
		if(null==queryObj) {
			bool= false;
		}
		return bool;
	}
	public List<FJCustom> queryFJList(FJCustom obj) {
		return fjMapper.queryFJList(obj);
	}
	
	public void saveBatcFJList(List<FJCustom> list) {
		fjMapper.saveBatcFJList(list);
		
	}

	
	public void updateFj(FJCustom obj) {
		fjMapper.updateFj(obj);
	}

	
	public void saveFJ(FJCustom obj) {
		fjMapper.saveFJ(obj);
	}
 
}
