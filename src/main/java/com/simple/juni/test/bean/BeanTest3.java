package com.simple.juni.test.bean;

import org.springframework.stereotype.Component;

@Component
public class BeanTest3 {
	public BeanTest3() {
		System.out.println("BeanTest3 생성");
	}

	public void hello() {
		System.out.println("BeanTest3.hello");
	}
}
