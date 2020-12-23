package com.hrbwmxx.system.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrbwmxx.framework.model.Result;
import com.hrbwmxx.framework.util.EhcacheUtil;
import com.hrbwmxx.framework.util.ExceptionUtil;
import com.hrbwmxx.system.dao.LoginMapper;
import com.hrbwmxx.system.model.Login;
import com.hrbwmxx.system.vo.LoginVo;
@Service("LoginServiceImpl")
public class LoginServiceImpl implements ILoginService {
	
	@Autowired
	private LoginMapper loginMapper;
	@Autowired
	private HttpSession session;
	@Autowired
    private ExceptionUtil exceptionUtil;
	/**
	 * 登录认证
	 */
	public Result login(HttpServletRequest request,Login login) {
		LoginVo result = new LoginVo();
		Login loginUser = new Login();
		
		//监测认证参数是否完整
		if(login.getUserName()==null ||login.getUserPassword()==null){
			result.setErrcode("501");
			result.setErrmsg("请输入账号密码");
			return result;
		}
		
		//将明文密码加密下一步使用
		login.setUserPassword(com.hrbwmxx.framework.util.MD5.toMD5(login.getUserPassword()));
		//校验用户名与密码
		loginUser = loginMapper.loginCheck(login);
		
		if(loginUser==null){
			//登录失败
			result.setErrcode("504");
			result.setErrmsg("用户名或密码错误");
			return result;
		}else{
			//登录成功
			String token = createAccessToken(loginUser.getUserId());
			if(token==null){
				result.setErrcode("505");
				result.setErrmsg("生成accesstoken失败");
				return result;
			}else{
				//登录成功返回
				loginUser.setAccessToken(token);
				result.setObj(loginUser);
				//将登录信息存入session
				request.getSession().setAttribute("LoginUserSession", loginUser);
				//将登录信息放入Ehcache
				//EhcacheUtil.getInstance().put("LoginUser", token, loginUser);
			}
			
		}
		return result;
	}
	
	
	public boolean loginWx(String openId) {
		Login loginUser = new Login();

		//校验用户名与密码
		loginUser = loginMapper.loginCheck_sso(openId);	
		
		if(loginUser==null){
			//登录失败
			return false;
		}else{
			//登录成功
			String token = createAccessToken(loginUser.getUserId());
			if(token==null){
				return false;
			}else{
				//登录成功返回
				loginUser.setAccessToken(token);
				//将登录信息存入session
				session.setAttribute("LoginUserSession", loginUser);
				//将登录信息放入Ehcache
				//EhcacheUtil.getInstance().put("LoginUser", token, loginUser);
				return true;
			}
			
		}
	}
	
	/**
	 * 检查登录状态
	 */
	public Result loginCheck(HttpServletRequest request,HttpServletResponse response) {
		LoginVo result = new LoginVo();
		Login sessionLogin = (Login) request.getSession().getAttribute("LoginUserSession");
		if(sessionLogin==null) {
			result.setErrcode("403");
			result.setErrmsg("系统监测到用户未登录");
			return  result;
		}else {
			result.setCode("0");
			result.setObj(sessionLogin);
		}
		return result;
	}

	/**
	 * 生成token
	 * 如果存在直接返回，如果不存在新建token
	 * @param login
	 * @return
	 */
	private String createAccessToken(String userId){
		//查询token是否存在，如果存在直接返回，如果不存在新建token
		//问题是这种方式不能确保用户唯一登录，同一个用户在不同的端进行登录，获取到的token却是相同的
		String oldToken = loginMapper.queryUserToken(userId);
		if(oldToken==null){
			String accessToken = java.util.UUID.randomUUID().toString();
			int insertStatus = loginMapper.insertToken(userId, accessToken, CurrentYMDHSMTime());
			if(insertStatus==1){
				return accessToken;
			}else{
				return null;
			}
		}else{
			return oldToken;
		}
		
	}
	/**
	 * 根据token获取USERID
	 * 如果存在直接返回，如果不存在新建token
	 * @param login
	 * @return
	 */
	private String queryUserId(String accessToken){
		return loginMapper.queryUserId(accessToken);
	}

	/**
	 * 获取token对应的userId
	 */
	public Result queryUserId(Login login) {
		LoginVo result = new LoginVo();
		Login loginUser = new Login();
		
		//监测认证参数是否完整
		if(login.getAccessToken()==null || login.getSignature()==null){
			result.setErrcode("506");
			result.setErrmsg("参数不全无法完成认证");
			return result;
		}
		
		String userId = queryUserId(login.getAccessToken());
		if(userId==null){
			result.setErrcode("509");
			result.setErrmsg("用户尚未登录或token过期");
			return result;
		}else{
			loginUser.setUserId(userId);
			loginUser.setAccessToken(login.getAccessToken());
			result.setObj(loginUser);
			return result;
		}
		
	}
	
	/**
	 * 创建时间
	 * @return
	 */
	public static final String CurrentYMDHSMTime(){
		String curTime="";
		//格式化时间开始
		SimpleDateFormat formatter;
		java.util.Date currentDate=new java.util.Date();
		formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		currentDate=Calendar.getInstance().getTime();
		//格式化时间结束
		curTime= formatter.format(currentDate);
		return curTime;
	}

	/**
	 * 退出系统
	 */
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		Login sessionLogin = (Login) request.getSession().getAttribute("LoginUserSession");
		if(sessionLogin!=null) {
			loginMapper.removeToken(sessionLogin.getAccessToken());
		}
		request.getSession().invalidate();
		return "redirect:/login.html";
	}

}
