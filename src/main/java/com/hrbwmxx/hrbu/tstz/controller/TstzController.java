package com.hrbwmxx.hrbu.tstz.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Page;
import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.util.TimeUtil;

import com.hrbwmxx.hrbu.tstz.dao.TstzMapper;
import com.hrbwmxx.hrbu.tstz.service.ITstzService;
import com.hrbwmxx.hrbu.tstz.vo.TsTz;
import com.hrbwmxx.hrbu.tstz.vo.XsBj;
import com.hrbwmxx.hrbu.tstz.vo.XsXx;
import com.hrbwmxx.hrbu.tstz.vo.Xslsb;
import com.hrbwmxx.system.model.Login;
import com.hrbwmxx.system.service.LoginUtil;
import com.webmos.framework.controller.AbstractModelController;
import com.webmos.framework.model.DataModel;
import com.webmos.framework.model.ResultEntity;
import com.webmos.framework.service.IDataService;

/**
 * 
* @title: TstzController 
* @description：推送通知控制层
* @author： shijiajun
* @date： 2018年5月11日 
 */
@Controller
@RequestMapping("tstz")
public class TstzController extends AbstractModelController {
	@Autowired
	public HttpSession session;
	@Qualifier("DataServiceImpl")
	@Autowired
	private IDataService dataService;
	@Autowired
	private ITstzService tstzService;
	@Autowired
	private TstzMapper tm;
	public TstzController() {
		super.setModid("875c8723-0252-4378-a15a-b17b6bbeae5b");
	}
	
	/**
	 * 
	* @MethodName: addOneData 
	* @description : 方法重写为了班级总共人数和增加时间
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param page
	* @return Result
	 */
	@Override
	public com.webmos.framework.model.Result addOneData(@RequestParam HashMap<String, String> queryParamFiledMap) {
		String strbj = queryParamFiledMap.get("TZBJ");
		if(null!=strbj && !strbj.equals("")) {
			XsXx ts = new XsXx();
			int zs=0;//推送总数
			int rs=0;//推送班级人数
			String[]  bj =strbj.split(",");
			for(int i=0;i<bj.length;i++) {
				String bjdm = bj[i].toString();
				ts.setBjdm(bjdm);
				rs=tm.queryZgxs(ts);
				zs +=rs;
			}
			String userid = LoginUtil.getUserId(session);
			queryParamFiledMap.put("USERID",userid);
			queryParamFiledMap.put("TSLX","1");
			queryParamFiledMap.put("TSRS",zs+"");
			queryParamFiledMap.put("FBSJ", TimeUtil.getTime());
			//模型数据临时存储类
			DataModel dataModel = new DataModel();
			//将模块的id注入到DataModel类中，后续会持续使用
			dataModel.setMoid(this.getModid());
			//将前端传入的查询条件塞入DataModel的adsearchParam中
			dataModel.setQueryFiledMap(queryParamFiledMap);
			return dataService.addOneData(dataModel);
		}else{
			ResultEntity result =new ResultEntity();
			result.setErrcode("300");
			result.setErrmsg("班级数据为空");
			return result;
		}
	}


	

	@Override
	public com.webmos.framework.model.Result queryListDataCustom(String moId, com.webmos.framework.model.Page page,
			HashMap<String, String> queryParamFiledMap) {
		String userid = LoginUtil.getUserId(session);
		queryParamFiledMap.put("USERID",userid);
		queryParamFiledMap.put("TSLX","1");
		DataModel dataModel = new DataModel();
		//将模块的id注入到DataModel类中，后续会持续使用
		dataModel.setMoid(moId);
		//将前端传入的查询条件塞入DataModel的adsearchParam中
		dataModel.setQueryFiledMap(queryParamFiledMap);
		//调用service类，查询数据
		return dataService.queryListData(page, dataModel,false);
	}

	/**
	 * 
	* @MethodName: queryBj 
	* @description : 查询班级代码
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param page
	* @return Result
	 */
	@RequestMapping("queryBj")
	@ResponseBody
	public Result queryBj(Page page) {
		return tstzService.queryBj(page);
	}
	/**
	 * 
	* @MethodName: queryTsBjRy 
	* @description : 根据wid获取推送新闻信息
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
//	@RequestMapping("queryBjRy")
//	@ResponseBody
//	public Result queryBjRy(TsTz obj) {
//		return tstzService.queryTsBjRy(obj);
//	}
	/**
	 * 
	* @MethodName: queryTsBjRy 
	* @description : 获取班级总数人员
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	@RequestMapping("queryXszs")
	@ResponseBody
	public Result queryXszs(XsXx obj) {
		return tstzService.queryXszs(obj);
	}
	/**
	 * 
	* @MethodName: saveXslsb 
	* @description : 保存临时表进行学生学号，和推送信息wid
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	@RequestMapping("saveXslsb")
	@ResponseBody
	public Result saveXslsb(TsTz tstz,Xslsb obj,XsXx xsob) {
		return tstzService.saveXslsb(tstz, obj, xsob);
	}
	/**
	 * 
	* @MethodName: updateXslsbzt 
	* @description : 修改学生临时表状态
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	@RequestMapping("updateXslsbzt")
	@ResponseBody
	public Result updateXslsbzt(Xslsb obj,@RequestParam Map resmap) {
		//获取本人的ID即学号
		String xsid = com.hrbwmxx.framework.util.TokenToUserId.getUserId(resmap);
		obj.setXh(xsid);
		return tstzService.updateXslsbzt(obj);
	}
	/**
	 * 
	* @MethodName: queryYdrs 
	* @description : 查询临时表越多人数
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	@RequestMapping("queryYdrs")
	@ResponseBody
	public Result queryYdrs(Xslsb obj) {
		return tstzService.queryYdrs(obj);
	}
	/**
	 * 
	* @MethodName: updateTszt 
	* @description : 删除时候修改状态state为2，同时根据登入用户的userid和wid才能删除
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	@RequestMapping("updateTszt")
	@ResponseBody
	public Result updateTszt(TsTz obj) {
		return tstzService.updateTszt(obj);
	}
	/**
	 * 
	* @MethodName: queryYdxs 
	* @description : 查询阅读状态和人数
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	@RequestMapping("queryYdxs")
	@ResponseBody
	public Result queryYdxs(Xslsb obj) {
		return tstzService.queryYdxs(obj);
	}
	/**
	 * 
	* @MethodName: queryWdxs 
	* @description : 查询未读状态和人数
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param obj
	* @return Result
	 */
	@RequestMapping("queryWdxs")
	@ResponseBody
	public Result queryWdxs(Xslsb obj) {
		return tstzService.queryWdxs(obj);
	}
	
	@RequestMapping("queryCs")
	@ResponseBody
	public Map<String, Object> queryCs(Xslsb obj) {
		return tstzService.queryCs(obj);
	}



}
