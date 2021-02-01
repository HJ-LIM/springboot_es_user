package com.simple.juni.test.bean;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanTest1 {

	final BeanTest3 beanTest3;

	public BeanTest1(BeanTest3 beanTest3) {
		System.out.println("BeanTest1 생성");
		this.beanTest3 = beanTest3;
	}

	@PostConstruct
	public void hello() {
		beanTest3.hello();
		System.out.println("BeanTest1.hello");
	}
}
