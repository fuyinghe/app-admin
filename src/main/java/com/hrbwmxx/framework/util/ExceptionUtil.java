package com.hrbwmxx.framework.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hrbwmxx.hrbu.log.dao.LogMapper;
import com.hrbwmxx.hrbu.log.model.Log;

@Component
public class ExceptionUtil {
	@Autowired
	private LogMapper logMapper;
	
	public void addLog(Log rz) {
		logMapper.addLog(rz);
	}
	public  Log buildRZ(String code,String mess) {
		SimpleDateFormat ymd=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat hms=new SimpleDateFormat("HH-mm-ss");
		Date date=new Date();
		Log r=new Log();
		String rzid=UUID.randomUUID().toString().replaceAll("-", "");
		r.setRzid(rzid);
		r.setCzrq(ymd.format(date));
		r.setCzsj(hms.format(date));
		r.setYcdm(code);
		r.setYcxx(mess);
		return r;
	}
}
