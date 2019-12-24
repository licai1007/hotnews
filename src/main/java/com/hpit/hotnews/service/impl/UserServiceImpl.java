package com.hpit.hotnews.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hpit.hotnews.dao.UserDao;
import com.hpit.hotnews.entity.User;
import com.hpit.hotnews.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public User login(User user) {
		return userDao.userSelect(user);
	}
	@Override
	public boolean register(User user) {
		int i = userDao.userInsert(user);
		return i == 1;
	}
	@Override
	public boolean checkUser(String username) {
		BigDecimal i = userDao.userSelectByUname(username);
		return i.equals(BigDecimal.ZERO)?false:true;
	}
	@Override
	public User getUser(String username) {
		return userDao.getUserByUname(username);
	}
	@Override
	public boolean bindMail(String mail, String username) {
		int i = userDao.mailInsert(mail, username);
		return (i == 0)?false:true;
	}
	@Override
	public User getUserByUuid(String uuid) {
		return userDao.getUserByUuid(uuid);
	}
	@Override
	public void wxLogin(User user) {
		String openid = user.getOpenid();
		User ur = userDao.getUserByOpenid(openid);
		if(null == ur){
			userDao.userInsert(user);
		}else{
			userDao.updateUserByOpenid(user);
		}
	}

}
