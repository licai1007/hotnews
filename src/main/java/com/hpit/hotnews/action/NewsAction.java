package com.hpit.hotnews.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hpit.hotnews.entity.Comment;
import com.hpit.hotnews.entity.News;
import com.hpit.hotnews.entity.Reply;
import com.hpit.hotnews.service.CommentService;
import com.hpit.hotnews.service.NewsService;
import com.hpit.hotnews.service.ReplyService;
import com.opensymphony.xwork2.Action;

public class NewsAction implements Action{
	private Logger logger = Logger.getLogger(this.getClass());
	private String nid;
	@Autowired
	private NewsService newsService;
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	@Autowired
	private CommentService commentService;
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
	@Autowired
	private ReplyService replyService;
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	@Override
	public String execute() throws Exception {
		logger.info("进入新闻详情页！");
		HttpServletRequest request = ServletActionContext.getRequest();
		BigDecimal newsid = new BigDecimal(nid);
		News news = newsService.newsDetail(newsid);
		List<Comment> comments = commentService.showComment(newsid);
		for (Comment comment : comments) {
			List<Reply> list = replyService.getReply(comment.getCommentid());
			comment.setReplys(list);
		}
		request.setAttribute("news",news);
		request.setAttribute("comments",comments);
		return SUCCESS;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	
	
}
