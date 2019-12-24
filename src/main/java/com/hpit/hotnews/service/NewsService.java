package com.hpit.hotnews.service;

import java.math.BigDecimal;
import java.util.List;

import com.hpit.hotnews.entity.News;

public interface NewsService {
	public List<News> showNews();
	public News newsDetail(BigDecimal newsid);
	public void dianzan(BigDecimal newsid,BigDecimal thumbsUp);
}
