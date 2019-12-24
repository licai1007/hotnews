package com.hpit.hotnews.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hpit.hotnews.dao.NewsDao;
import com.hpit.hotnews.entity.News;

@Repository
public class NewsDaoImpl implements NewsDao{
	@Autowired
	private SessionFactory factory;
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	private Session session;
	private Transaction tx;
	@Override
	public List<News> getAllNews() {
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		String sql = "select * from tb_news";
		SQLQuery query = session.createSQLQuery(sql);
		List<Object[]> list = query.list();
		List<News> newss = new ArrayList<News>();
		News news = null;
		for (Object[] objects : list) {
			news = new News((BigDecimal)objects[0],
					(String)objects[1],
					(String)objects[2],
					(String)objects[3],
					(String)objects[4],
					(BigDecimal)objects[5],
					(BigDecimal)objects[6],
					(Date)objects[7],
					(String)objects[8],
					(String)objects[9]);
			newss.add(news);
		}
		tx.commit();
		return newss;
	}
	@Override
	public News getNewsById(BigDecimal newsid) {
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		String sql = "select * from tb_news where newsid=?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0,newsid);
		List<Object[]> list = query.list();
		News news = null;
		for (Object[] objects : list) {
			news = new News((BigDecimal)objects[0],
					(String)objects[1],
					(String)objects[2],
					(String)objects[3],
					(String)objects[4],
					(BigDecimal)objects[5],
					(BigDecimal)objects[6],
					(Date)objects[7],
					(String)objects[8],
					(String)objects[9]);
		}
		tx.commit();
		return news;
	}
	@Override
	public void updateThumbsUp(BigDecimal newsid, BigDecimal thumbsUp) {
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		String sql="update tb_news set thumbsUp=? where newsid=?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0,thumbsUp);
		query.setParameter(1,newsid);
		query.executeUpdate();
		tx.commit();
	}

}
