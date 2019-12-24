package com.hpit.hotnews.action;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hpit.hotnews.entity.Comment;
import com.hpit.hotnews.entity.User;
import com.hpit.hotnews.service.CommentService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class PublishCommentAction extends ActionSupport implements ModelDriven<Comment>{
	private static final long serialVersionUID = 270688901099174931L;
	private Comment comment;
	private String flag = "news";
	@Autowired
	private CommentService commentService;
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String msg = new String(comment.getMessage().getBytes("iso8859-1"),"utf-8");
		comment.setMessage(msg);
		comment.setWriter(user.getUsername());
		comment.setAgree(new BigDecimal(0));
		comment.setWritertime(new Date(System.currentTimeMillis()));
		boolean flag = commentService.publishComment(comment);
		if(flag){
			request.setAttribute("commentMsg","评论发表成功！");
		}else{
			request.setAttribute("commentMsg","评论发表失败！");
		}
		return SUCCESS;
	}
	@Override
	public Comment getModel() {
		if(null == comment){
			comment = new Comment();
		}
		return comment;
	}
	public String getFlag() {
		return flag;
	}
}
