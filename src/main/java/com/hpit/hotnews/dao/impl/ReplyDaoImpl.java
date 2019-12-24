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

import com.hpit.hotnews.dao.ReplyDao;
import com.hpit.hotnews.entity.Reply;

@Repository
public class ReplyDaoImpl implements ReplyDao{
	@Autowired
	private SessionFactory factory;
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	private Session session;
	private Transaction tx;
	@Override
	public void insertReply(Reply reply) {
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		String sql = "insert into tb_reply values(seqReply.nextval,?,?,?,to_date(?,'yyyy-MM-dd HH24:MI:SS'))";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0,reply.getCid());
		query.setParameter(1,reply.getMsg());
		query.setParameter(2,reply.getRauthor());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(reply.getRtime());
		query.setParameter(3,time);
		query.executeUpdate();
		tx.commit();	
	}
	@Override
	public List<Reply> getReplyByCid(BigDecimal cid) {
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		String sql = "select * from tb_reply where cid=? order by rtime desc";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0,cid);
		List<Object[]> list = query.list();
		List<Reply> replys = new ArrayList<Reply>();
		Reply reply = null;
		for (Object[] objects : list) {
			reply = new Reply((BigDecimal)objects[0],
					(BigDecimal)objects[1],
					(String)objects[2],
					(String)objects[3],
					(Date)objects[4]);
			replys.add(reply);
		}
		tx.commit();
		return replys;
	}

}
