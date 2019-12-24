package com.hpit.hotnews.action;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.hpit.hotnews.service.CommentService;
import com.opensymphony.xwork2.ActionSupport;

public class AgreeAction extends ActionSupport{
	private static final long serialVersionUID = 10683590120450503L;
	private String commentid;
	private String agree;
	@Autowired
	private CommentService commentService;
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	public void agreeComment(){
		BigDecimal cd = new BigDecimal(commentid);
		BigDecimal ae = new BigDecimal(agree);
		commentService.agree(cd, ae);
	}
	public String getCommentid() {
		return commentid;
	}
	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}
	public String getAgree() {
		return agree;
	}
	public void setAgree(String agree) {
		this.agree = agree;
	}
	
}
