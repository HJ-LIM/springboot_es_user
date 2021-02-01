package com.simple.juni.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class SimpleExcelFile<T> {

	private static final SpreadsheetVersion supplyExcelVersion = SpreadsheetVersion.EXCEL2007;
	private static final int ROW_START_INDEX = 0;
	private static final int COLUMN_START_INDEX = 0;

	private SXSSFWorkbook workbook;
	private Sheet sheet;
	private SimpleExcelMetaData excelMetaData;

	public SimpleExcelFile(List<T> data, Class<T> type) {
		validateMaxRow(data);
		this.workbook = new SXSSFWorkbook();
		this.excelMetaData = new SimpleExcelMetaData(type);
		// renderExcel(data);
	}

	private void validateMaxRow(List<T> data) {
		int maxRows = supplyExcelVersion.getMaxRows();
		if (data.size() > maxRows){
			throw new IllegalArgumentException(
				String.format("This concrete ExcelFile does not support over %s rows", maxRows));
		}
	}

	private void renderExcel(List<T> data) {
		// Create sheet and render headers
		sheet = workbook.createSheet();
		renderHeadersWithNewSheet(sheet , ROW_START_INDEX , COLUMN_START_INDEX);

		if (data.isEmpty()){
			return;
		}

		// Render Body
		int rowIndex = ROW_START_INDEX + 1;
		for (Object renderedData : data) {
			renderBody(renderedData, rowIndex++, COLUMN_START_INDEX);
		}
	}

	private void renderHeaders(Sheet sheet, int rowIndex, int columnStartIndex){
		Row row = sheet.createRow(rowIndex);
		int columnIndex = columnStartIndex;
	}

	private void renderBody(Object data, int rowIndex, int columnStartIndex) {
		Row row = sheet.createRow(rowIndex);
		int columnIndex = columnStartIndex;
	}

	private void renderHeadersWithNewSheet(Sheet sheet, int rowIndex, int columnStartIndex) {


	}

	private void renderCellValue(Cell cell, Object cellValue){
		if(cellValue instanceof Number){
			Number numberValue = (Number)cellValue;
			cell.setCellValue(numberValue.doubleValue());
			return;
		}
		cell.setCellValue(cellValue == null ? "" : cellValue.toString());
	}

	public void write(OutputStream stream) throws IOException {
		workbook.write(stream);
		workbook.close();
		workbook.dispose();
		stream.close();
	}
}
