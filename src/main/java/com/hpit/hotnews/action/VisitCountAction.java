package com.hpit.hotnews.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class VisitCountAction extends ActionSupport{
	private static final long serialVersionUID = 8722687441251199820L;
	
	private int num;
	@Override
	public String execute() throws Exception {
		Map<String, Object> application = ServletActionContext.getContext().getApplication();
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(session.getAttribute("visit") == null){
			Object count = application.get("count");
			if(count == null){
				application.put("count",1);
			}else{
				application.put("count",(int)count+1);
			}
			session.setAttribute("visit",true);
		}
		num = (int)application.get("count");
		return SUCCESS;
	}
	public int getNum() {
		return num;
	}

}
