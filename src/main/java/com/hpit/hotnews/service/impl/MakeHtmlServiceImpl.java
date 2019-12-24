package com.hpit.hotnews.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hpit.hotnews.common.FreemarkerUtil;
import com.hpit.hotnews.dao.CommentDao;
import com.hpit.hotnews.dao.NewsDao;
import com.hpit.hotnews.dao.ReplyDao;
import com.hpit.hotnews.entity.Comment;
import com.hpit.hotnews.entity.News;
import com.hpit.hotnews.entity.Reply;
import com.hpit.hotnews.service.MakeHtmlService;

@Service
@Transactional
public class MakeHtmlServiceImpl implements MakeHtmlService{
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private FreemarkerUtil freemarkerUtil;
	public void setFreemarkerUtil(FreemarkerUtil freemarkerUtil) {
		this.freemarkerUtil = freemarkerUtil;
	}
	@Autowired
	private NewsDao newsDao;
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private ReplyDao replyDao;
	@Override
	public void generate() {
		logger.info("生成新闻静态页！");
		List<News> newss = newsDao.getAllNews();
		for (News news : newss) {
			List<Comment> comments = commentDao.getCommentByNewsid(news.getNewsid());
			for (Comment comment : comments) {
				List<Reply> list = replyDao.getReplyByCid(comment.getCommentid());
				comment.setReplys(list);
			}
			Map<String,Object> root = new HashMap<String,Object>();
			root.put("news",news);
			root.put("comments",comments);
			freemarkerUtil.toHTML(root);
		}

	}

}
