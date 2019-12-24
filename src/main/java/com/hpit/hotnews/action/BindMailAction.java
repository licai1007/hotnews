package com.hpit.hotnews.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hpit.hotnews.entity.User;
import com.hpit.hotnews.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class BindMailAction extends ActionSupport{
	private static final long serialVersionUID = -5649366533930745672L;
	private Logger logger = Logger.getLogger(this.getClass());
	private String email;
	@Autowired
	private UserService userService;
	@Override
	public String execute() throws Exception {
		logger.info("执行绑定邮箱");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user.getEmail().equals("无")){
			boolean flag = userService.bindMail(email,user.getUsername());
			if(flag){
				//更新session里用户信息
				User ur = userService.getUser(user.getUsername());
				session.setAttribute("user",ur);
				request.setAttribute("emailMsg","邮箱绑定成功！");
			}else{
				request.setAttribute("emailMsg","邮箱绑定失败！");
			}
		}else{
			request.setAttribute("emailMsg","您的账号以前已绑定邮箱！");
		}
		return SUCCESS;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
