package com.hpit.hotnews.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import com.hpit.hotnews.entity.JsdkParam;
import net.sf.json.JSONObject;
public class JsdkCommonUtil {
	/**
	 * 返回权限验证信息
	 * @param url
	 * @return
	 */
	 public JsdkParam ReturnJSDKapi(String url){
		 String xml = HttpXmlClient.get("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ returnAccesstoken() +"&type=jsapi");
		 
			JSONObject jsonMap  = JSONObject.fromObject(xml);
			Map<String, String> map = new HashMap<String, String>();
	        Iterator<String> it = jsonMap.keys();
	        while(it.hasNext()) {  
	            String key = (String) it.next();  
	            String u = jsonMap.get(key).toString();
	            map.put(key, u);  
	        }
	        String jsapi_ticket = map.get("ticket");

	        //获取签名signature
	        String noncestr = UUID.randomUUID().toString();
	        String timestamp = Long.toString(System.currentTimeMillis() / 1000);

	        String str = "jsapi_ticket=" + jsapi_ticket +
	                "&noncestr=" + noncestr +
	                "&timestamp=" + timestamp +
	                "&url=" + url;
	        //sha1加密
	        String signature = HttpXmlClient.SHA1(str);
	        JsdkParam jsdkParam=new JsdkParam();
	        jsdkParam.setAppId(Constants.APPID);
	        jsdkParam.setNonceStr(noncestr);
	        jsdkParam.setSignature(signature);
	        jsdkParam.setTimestamp(timestamp);
	        return jsdkParam;
 
	 }
	 
	 /**
	  * 返回微信的accesstoken
	  * @return
	  */
	 public String returnAccesstoken() {
        StringBuilder stringBuilder=new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=");
        stringBuilder.append(Constants.APPID);
        stringBuilder.append("&secret=");
        stringBuilder.append(Constants.APPSECRET);
        String xml = HttpXmlClient.get(stringBuilder.toString());
        JSONObject jsonMap  = JSONObject.fromObject(xml);
        Map<String, String> map = new HashMap<String, String>();
        Iterator<String> it = jsonMap.keys();
        while(it.hasNext()) {  
            String key = (String) it.next();  
            String u = jsonMap.get(key).toString();
            map.put(key, u);  
        }
        //获取access_token
        String access_token = map.get("access_token");
       return access_token;
		
	 }
}

