package com.simple.juni.excel;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.simple.juni.exception.NoExcelColumnAnnotationsException;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SimpleExcelMetaDataFactory {

	private static SimpleExcelMetaDataFactory factory = new SimpleExcelMetaDataFactory();
	public static SimpleExcelMetaDataFactory getInstance(){
		return factory;
	}

	public SimpleExcelMetaData createMetaData(Class<T> type) throws NoExcelColumnAnnotationsException {
		Map<String, String> headerNamesMap = new LinkedHashMap<>();

		for (Field field : getAllFields(type)) {
			if (field.isAnnotationPresent(ExcelColumn.class)) {
				ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
				headerNamesMap.put(field.getName() , annotation.headerName());
			}
		}

		if (headerNamesMap.isEmpty()) {
			throw new NoExcelColumnAnnotationsException(String.format("Class %s has not @ExcelColumn at all", type));
		}

		return new SimpleExcelMetaData(headerNamesMap);
	}

	private Field[] getAllFields(Class<T> type) {
		return type.getClass().getDeclaredFields();
	}
}
