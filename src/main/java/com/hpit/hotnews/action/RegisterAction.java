package com.hpit.hotnews.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hpit.hotnews.entity.User;
import com.hpit.hotnews.service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RegisterAction extends ActionSupport implements ModelDriven<User>{
	private static final long serialVersionUID = 6142697672222794897L;
	private Logger logger = Logger.getLogger(this.getClass());
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	private User user;
	@Autowired
	private UserService userService;
	@Override
	public void validate() {
		logger.info("执行后端验证");
		if(user.getCode().toUpperCase().equals(session.getAttribute("code"))){
			boolean b = userService.checkUser(user.getUsername());
			if(!b){
				return;
			}else{
				addFieldError("registerError","用户名被占用！");
			}
		}else{
			addFieldError("registerError","您输入的验证码不正确！");
		}
		ActionContext context = ServletActionContext.getContext();
		LinkedHashMap<String,ArrayList<String>> value = (LinkedHashMap<String,ArrayList<String>>)context.getValueStack().findValue("fieldErrors");
		ArrayList<String> list = value.get("registerError");
		request.setAttribute("error","registerError#"+list.get(0));
	}
	@Override
	public String execute() throws Exception {
		logger.info("执行注册");
		user.setCreatedate(new Date());
		user.setModifytime(new Date());
		userService.register(user);
		user.setPassword(null);
		session.setAttribute("user",user);
		return Action.SUCCESS;
	}
	@Override
	public User getModel() {
		if(null == user){
			user = new User();
		}
		return user;
	}

}
