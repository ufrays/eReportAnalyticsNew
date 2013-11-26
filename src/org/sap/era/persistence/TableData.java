package org.sap.era.persistence;

import static javax.persistence.TemporalType.DATE;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

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
	private Integer reportOrgnizationID;
	@OneToMany(mappedBy = "tableData")
	private Collection<CellData> cellData;

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

	public Collection<CellData> getCellData() {
		return cellData;
	}

	public void setCellData(Collection<CellData> cellData) {
		this.cellData = cellData;
	}

}