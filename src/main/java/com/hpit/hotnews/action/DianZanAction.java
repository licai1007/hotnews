package com.hpit.hotnews.action;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.hpit.hotnews.service.NewsService;
import com.opensymphony.xwork2.ActionSupport;

public class DianZanAction extends ActionSupport{
	private static final long serialVersionUID = -6038081390254466918L;
	private String newsid;
	private String thumbsUp;
	@Autowired
	private NewsService newsService;
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	public void zan(){
		BigDecimal nd = new BigDecimal(newsid);
		BigDecimal tp = new BigDecimal(thumbsUp);
		newsService.dianzan(nd, tp);
	}
	public String getNewsid() {
		return newsid;
	}
	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}
	public String getThumbsUp() {
		return thumbsUp;
	}
	public void setThumbsUp(String thumbsUp) {
		this.thumbsUp = thumbsUp;
	}
	
}
