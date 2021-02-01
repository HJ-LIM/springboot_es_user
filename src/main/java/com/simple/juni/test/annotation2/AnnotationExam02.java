package com.simple.juni.test.annotation2;

import lombok.Getter;

@Getter
public class AnnotationExam02 {

	@InsertStringData(data = "MHLab")
	private String myData;

	@InsertStringData
	private String defaultData;

	public AnnotationExam02() {
		myData = "No data";
		defaultData = "No data";
	}
}
