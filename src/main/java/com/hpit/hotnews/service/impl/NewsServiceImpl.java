package com.hpit.hotnews.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hpit.hotnews.dao.NewsDao;
import com.hpit.hotnews.entity.News;
import com.hpit.hotnews.service.NewsService;

@Service
@Transactional
public class NewsServiceImpl implements NewsService{
	@Autowired
	private NewsDao newsDao;
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}
	@Override
	public List<News> showNews() {
		return newsDao.getAllNews();
	}
	@Override
	public News newsDetail(BigDecimal newsid) {
		return newsDao.getNewsById(newsid);
	}
	@Override
	public void dianzan(BigDecimal newsid, BigDecimal thumbsUp) {
		newsDao.updateThumbsUp(newsid, thumbsUp);
	}

}
