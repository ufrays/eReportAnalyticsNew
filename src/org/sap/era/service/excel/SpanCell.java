package org.sap.era.service.excel;

import org.apache.poi.ss.usermodel.Cell;

public class SpanCell {

	private Cell cell;

	private int firstrow;

	private int lastrow;

	private int firstcol;

	private int lastcol;

	public int getFirstrow() {
		return firstrow;
	}

	public void setFirstrow(int firstrow) {
		this.firstrow = firstrow;
	}

	public int getLastrow() {
		return lastrow;
	}

	public void setLastrow(int lastrow) {
		this.lastrow = lastrow;
	}

	public int getFirstcol() {
		return firstcol;
	}

	public void setFirstcol(int firstcol) {
		this.firstcol = firstcol;
	}

	public int getLastcol() {
		return lastcol;
	}

	public void setLastcol(int lastcol) {
		this.lastcol = lastcol;
	}

	private int rowspan;

	private int colspan;

	public SpanCell() {
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}
}
