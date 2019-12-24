package com.hpit.hotnews.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class OperateOracle {
	Connection connection;
	Statement statement;
	ResultSet result;
	@Test
	public void testSelect(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:news","system","87chu14zhuang");
			statement = connection.createStatement();
			result = statement.executeQuery("select * from tb_user");
			result.next();
			System.out.println("用户名："+result.getString("username")+"，密码："+result.getString("password"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				result.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
