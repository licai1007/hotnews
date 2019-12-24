package com.hpit.hotnews.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hpit.hotnews.entity.News;
import com.hpit.hotnews.service.NewsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport{
	private static final long serialVersionUID = -8302378562354022381L;
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private NewsService newsService;
	HttpServletRequest request = ServletActionContext.getRequest();
	public String showIndex(){
		logger.info("显示主页！");
		messagePass();
		List<News> newss = newsService.showNews();
		request.setAttribute("newss",newss);
		String addr = request.getRemoteAddr();
		return Action.SUCCESS;
	}
	public String video(){
		System.out.println("进入视频页");
		messagePass();
		String realPath = ServletActionContext.getServletContext().getRealPath("/video");
		File file = new File(realPath);
		String[] fileName = file.list();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("videos",fileName);
		String videoName = "";
		if(fileName.length>0){
			videoName = fileName[0];
		}
		request.setAttribute("player",videoName);
		return "video";
	}
	private void messagePass() {
		Object obj = request.getAttribute("error");
		if(null!=obj){
			String[] msg = ((String)obj).split("#");
			addFieldError(msg[0],msg[1]);
		}
	}
}
