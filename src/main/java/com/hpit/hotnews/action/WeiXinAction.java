package com.hpit.hotnews.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.hpit.hotnews.common.Constants;
import com.hpit.hotnews.common.JsdkCommonUtil;
import com.hpit.hotnews.entity.JsdkParam;
import com.hpit.hotnews.entity.User;
import com.hpit.hotnews.service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class WeiXinAction extends ActionSupport {
	private static final long serialVersionUID = -6078914523845902618L;
	private String uuid = "";
	private String position = "";
	private String isLogin;
	private String url;
	private JsdkParam info;
	@Autowired
	private UserService userService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();

	public String webWXSaoma(){
		System.out.println("position:"+position);
		String uuid = UUID.randomUUID().toString();
		String url = Constants.getCodeURL(uuid,position);
		System.out.println("url:"+url);
		request.setAttribute("codeurl",url);
		request.setAttribute("uuid",uuid);
		request.setAttribute("position",position);
		return SUCCESS;
	}
	public String webWXLogin(){
		System.out.println("哈哈，回调了!!!!");
		//position = request.getParameter("position");
		//System.out.println("position:" + position);
		if(session.getAttribute("user")==null){
			String code = request.getParameter("code");
			String uuid = request.getParameter("state");
			System.out.println("code:" + code);
			System.out.println("uuid:" + uuid);
			String tokenURL = Constants.getTokenURL(code);
			try {
				URL url = new URL(tokenURL);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");
				if(conn.getResponseCode()==200){
					InputStream is = conn.getInputStream();
					BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
					String line = buffer.readLine();
					JSONObject jo = new JSONObject(line);
					String token = jo.optString("access_token");
					String openId = jo.optString("openid");
					System.out.println("token:" + token);
					System.out.println("openId:" + openId);
					String urlPath = Constants.getUserURL(token,openId);
					URL userUrl = new URL(urlPath);
					HttpURLConnection connection = (HttpURLConnection)userUrl.openConnection();
					connection.setRequestMethod("GET");
					if(connection.getResponseCode()==200){
						InputStream in = connection.getInputStream();
						BufferedReader br = new BufferedReader(new InputStreamReader(in));
						String userStr = br.readLine();
						System.out.println("userStr:"+userStr);
						User user = JSON.parseObject(userStr,User.class);
						user.setUsername("微信用户");
						user.setUuid(uuid);
						user.setCreatedate(new Date());
						user.setModifytime(new Date());
						userService.wxLogin(user);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	/**
	 * 传递url调用微信的jsdk
	 */
	public String selectUserLocation() {
		JsdkCommonUtil jsdkCommonUtil=new JsdkCommonUtil();
		JsdkParam jsdkParam = jsdkCommonUtil.ReturnJSDKapi(url);
		jsdkParam.setData("扫码成功");
		info = jsdkParam;
		return SUCCESS;
	}
	public String webWXCallback(){
		System.out.println("我想返回网站页面。。。。。。。。。");
		System.out.println("uuid:"+uuid);
		User wxUser = userService.getUserByUuid(uuid);
		if(null==wxUser){
			isLogin = "false";
		}else{
			wxUser.setPassword(null);
			session.setAttribute("user",wxUser);
			isLogin = "true";
		}
		return Action.SUCCESS;
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getIsLogin() {
		return isLogin;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public JsdkParam getInfo() {
		return info;
	}
	
}
