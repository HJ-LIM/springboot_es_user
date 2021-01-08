package com.simple.juni.security.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.simple.juni.security.service.UserService;

/**
 * Mockup 으로 테스트
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class UserControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	UserService userService;

	/**
	 * Controller Test
	 * @throws Exception
	 */
	@Test
	public void hello() throws Exception {
		mockMvc.perform(get("/user/hello.json"))
			.andExpect(status().isOk())
			.andExpect(content().string("hello"))
			.andDo(print());
	}

	@Test
	public void getUser() throws Exception {
		mockMvc.perform(
			get("/user/getUser.json")
				.param("userId" , "juni"))
			.andExpect(status().isOk())
			.andExpect(content().string("juni"))
			.andDo(print());
	}

	@Test
	public void getUser2() throws Exception {
		System.out.println("User ID : " + userService.loadUser("juni").get_id());
	}
}