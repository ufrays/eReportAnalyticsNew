package org.sap.era.persistence;

import static javax.persistence.TemporalType.DATE;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "T_TABLEDATA")
public class TableData {

	@Id
	private long id;
	@Basic
	@Temporal(DATE)
	private Date createAt;
	@Basic
	private Integer createBy;
	@Basic
	private String orgnizationID;
	@Basic
	private Integer durationID;
	@Basic
	private Integer status;
	@Basic
	private Integer reportMode;
	@Basic
	private long TableModelID;
	@Basic
	private String uploadedDocmentID;
	@Basic
	private Integer reportOrgnizationID;
	@OneToMany(mappedBy = "tableData")
	private List<CellData> cellData;
	@ManyToOne
	private TableModel tableModel;

	public List<CellData> getCellData() {
		return cellData;
	}

	public void setCellData(List<CellData> cellData) {
		this.cellData = cellData;
	}

	public TableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public String getOrgnizationID() {
		return orgnizationID;
	}

	public void setOrgnizationID(String orgnizationID) {
		this.orgnizationID = orgnizationID;
	}

	public Integer getDurationID() {
		return durationID;
	}

	public void setDurationID(Integer durationID) {
		this.durationID = durationID;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getReportMode() {
		return reportMode;
	}

	public void setReportMode(Integer reportMode) {
		this.reportMode = reportMode;
	}

	public Integer getReportOrgnizationID() {
		return reportOrgnizationID;
	}

	public void setReportOrgnizationID(Integer reportOrgnizationID) {
		this.reportOrgnizationID = reportOrgnizationID;
	}

	public long getTableModelID() {
		return TableModelID;
	}

	public void setTableModelID(long tableModelID) {
		TableModelID = tableModelID;
	}

	public String getUploadedDocmentID() {
		return uploadedDocmentID;
	}

	public void setUploadedDocmentID(String uploadedDocmentID) {
		this.uploadedDocmentID = uploadedDocmentID;
	}

}
