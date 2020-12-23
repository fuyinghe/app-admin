package com.hrbwmxx.hrbu.pushmessage.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hrbwmxx.hrbu.pushmessage.service.IpushService;

@Controller
@RequestMapping("push")
public class PushController {
	
	
	@Qualifier("PushServiceImpl")
	@Autowired
	IpushService pushService;

	
	
	@RequestMapping("binding")
	@ResponseBody
	public String binding(@RequestParam Map resmap) throws IOException {
		String rtstr = pushService.binding(resmap);
		System.out.println("push/binding:"+rtstr);
		return rtstr;
	}
	
	
}
