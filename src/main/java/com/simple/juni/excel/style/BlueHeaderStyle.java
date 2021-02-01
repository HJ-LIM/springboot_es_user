package com.simple.juni.excel.style;

import com.simple.juni.exception.InvalidRgbException;

public class BlueHeaderStyle extends CustomExcelCellStyle{

	public BlueHeaderStyle(ExcelCellStyleConfigurer configurer) throws InvalidRgbException {
		super(configurer);
	}

	@Override
	public void configure(ExcelCellStyleConfigurer configurer) throws InvalidRgbException {
		configurer.backgroundColor(223, 235, 246)
			.allBorders();
	}
}
