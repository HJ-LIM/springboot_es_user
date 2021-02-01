package com.simple.juni.test.annotation;

// @MyAnnotation(name = "someName", value = "Hello World")
public class TheClass {

	@MyAnnotation(name = "doThisMethod", value = "Hello World")
	public void doThis(){}

	@MyAnnotation(name = "doThatMethod")
	public void doThat(){}
}
