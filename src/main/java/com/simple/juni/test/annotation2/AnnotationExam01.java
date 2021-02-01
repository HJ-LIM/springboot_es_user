package com.simple.juni.test.annotation2;

import lombok.Getter;

@Getter
public class AnnotationExam01 {

	@InsertIntData(data = 30)
	private int myAge;

	@InsertIntData
	private int defaultAge;

	private String name;

	public AnnotationExam01() {
		this.myAge = -1;
		this.defaultAge = -1;
	}
}
