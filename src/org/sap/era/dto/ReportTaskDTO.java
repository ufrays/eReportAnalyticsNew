package org.sap.era.dto;

public class ReportTaskDTO {

	private long tableGroupModel;

	private String tableGroupModelName;
	
	private String durationID;

	private String durationFlag;

	private Integer status;
	
	private String startDate;

	private String endDate;
	
	private boolean isAllReporters;

	public long getTableGroupModel() {
		return tableGroupModel;
	}

	public void setTableGroupModel(long tableGroupModel) {
		this.tableGroupModel = tableGroupModel;
	}

	public String getDurationID() {
		return durationID;
	}

	public void setDurationID(String durationID) {
		this.durationID = durationID;
	}

	public String getDurationFlag() {
		return durationFlag;
	}

	public void setDurationFlag(String durationFlag) {
		this.durationFlag = durationFlag;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isAllReporters() {
		return isAllReporters;
	}

	public void setAllReporters(boolean isAllReporters) {
		this.isAllReporters = isAllReporters;
	}

	public String getTableGroupModelName() {
		return tableGroupModelName;
	}

	public void setTableGroupModelName(String tableGroupModelName) {
		this.tableGroupModelName = tableGroupModelName;
	}



}
