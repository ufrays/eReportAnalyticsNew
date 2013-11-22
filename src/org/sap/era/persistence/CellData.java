package org.sap.era.persistence;

import javax.persistence.*;

@Entity
@Table(name = "T_CELLDATA")
public class CellData {

	@Id
	private long id;
	@Basic
	private int row;
	@Basic
	private int col;
	@Basic
	private int type;
	@Basic
	private int dataInt;
	@Basic
	private double dataDouble;
	@Basic
	private String dataText;
	@ManyToOne
	@JoinColumn(name="tableData", nullable=false)
	private TableData tableData;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getDataInt() {
		return dataInt;
	}

	public void setDataInt(int dataInt) {
		this.dataInt = dataInt;
	}

	public double getDataDouble() {
		return dataDouble;
	}

	public void setDataDouble(double dataDouble) {
		this.dataDouble = dataDouble;
	}

	public String getDataText() {
		return dataText;
	}

	public void setDataText(String dataText) {
		this.dataText = dataText;
	}

	public TableData getTableData() {
		return tableData;
	}

	public void setTableData(TableData tableData) {
		this.tableData = tableData;
	}

}