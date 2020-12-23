package com.simple.juni.security.service;

import org.springframework.stereotype.Service;

import com.simple.juni.security.domain.bean.User;

@Service
public class UserService {
	public User getUser(String userId){
		return new User(userId);
	}
}
