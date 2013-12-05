package org.sap.era.service.excel;

import java.util.HashMap;
import java.util.Map;

import org.sap.era.persistence.CellModel;

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

	private Map<String, Control> cells = new HashMap<String, Control>();

	public void addCell(int row, int col, Control control) {

		cells.put(String.valueOf(row).concat("-").concat(String.valueOf(col)), control);
	}

	public static ExcelForm DuplicateDataCellByRow(int duplicateRow, ExcelForm excelForm) {
		Map<String, Control> map = excelForm.getCells();
		System.out.println("a:");
		for (Map.Entry<String, Control> entry : map.entrySet()) {
			String key = entry.getKey();
			Control cell = entry.getValue();
			System.out.println("b:" + key);
			String rc[] = key.split("-");
			int row = Integer.parseInt(rc[0]);
			int col = Integer.parseInt(rc[1]);
			if (cell.getCellType() == CellModel.CELL_TYPE_DATA) {
				for (int i = 0; i < duplicateRow; i++) {
					System.out.println("c" + i);
					Control newCell = new LabelControl();
					newCell = cell;
					excelForm.addCell(row + i + 1, col, newCell);
				}
			}
			// map.remove(entry.getKey());
			System.out.println("d");
			// ...
		}
		excelForm.setRows(excelForm.getRows() + duplicateRow);
		return excelForm;
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

	public Map getCells() {
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
