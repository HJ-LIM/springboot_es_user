package com.simple.juni.test.constroctor;

import org.springframework.stereotype.Component;

@Component
public class HelloService {

	public String getHello(){
		return "HelloService.Hello()";
	}
}
