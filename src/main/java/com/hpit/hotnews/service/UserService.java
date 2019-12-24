package com.hpit.hotnews.service;

import com.hpit.hotnews.entity.User;

public interface UserService {
	User login(User user);
	boolean register(User user);
	boolean checkUser(String username);
	User getUser(String username);
	User getUserByUuid(String uuid);
	void wxLogin(User user);
	boolean bindMail(String mail,String username);
}
