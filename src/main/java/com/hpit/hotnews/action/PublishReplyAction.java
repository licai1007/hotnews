package com.hpit.hotnews.action;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hpit.hotnews.entity.Reply;
import com.hpit.hotnews.entity.User;
import com.hpit.hotnews.service.ReplyService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PublishReplyAction extends ActionSupport implements ModelDriven<Reply>{
	private static final long serialVersionUID = 5175647684670419730L;
	private Reply reply;
	private String flag = "news";
	@Autowired
	private ReplyService replyService;
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	@Override
	public String execute() throws Exception {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User)session.getAttribute("user");
		reply.setRauthor(user.getUsername());
		String message = new String(reply.getMsg().getBytes("iso8859-1"),"utf-8");
		reply.setMsg(message);
		reply.setRtime(new Date());
		replyService.publishReply(reply);
		return SUCCESS;
	}
	@Override
	public Reply getModel() {
		if(null == reply){
			reply = new Reply();
		}
		return reply;
	}
	public String getFlag() {
		return flag;
	}
}
