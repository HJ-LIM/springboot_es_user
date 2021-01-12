package com.simple.juni.security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.simple.juni.security.dao.UserDao;
import com.simple.juni.security.domain.bean.User;

@Service
public class UserService {

	final UserDao userDao;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public User loadUser(String userId) throws Exception {
		return userDao.loadUser(userId);
	}

	public List<User> loadAllUsers() throws Exception {
		return userDao.loadAllUsers();
	}
}
