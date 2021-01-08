package com.simple.juni.test.bean;

import org.springframework.stereotype.Component;

@Component
public class BeanTest2 {
	public BeanTest2() {
		System.out.println("BeanTest2 생성");
		hello();
	}

	public void hello() {
		System.out.println("BeanTest2.hello");
	}
}
