package com.hrbwmxx.hrbu.jgtstz.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.util.TimeUtil;
import com.hrbwmxx.hrbu.jgtstz.dao.JgtstzMapper;
import com.hrbwmxx.hrbu.jgtstz.service.IJgtstzService;
import com.hrbwmxx.hrbu.jgtstz.vo.BmVo;
import com.hrbwmxx.hrbu.jgtstz.vo.Jgbm;
import com.hrbwmxx.hrbu.jgtstz.vo.Jglsb;
import com.hrbwmxx.hrbu.jgtstz.vo.Jgxx;
import com.hrbwmxx.hrbu.jgtstz.vo.TsTz;
import com.hrbwmxx.hrbu.tstz.vo.Xslsb;
import com.hrbwmxx.system.service.LoginUtil;
import com.webmos.framework.controller.AbstractModelController;
import com.webmos.framework.model.DataModel;
import com.webmos.framework.model.Page;
import com.webmos.framework.model.Result;
import com.webmos.framework.service.IDataService;
/**
 * 
* @title: TstzController 
* @description：教工推送通知控制层
* @author： shijiajun
* @date： 2018年5月11日 
 */
@Controller
@RequestMapping("jgtstz")
public class JgtstzController extends AbstractModelController {
	@Autowired
	public HttpSession session;
	@Autowired
	private IJgtstzService jgtstzService;
	@Qualifier("DataServiceImpl")
	@Autowired
	private IDataService dataService;
	@Autowired
	private JgtstzMapper tm;
	
	public JgtstzController() {
		super.setModid("875c8723-0252-4378-a15a-b17b6bbeae5b");
	}
	
	/**
	 * 
	* @MethodName: addOneData 
	* @description : 方法重写为了教师总共通知人数
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param page
	* @return Result
	 */
	@Override
	public Result addOneData(@RequestParam HashMap<String, String> queryParamFiledMap) {
		String strbm = queryParamFiledMap.get("TZBJ");
		if(null!=strbm && !strbm.equals("")) {
			Jgxx jx = new Jgxx();
			int zs=0;
			int rs=0;
			String[]  bm =strbm.split(",");
			for(int i=0;i<bm.length;i++) {
				String bmdm = bm[i].toString();
				jx.setBmdm(bmdm);
				rs=tm.queryZgjg(jx);
				zs +=rs;
			}
			String userid = LoginUtil.getUserId(session);
			queryParamFiledMap.put("USERID",userid);
			queryParamFiledMap.put("TSLX","2");
			queryParamFiledMap.put("TSRS",zs+"");
			queryParamFiledMap.put("FBSJ", TimeUtil.getTime());
			//模型数据临时存储类
			DataModel dataModel = new DataModel();
			//将模块的id注入到DataModel类中，后续会持续使用
			dataModel.setMoid(this.getModid());
			//将前端传入的查询条件塞入DataModel的adsearchParam中
			dataModel.setQueryFiledMap(queryParamFiledMap);
			return dataService.addOneData(dataModel);
		}else {
			BmVo bv = new BmVo();
			bv.setErrcode("300");
			bv.setMessage("添加班级数据为空");
			return bv;
		}
	}
	
	@Override
	public Result queryListDataCustom(String moId, Page page, HashMap<String, String> queryParamFiledMap) {
		String userid = LoginUtil.getUserId(session);
		queryParamFiledMap.put("USERID",userid);
		queryParamFiledMap.put("TSLX","2");
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
	* @MethodName: queryJgbm 
	* @description : 查询教工代码
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param page
	* @return Result
	 */
	@RequestMapping("queryJgbm")
	@ResponseBody
	public Result queryJgbm() {
		return jgtstzService.queryJgbm();
	}
	/**
	 * 
	* @MethodName: saveJglsb 
	* @description : 推送时，根据部门把教工转存到临时表中
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param page
	* @return Result
	 */
	@RequestMapping("saveJglsb")
	@ResponseBody
	public Result saveJglsb(TsTz tstz) {
		return jgtstzService.saveJglsb(tstz);
	}
	/**
	 * 
	* @MethodName: queryYdjg 
	* @description : 查询出阅读人员和阅读总数
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param page
	* @return Result
	 */
	@RequestMapping("queryYdjg")
	@ResponseBody
	public Result queryYdjg(Jglsb obj) {
		return jgtstzService.queryYdjg(obj);
	}
	/**
	 * 
	* @MethodName: queryWdjg 
	* @description : 查询出未读人员和未读总数
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param page
	* @return Result
	 */
	@RequestMapping("queryWdjg")
	@ResponseBody
	public Result queryWdjg(Jglsb obj) {
		return jgtstzService.queryWdjg(obj);
	}
	/**
	 * 
	* @MethodName: updateTszt 
	* @description : 伪删除
	* @author：shijiajun
	* @date： 2018年5月11日 下午1:10:38
	* @param page
	* @return Result
	 */
	@RequestMapping("updateTszt")
	@ResponseBody
	public Result updateTszt(TsTz obj) {
		return jgtstzService.updateTszt(obj);
	}

}
