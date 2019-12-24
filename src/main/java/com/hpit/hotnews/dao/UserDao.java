package com.hpit.hotnews.dao;


import java.math.BigDecimal;


import com.hpit.hotnews.entity.User;

public interface UserDao {
	User userSelect(User user);
	int userInsert(User user);
	BigDecimal userSelectByUname(String username);
	User getUserByUname(String uname);
	User getUserByUuid(String uuid);
	User getUserByOpenid(String openid);
	int updateUserByOpenid(User user);
	int mailInsert(String mail,String username);
}
