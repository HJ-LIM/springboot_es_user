package com.simple.juni.security.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.simple.juni.exception.SimpleSearchException;
import com.simple.juni.security.dao.UserDao;
import com.simple.juni.security.domain.bean.User;
import com.simple.juni.security.query.OffsetOption;

@Service
public class UserService {

	final UserDao userDao;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public User loadUser(String userId){
		return userDao.loadUser(userId);
	}

	public List<User> loadAllUsers(int offset, int limit) throws SimpleSearchException {
		return userDao.loadAllUsers(new OffsetOption(offset, limit));
	}
}
