package com.hpit.hotnews.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.hpit.hotnews.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AdminInterceptor implements Interceptor{
	private static final long serialVersionUID = 3880326601124458656L;
	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	@Override
	public void init() {
		System.out.println("init");
	}

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(null != user){
			System.out.println("拦截器中检查到了管理员的登录信息");
			ai.invoke();
		}else{
			System.out.println("拦截器中没有检查到管理员的登录信息，跳转登录");
			request.setAttribute("notAdmin","没有登录信息");
			return "skip";
		}
		
		return null;
	}

}
