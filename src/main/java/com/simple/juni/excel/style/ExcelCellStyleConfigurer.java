package com.simple.juni.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;

import com.simple.juni.exception.InvalidRgbException;

public class ExcelCellStyleConfigurer {

	private ExcelBackgroundColor backgroundColor = new NoExcelBackgroundColor();

	public ExcelCellStyleConfigurer backgroundColor(int red , int blue, int green) throws InvalidRgbException {
		this.backgroundColor = new RgbExcelBackgroundColor(red, blue , green);
		return this;
	}

	public void configure(CellStyle cellStyle) {
		backgroundColor.applyBackground(cellStyle);
	}

	public void allBorders() {
	}
}
