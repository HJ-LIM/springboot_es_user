package com.simple.juni.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;

public class NoExcelBackgroundColor implements ExcelBackgroundColor{

	@Override
	public void applyBackground(CellStyle cellStyle) {
		// Do nothing
	}
}
