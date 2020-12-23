package com.hrbwmxx.system.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.model.ResultEntity;
import com.hrbwmxx.framework.util.MD5;
import com.hrbwmxx.system.service.IYHService;
import com.hrbwmxx.system.service.LoginUtil;


@Controller
@RequestMapping("password")
public class PassWordController {
	
	@Autowired
	public HttpSession session;
	
	@Autowired
	private IYHService yhService;
	
	/**
	 * 
	 * @param queryParamFiledMap
	 * @return
	 */
	@RequestMapping("updatePass")
	@ResponseBody
	public Result updatePass(@RequestParam HashMap<String, String> paramFiledMap) {
		
		//参数
		String userId = LoginUtil.getUserId(session);
		//新密码、旧密码
		String oldPass = paramFiledMap.get("oldPass");
		String newPass = paramFiledMap.get("newPass");
		
		//判断用户是否合法
		if(oldPass!=null && newPass!=null && !"".equals(newPass)) {
			oldPass = MD5.toMD5(oldPass);
			newPass = MD5.toMD5(newPass);
			return yhService.updatePassWord(userId, oldPass, newPass);
		}else {
			Result result = new ResultEntity();
			result.setErrcode("500");
			result.setErrmsg("参数不合法,无法完成密码修改");
			return result;
		}
	}
	
	
	

}
