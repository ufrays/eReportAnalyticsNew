package org.sap.era.persistence;

import static javax.persistence.TemporalType.DATE;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "T_REPORTTASK")
public class ReportTask {

	@Id
	private long id;

	private long orgizationID;

	private long TableGroupModel;

	private String TableGroupModelName;

	private long durationID;
	@Temporal(DATE)
	private Date startDate;
	@Temporal(DATE)
	private Date endDate;

	private String flag;

	private String reportMode;

	private String reportOrgizationID;

	private long reportBy;
	@Temporal(DATE)
	private Date reportAt;

	private String reportByName;

	private String durationFlag;

	private String durationDepict;

	private String errorInformation;

	private String errorReason;

	private Integer passStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrgizationID() {
		return orgizationID;
	}

	public void setOrgizationID(long orgizationID) {
		this.orgizationID = orgizationID;
	}

	public long getTableGroupModel() {
		return TableGroupModel;
	}

	public void setTableGroupModel(long tableGroupModel) {
		TableGroupModel = tableGroupModel;
	}

	public String getTableGroupModelName() {
		return TableGroupModelName;
	}

	public void setTableGroupModelName(String tableGroupModelName) {
		TableGroupModelName = tableGroupModelName;
	}

	public long getDurationID() {
		return durationID;
	}

	public void setDurationID(long durationID) {
		this.durationID = durationID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getReportMode() {
		return reportMode;
	}

	public void setReportMode(String reportMode) {
		this.reportMode = reportMode;
	}

	public String getReportOrgizationID() {
		return reportOrgizationID;
	}

	public void setReportOrgizationID(String reportOrgizationID) {
		this.reportOrgizationID = reportOrgizationID;
	}

	public long getReportBy() {
		return reportBy;
	}

	public void setReportBy(long reportBy) {
		this.reportBy = reportBy;
	}

	public Date getReportAt() {
		return reportAt;
	}

	public void setReportAt(Date reportAt) {
		this.reportAt = reportAt;
	}

	public String getReportByName() {
		return reportByName;
	}

	public void setReportByName(String reportByName) {
		this.reportByName = reportByName;
	}

	public String getDurationFlag() {
		return durationFlag;
	}

	public void setDurationFlag(String durationFlag) {
		this.durationFlag = durationFlag;
	}

	public String getDurationDepict() {
		return durationDepict;
	}

	public void setDurationDepict(String durationDepict) {
		this.durationDepict = durationDepict;
	}

	public String getErrorInformation() {
		return errorInformation;
	}

	public void setErrorInformation(String errorInformation) {
		this.errorInformation = errorInformation;
	}

	public String getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}

	public Integer getPassStatus() {
		return passStatus;
	}

	public void setPassStatus(Integer passStatus) {
		this.passStatus = passStatus;
	}

}