package com.simple.juni.test.constroctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MadExample {

	private final HelloService helloService;

	@Autowired
	public MadExample(HelloService helloService) {
		System.out.println("MadExample.MadExample >> " + helloService.getHello());
		this.helloService = helloService;
	}
}
