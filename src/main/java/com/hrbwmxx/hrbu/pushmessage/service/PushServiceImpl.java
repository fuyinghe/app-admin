package com.hrbwmxx.hrbu.pushmessage.service;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.gexin.rp.sdk.base.IAliasResult;
import com.gexin.rp.sdk.http.IGtPush;
import com.webmos.framework.util.PropertiesUtil;
@Service("PushServiceImpl")
public class PushServiceImpl implements IpushService {

	
	public String binding(Map resmap) throws IOException {
		String cid = resmap.get("cid")+"";
		String token = resmap.get("token")+"";
		
		if(token==null || "".equals(token) || cid==null || "".equals(cid)) {
			return "参数不完整";
		}
		String userId = com.hrbwmxx.framework.util.TokenToUserId.getUserId(resmap);
		if(userId==null || "".equals(userId)) {
			return "token已过期";
		}else {
			Properties properties = PropertiesUtil.getKey("config/getui.properties");
			String appId = properties.getProperty("appId");
			String appkey = properties.getProperty("appkey");
			String mastersecret = properties.getProperty("mastersecret");
			String host = properties.getProperty("host");
			String CID = cid;
			String Alias = userId;
			System.out.println(appId);
			System.out.println(appkey);
			System.out.println(mastersecret);
			System.out.println(host);
			System.out.println(CID);
			System.out.println(Alias);	
					
			IGtPush push = new IGtPush(host, appkey, mastersecret);
			IAliasResult bindSCid = push.bindAlias(appId, Alias, CID);
			System.out.println("绑定结果：" + bindSCid.getResult() + "错误码:" + bindSCid.getErrorMsg());
			if(bindSCid!=null && bindSCid.getResult()) {
				return "binding ok";
			}else {
				return "binding error"+bindSCid.getErrorMsg();
			}
		}
	}

}
