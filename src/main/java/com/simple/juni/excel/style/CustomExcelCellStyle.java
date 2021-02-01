package com.simple.juni.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;

import com.simple.juni.exception.InvalidRgbException;

public abstract class CustomExcelCellStyle implements ExcelCellStyle{

	private ExcelCellStyleConfigurer configurer = new ExcelCellStyleConfigurer();

	public CustomExcelCellStyle(ExcelCellStyleConfigurer configurer) throws InvalidRgbException {
		configure(configurer);
	}

	public abstract void configure(ExcelCellStyleConfigurer configurer) throws InvalidRgbException;

	@Override
	public void apply(CellStyle cellStyle) {
		configurer.configure(cellStyle);
	}
}
