package com.simple.juni.security.controller;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.juni.security.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(value = "/hello.json")
	public String hello(){
		return "hello";
	}

	@GetMapping(value = "/getUser.json")
	public void findUser(
		@RequestParam(value="userId",required=true) String userId,
		ModelMap model){

		model.put("result" , userService.getUser(userId));
	}
}
