package org.sap.era.persistence;

import static javax.persistence.GenerationType.AUTO;
import javax.persistence.*;

@Entity
@Table(name = "T_CELLMODEL")
@NamedQuery(name = "AllCellModelsByTableModel", query = "select cm from CellModel cm where cm.tableModel.id = :tableModelID")
public class CellModel {

	public final static int CELL_HIDDEN = -1;
	public final static int CELL_LABEL = 0;
	public final static int CELL_DATA = 1;
	public final static int CELL_BANK = 2;
	public final static int CELL_Text = 3;
	@Id
	@GeneratedValue(strategy = AUTO)
	private long id;
	@Basic
	private int type;
	@Basic
	private String label;
	@Basic
	private int row;
	@Basic
	private int col;
	@Basic
	private int colspan;
	@Basic
	private int rowspan;
	@ManyToOne
	@JoinColumn(name = "tableModel", nullable = false)
	private TableModel tableModel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}

	public TableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	public String getHtmlLabel() {
		switch (type) {
		case 0:
			return label;
		case 1:
			return "";
		case 2:
			return "--";
		default:
			return label;
		}
	}

	public String getKey() {
		String key = String.valueOf(this.row) + "-" + String.valueOf(this.col);
		return key;
	}

	public void setKey(String key) {

	}

	public boolean isMerged() {

		boolean isMerged = false;
		if (this.colspan > 0 || this.rowspan > 0) {
			isMerged = true;
		}
		return isMerged;
	}

}