package org.sap.era.service.excel;

import java.util.HashMap;
import java.util.Map;

import org.sap.era.service.excel.Control;

public class ExcelForm {

	private int rows;
	private int cols;
	private int startRow;
	private int startCol;
	private String SheetName;
	private int sheetID;
	private long tableModelID;
	
	 public int getStartCol() {
		return startCol;
	}

	public void setStartCol(int startCol) {
		this.startCol = startCol;
	}

	private Map<String,Control> cells = new HashMap<String,Control>();
	 
	 public void addCell(int row,int col,Control control){
		 
		 cells.put(String.valueOf(row).concat("-").concat(String.valueOf(col)), control); 
	 }

	public int getCols() {
		return cols;
	}

	public void setCols(int cols) {
		this.cols = cols;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public Map getCells(){
		return cells;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public void setCells(Map<String, Control> cells) {
		this.cells = cells;
	}

	public String getSheetName() {
		return SheetName;
	}

	public void setSheetName(String sheetName) {
		SheetName = sheetName;
	}

	public int getSheetID() {
		return sheetID;
	}

	public void setSheetID(int sheetID) {
		this.sheetID = sheetID;
	}

	public long getTableModelID() {
		return tableModelID;
	}

	public void setTableModelID(long tableModelID) {
		this.tableModelID = tableModelID;
	}
	
}
