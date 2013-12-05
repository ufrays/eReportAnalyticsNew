package org.sap.era.dto;

public class ReportTaskDTO {

	private long tableGroupModel;

	private String durationID;

	private String durationFlag;

	private Integer status;

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

}
