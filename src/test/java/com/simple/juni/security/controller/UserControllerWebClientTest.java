package com.simple.juni.security.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static reactor.core.publisher.Mono.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;

import com.simple.juni.security.domain.bean.User;
import com.simple.juni.security.service.UserService;

/**
 * WebTestClient 테스트
 *
 * 스프링부트 웹 어플리케이션 테스트 시 웹 클라이언트를 통해서 비동기 형식으로 테스트를 진행.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerWebClientTest {

	@Autowired
	WebTestClient webTestClient;

	@MockBean
	UserService userService;

	@Test
	public void hello() throws Exception {
		when(userService.getUser("juni"))
			.thenReturn(new User("juni"));

		webTestClient.get().uri("/user/hello.json").exchange()
			.expectStatus().isOk()
			.expectBody(String.class).isEqualTo("hello");
	}

}