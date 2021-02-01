package com.simple.juni.test.annotation2;

import java.lang.reflect.Field;
import java.util.Optional;

public class AnnotationHandler {
	private <T> T checkAnnotation(T targetObj, Class annotationObj){
		Field[] fields = targetObj.getClass().getDeclaredFields();
		for (Field f : fields) {
			if (annotationObj == InsertIntData.class){
				checkAnnotation4InsertInt(targetObj, f);
			} else if(annotationObj == InsertStringData.class) {
				checkAnnotation4InsertString(targetObj, f);
			}
		}

		return targetObj;
	}

	private <T> T checkAnnotation4InsertString(T targetObj, Field field) {
		InsertStringData annotation = field.getAnnotation(InsertStringData.class);
		if (annotation != null && field.getType() == String.class){
			field.setAccessible(true);	//일반적으로 private로 선언된 변수(필드)의 경우 접근이 불가능하지만, 리플렉트를 통한 접근에 한하여 가능하게끔 해준다.
			try {
				field.set(targetObj, annotation.data());	//해당 변수의 값을 어노테이션의 값으로 치환하게 됩니다.
			} catch (IllegalAccessException e) {
				System.out.println(e.getMessage());
			}
		}

		return targetObj;
	}

	private <T> T checkAnnotation4InsertInt(T targetObj, Field field) {
		InsertIntData annotation = field.getAnnotation(InsertIntData.class);
		if (annotation != null && field.getType() == int.class) {
			field.setAccessible(true);
			try {
				field.set(targetObj, annotation.data());
			} catch (IllegalAccessException e) {
				System.out.println(e.getMessage());
			}
		}
		return targetObj;
	}

	public <T> Optional<T> getInstance(Class targetClass, Class annotationClass){
		Optional optional = Optional.empty();
		Object object;
		try{
			object = targetClass.newInstance();
			object = checkAnnotation(object, annotationClass);
			optional = Optional.of(object);
		} catch (IllegalAccessException | InstantiationException e) {
			System.out.println(e.getMessage());
		}

		return optional;
	}
}
