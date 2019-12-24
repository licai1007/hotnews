package com.hpit.hotnews.test;

import java.util.List;



import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestHibernate {
	@Autowired
	private SessionFactory factory;
	private Session session;
	private Transaction tx;
	@Before
	public void init(){
		session = factory.getCurrentSession();
		tx = session.beginTransaction();
	}
	@Test
	public void TestSession(){
		SQLQuery query = session.createSQLQuery("select * from tb_user");
		List<Object[]> list = query.list();
		for (Object[] objects : list) {
			System.out.println("用户名："+objects[1]+"，密码："+objects[2]);
		}
	}

}
