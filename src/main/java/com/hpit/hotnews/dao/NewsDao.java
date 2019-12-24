package com.hpit.hotnews.dao;

import java.math.BigDecimal;
import java.util.List;

import com.hpit.hotnews.entity.News;

public interface NewsDao {
	public List<News> getAllNews();
	public News getNewsById(BigDecimal newsid);
	public void updateThumbsUp(BigDecimal newsid,BigDecimal thumbsUp);
}
