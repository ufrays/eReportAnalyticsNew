package org.sap.era.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.TemporalType.DATE;
import org.sap.era.persistence.CellModel;
import java.util.Collection;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "T_TABLEMODEL")
@NamedQuery(name = "AllTableModelsByTableGroupModel", query = "select tm from TableModel tm where tm.tableGroupModel.id = :tableGroupModelID")
public class TableModel {

	@Id
	@GeneratedValue(strategy = AUTO)
	private long id;
	@Basic
	private String groupName;
	@Basic
	private String title;
	@Basic
	private String description;
	@Temporal(DATE)
	private Date createdOn;
	@Basic
	private long CreatedBy;
	@Basic
	private int row;
	@Basic
	private int col;
	@Basic
	private int startRow;
	@Basic
	private int startCol;
	@Basic
	private int sheetID;
	@Basic
	private String sheetName;
	@Basic
	private String modelPath;
	@Basic
	private String hasEnabled;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tableModel")
	private List<CellModel> cellModel;
	@ManyToOne
	@JoinColumn(name = "tableGroupModel", nullable = false)
	private TableGroupModel tableGroupModel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public long getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(long createdBy) {
		CreatedBy = createdBy;
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

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getSheetID() {
		return sheetID;
	}

	public void setSheetID(int sheetID) {
		this.sheetID = sheetID;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getModelPath() {
		return modelPath;
	}

	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}

	public String getHasEnabled() {
		return hasEnabled;
	}

	public void setHasEnabled(String hasEnabled) {
		this.hasEnabled = hasEnabled;
	}

	public List<CellModel> getCellModel() {
		return cellModel;
	}

	public void setCellModel(List<CellModel> cellModel) {
		this.cellModel = cellModel;
	}

	public TableGroupModel getTableGroupModel() {
		return tableGroupModel;
	}

	public void setTableGroupModel(TableGroupModel tableGroupModel) {
		this.tableGroupModel = tableGroupModel;
	}

	public int getStartCol() {
		return startCol;
	}

	public void setStartCol(int startCol) {
		this.startCol = startCol;
	}

}