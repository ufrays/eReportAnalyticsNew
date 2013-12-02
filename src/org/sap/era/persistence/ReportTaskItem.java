package org.sap.era.persistence;

import javax.persistence.*;

@Entity
@Table(name = "T_REPORTTASKITEM")
public class ReportTaskItem {

	@Id
	private long id;
	@Basic
	private String orgnazition;
	@Basic
	private String itemStatus;
	@Basic
	private String reportOrgnazition;
	@Basic
	private String reportPerson;
	@Basic
	private String reportDate;
	@Basic
	private String errorInformation;
	@Basic
	private String errorReson;
	@Basic
	private String filePath;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setOrgnazition(String param) {
		this.orgnazition = param;
	}

	public String getOrgnazition() {
		return orgnazition;
	}

	public void setItemStatus(String param) {
		this.itemStatus = param;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setReportOrgnazition(String param) {
		this.reportOrgnazition = param;
	}

	public String getReportOrgnazition() {
		return reportOrgnazition;
	}

	public void setReportPerson(String param) {
		this.reportPerson = param;
	}

	public String getReportPerson() {
		return reportPerson;
	}

	public void setReportDate(String param) {
		this.reportDate = param;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setErrorInformation(String param) {
		this.errorInformation = param;
	}

	public String getErrorInformation() {
		return errorInformation;
	}

	public void setErrorReson(String param) {
		this.errorReson = param;
	}

	public String getErrorReson() {
		return errorReson;
	}

	public void setFilePath(String param) {
		this.filePath = param;
	}

	public String getFilePath() {
		return filePath;
	}

}