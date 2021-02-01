package com.simple.juni.excel.style;

import java.awt.*;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

public class BlueCellStyle implements ExcelCellStyle{

	@Override
	public void apply(CellStyle cellStyle) {
		XSSFCellStyle xssfCellStyle = (XSSFCellStyle) cellStyle;
		xssfCellStyle.setFillForegroundColor(new XSSFColor(new Color(223, 235, 246) , new DefaultIndexedColorMap()));
		xssfCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
 	}
}
