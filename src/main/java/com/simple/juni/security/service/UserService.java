package com.simple.juni.security.service;

import org.springframework.stereotype.Service;

import com.simple.juni.security.dao.UserDao;
import com.simple.juni.security.domain.bean.User;

@Service
public class UserService {

	final UserDao userDao;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public User getUser(String userId) throws Exception {
		return userDao.getUser(userId);
	}
}
