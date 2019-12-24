package com.hpit.hotnews.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


public class GetCity {
	public static String getCity(){
		String strCut = null;
		try {
			URL url1 = new URL("http://city.ip138.com/ip2city.asp");
			BufferedReader br = new BufferedReader(new InputStreamReader(url1.openStream()));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			String webContent = "";
			while((s = br.readLine()) != null){
				sb.append(s+"\r\n");
			}
			br.close();
			webContent = sb.toString();
			int start = webContent.indexOf("[")+1;
			int end = webContent.indexOf("]");
			webContent = webContent.substring(start,end);
			
			String urlPath = "http://ip.taobao.com/service/getIpInfo.php?ip="+webContent;
			URL url2 = new URL(urlPath);
			URLConnection connection = url2.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
			StringBuilder json = new StringBuilder();
			String str = null;
			while((str = in.readLine()) != null){
				json.append(str);
			}
			in.close();
			JSONObject jo = JSON.parseObject(json.toString().toString());
			String strCity = jo.getJSONObject("data").get("city").toString();
			strCut = strCity.substring(0,strCity.length()-1);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return strCut;
	}
}
