package com.simple.juni.excel.style;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

import com.simple.juni.exception.InvalidRgbException;

public class RgbExcelBackgroundColor implements ExcelBackgroundColor{

	private static final int MIN_RGB = 0;
	private static final int MAX_RGB = 255;

	private byte red;
	private byte green;
	private byte blue;

	public RgbExcelBackgroundColor(int red, int green, int blue) throws InvalidRgbException {
		if (red < MIN_RGB || red > MAX_RGB
			|| green < MIN_RGB || green > MAX_RGB
			|| blue < MIN_RGB || blue > MAX_RGB) {
			throw new InvalidRgbException(String.format("Wwrong RGB(%s %s %s)" , red , green , blue));
		}

		this.red = (byte)red;
		this.green = (byte)green;
		this.blue = (byte)blue;
	}

	@Override
	public void applyBackground(CellStyle cellStyle) {
		XSSFCellStyle xssfCellStyle = (XSSFCellStyle) cellStyle;
		xssfCellStyle.setFillForegroundColor(new XSSFColor(new byte[]{red, green, blue}, new DefaultIndexedColorMap()));
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	}
}
