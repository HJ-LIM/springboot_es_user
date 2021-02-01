package com.simple.juni.excel;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Workbook;

import com.simple.juni.excel.style.BlueHeaderStyle;
import com.simple.juni.excel.style.ExcelCellStyle;

public class SimpleExcelMetaData {
	public <T> SimpleExcelMetaData(Class<T> type) {
		Field[] fields = type.getDeclaredFields();

		Map<String, Integer> collect = Arrays.stream(fields)
			.filter(field -> field.isAnnotationPresent(ExcelColumn.class))
			.map(field -> {
				ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
				return annotation;
			})
			.collect(Collectors.toMap(ExcelColumn::headerName, ExcelColumn::order, (integer, integer2) ->integer ,  LinkedHashMap::new));
		System.out.println(collect);

	}

	public SimpleExcelMetaData(Map<String, String> headerNamesMap) {

	}

	// public SimpleExcelMetaData createMetaData(Class<T> type, Workbook wb){
		// new  PreCalculatedCellStyleMap();
	// }
}
