package com.simple.juni.test.bean;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanTest1 {

	@Autowired
	BeanTest3 beanTest3;

	public BeanTest1() {
		System.out.println("BeanTest1 생성");
	}

	@PostConstruct
	public void hello() {
		beanTest3.hello();
		System.out.println("BeanTest1.hello");
	}
}
