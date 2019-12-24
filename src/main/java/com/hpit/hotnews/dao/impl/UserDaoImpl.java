package com.hpit.hotnews.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hpit.hotnews.common.AESUtil;
import com.hpit.hotnews.common.Constants;
import com.hpit.hotnews.dao.UserDao;
import com.hpit.hotnews.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory factory;
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	private Session session;
	private Transaction tx;			
	@Override
	public User userSelect(User user) {
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		String sql = "select * from tb_user where username=? and \"password\"=?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0,user.getUsername());
		byte[] pwd = AESUtil.encrypt(user.getPassword(),Constants.AES_PWD);
		query.setParameter(1,AESUtil.parseByte2HexStr(pwd));
		List<Object[]> list = query.list();
		User ur = null;
		for (Object[] objects : list) {
			ur = new User((BigDecimal)objects[0],(String)objects[1],(String)objects[2],(String)objects[3]);
		}
		tx.commit();
		return ur;
	}
	@Override
	public int userInsert(User user) {
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		String sql = "insert into tb_user values(seq1.nextval,?,?,?,?,?,?,?,?,?,to_date(?,'yyyy-MM-dd HH24:MI:SS'),to_date(?,'yyyy-MM-dd HH24:MI:SS'))";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0,user.getUsername());
		byte[] pwd = AESUtil.encrypt(user.getPassword(),Constants.AES_PWD);
		query.setParameter(1,AESUtil.parseByte2HexStr(pwd));
		query.setParameter(2,"无");
		query.setParameter(3,user.getOpenid());
		query.setParameter(4,user.getNickname());
		query.setParameter(5,user.getSex());
		query.setParameter(6,user.getCity());
		query.setParameter(7,user.getHeadimgurl());
		query.setParameter(8,user.getUuid());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(user.getCreatedate());
		String modifytime = sdf.format(user.getModifytime());
		query.setParameter(9,time);
		query.setParameter(10,modifytime);
		int i = query.executeUpdate();
		tx.commit();
		return i;
	}
	@Override
	public BigDecimal userSelectByUname(String username) {
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		String sql = "select count(1) from tb_user where username = ?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0, username);
		List<Object> list = query.list();
		BigDecimal i = null;
		for (Object object : list) {
			i = (BigDecimal)object;
		}
		tx.commit();
		return i;
	}
	@Override
	public User getUserByUname(String uname) {
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		String sql = "select * from tb_user where username = ?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0,uname);
		List<Object[]> list = query.list();
		User user = null;
		for (Object[] objects : list) {
			user = new User((BigDecimal)objects[0],(String)objects[1],(String)objects[2],(String)objects[3]);
		}
		tx.commit();
		return user;
	}
	@Override
	public int mailInsert(String mail,String username) {
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		String sql = "update tb_user set email=? where username=?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0, mail);
		query.setParameter(1,username);
		int i = query.executeUpdate();
		tx.commit();
		return i;
	}
	@Override
	public User getUserByUuid(String uuid) {
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		String sql = "select * from tb_user where uuid = ?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0,uuid);
		List<Object[]> list = query.list();
		User user = null;
		for (Object[] objects : list) {
			user = new User((BigDecimal)objects[0],(String)objects[1],(String)objects[2],(String)objects[3],
					(String)objects[4],(String)objects[5],(String)objects[6],(String)objects[7],
					(String)objects[8],(String)objects[9],(Date)objects[10]);
		}
		tx.commit();
		return user;
	}
	@Override
	public User getUserByOpenid(String openid) {
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		String sql = "select * from tb_user where openid = ?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0,openid);
		List<Object[]> list = query.list();
		User user = null;
		for (Object[] objects : list) {
			user = new User((BigDecimal)objects[0],(String)objects[1],(String)objects[2],(String)objects[3],
					(String)objects[4],(String)objects[5],(String)objects[6],(String)objects[7],
					(String)objects[8],(String)objects[9],(Date)objects[10]);
		}
		tx.commit();
		return user;
	}
	@Override
	public int updateUserByOpenid(User user) {
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
		String sql = "update tb_user set nickname=?,city=?,headimgurl=?,uuid=?,modifytime=to_date(?,'yyyy-MM-dd HH24:MI:SS') where openid=?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0,user.getNickname());
		query.setParameter(1,user.getCity());
		query.setParameter(2,user.getHeadimgurl());
		query.setParameter(3,user.getUuid());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(user.getModifytime());
		query.setParameter(4,time);
		query.setParameter(5,user.getOpenid());
		int update = query.executeUpdate();
		tx.commit();
		return update;
	}

}
