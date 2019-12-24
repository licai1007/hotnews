package com.hpit.hotnews.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hpit.hotnews.dao.CommentDao;
import com.hpit.hotnews.entity.Comment;

@Repository
public class CommentDaoImpl implements CommentDao{
	@Autowired
	private SessionFactory factory;
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	private Session session;
	private Transaction tx;
	@Override
	public List<Comment> getCommentByNewsid(BigDecimal nid) {
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		String sql = "select * from tb_comment where nid=? order by writertime desc";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0,nid);
		List<Object[]> list = query.list();
		List<Comment> comments = new ArrayList<Comment>();
		Comment comment = null;
		for (Object[] objects : list) {
			comment = new Comment((BigDecimal)objects[0],
					(BigDecimal)objects[1],
					(String)objects[2],
					(String)objects[3],
					(BigDecimal)objects[4],
					(Date)objects[5]);
			comments.add(comment);
		}
		tx.commit();
		return comments;
	}
	@Override
	public int insertComment(Comment comment) {
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		String sql = "insert into tb_comment values(seqComment.nextval,?,?,?,?,to_date(?,'yyyy-MM-dd HH24:MI:SS'))";
		SQLQuery query = session.createSQLQuery(sql);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(comment.getWritertime());
		query.setParameter(0,comment.getNid());
		query.setParameter(1,comment.getMessage());
		query.setParameter(2,comment.getWriter());
		query.setParameter(3,comment.getAgree());
		query.setParameter(4,time);
		int i = query.executeUpdate();
		tx.commit();
		return i;
	}
	@Override
	public void updateAgree(BigDecimal commentid, BigDecimal agree) {
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		String sql = "update tb_comment set agree=? where commentid=?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0,agree);
		query.setParameter(1,commentid);
		query.executeUpdate();
		tx.commit();
	}

}
