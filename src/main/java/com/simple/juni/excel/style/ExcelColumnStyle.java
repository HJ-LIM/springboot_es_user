package com.simple.juni.excel.style;

public @interface ExcelColumnStyle {
	Class<? extends ExcelCellStyle> excelCellStyleClass();
}
