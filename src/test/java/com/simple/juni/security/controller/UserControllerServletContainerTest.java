package com.simple.juni.security.controller;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.simple.juni.security.domain.bean.User;
import com.simple.juni.security.service.UserService;

/**
 * 서블릿 컨테이너 테스트
 * SpringBootTest 어노테이션에 webEnvironment 값을 변경. (MOCK -> RANDOM_PORT)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerServletContainerTest {

	@Autowired
	TestRestTemplate testRestTemplate;

	@MockBean
	UserService userService;

	@Test
	public void hello() throws Exception {
		String result = testRestTemplate.getForObject("/user/hello.json", String.class);
		assertThat(result).isEqualTo("hello");
	}

	@Test
	public void hello2() throws Exception {
		when(userService.loadUser("juni")).thenReturn(new User("juni"));

		String result = testRestTemplate.getForObject("/user/hello.json", String.class);
		assertThat(result).isEqualTo("hello");
	}


}