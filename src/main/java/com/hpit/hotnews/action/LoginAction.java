package com.hpit.hotnews.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hpit.hotnews.entity.User;
import com.hpit.hotnews.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<User>{
	private static final long serialVersionUID = -7471497066936200668L;
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private UserService userService;
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	private User user;
	@Override
	public void validate() {
		logger.info("执行登录后端验证");
		User userInfo = userService.login(user);
		if(user.getCode().toUpperCase().equals(session.getAttribute("code"))){
			if(null != userInfo){
				userInfo.setPassword(null);
				session.setAttribute("user",userInfo);
				return;
			}else{
				addFieldError("loginError","您输入的用户名或密码错误！");
			}
		}else{
			addFieldError("loginError","您输入的验证码不正确！");
		}
		ActionContext context = ServletActionContext.getContext();
		LinkedHashMap<String,ArrayList<String>> value = (LinkedHashMap<String,ArrayList<String>>)context.getValueStack().findValue("fieldErrors");
		ArrayList<String> list = value.get("loginError");
		request.setAttribute("error","loginError#"+list.get(0));
	}
	@Override
	public String execute() throws Exception {
		logger.info("执行登录");
		return SUCCESS;
	}
	@Override
	public User getModel() {
		if(null == user){
			user = new User();
		}
		return user;
	}
	
}
