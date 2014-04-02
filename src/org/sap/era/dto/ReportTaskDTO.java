package org.sap.era.dto;

import java.util.List;

import org.sap.era.persistence.Orgnazition;
import org.sap.era.persistence.ReportTaskItem;

public class ReportTaskDTO {

	private long tableGroupModel;

	private String tableGroupModelName;

	private String durationID;

	private String durationFlag;
	
	private String durationDepict;

	private Integer status;

	private String startDate;

	private String endDate;

	private List<Orgnazition> orgnazitions;

	public long getTableGroupModel() {
		return tableGroupModel;
	}

	public void setTableGroupModel(long tableGroupModel) {
		this.tableGroupModel = tableGroupModel;
	}

	public String getTableGroupModelName() {
		return tableGroupModelName;
	}

	public void setTableGroupModelName(String tableGroupModelName) {
		this.tableGroupModelName = tableGroupModelName;
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

	public String getDurationDepict() {
		return durationDepict;
	}

	public void setDurationDepict(String durationDepict) {
		this.durationDepict = durationDepict;
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

	public List<Orgnazition> getOrgnazitions() {
		return orgnazitions;
	}

	public void setOrgnazitions(List<Orgnazition> orgnazitions) {
		this.orgnazitions = orgnazitions;
	}



	
	
	
}
