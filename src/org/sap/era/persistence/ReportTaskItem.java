package org.sap.era.persistence;

import static javax.persistence.TemporalType.DATE;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.sap.era.main.CustomDateSerializer;

@Entity
@Table(name = "T_REPORTTASKITEM")
@NamedQueries({ @NamedQuery(name = "GetAllReportTaskItems", query = "select t from ReportTaskItem t"),
		@NamedQuery(name = "GetReportTaskItemByOrgID", query = "select t from ReportTaskItem t where t.orgnazition.id = :orgId") })
public class ReportTaskItem {

	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Orgnazition orgnazition;
	@Basic
	private String itemStatus;
	@ManyToOne
	private Orgnazition reportOrgnazition;
	@ManyToOne
	private Person reportPerson;
	@Basic
	@Temporal(DATE)
	private Date reportDate;
	@Basic
	private String errorInformation;
	@Basic
	private String errorReson;
	@Basic
	private String filePath;
	@ManyToOne
	@JoinColumn(name = "reportTask", nullable = false)
	private ReportTask reportTask;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Orgnazition getOrgnazition() {
		return orgnazition;
	}

	public void setOrgnazition(Orgnazition orgnazition) {
		this.orgnazition = orgnazition;
	}

	public void setItemStatus(String param) {
		this.itemStatus = param;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public Orgnazition getReportOrgnazition() {
		return reportOrgnazition;
	}

	public void setReportOrgnazition(Orgnazition reportOrgnazition) {
		this.reportOrgnazition = reportOrgnazition;
	}

	public Person getReportPerson() {
		return reportPerson;
	}

	public void setReportPerson(Person reportPerson) {
		this.reportPerson = reportPerson;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
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

	public ReportTask getReportTask() {
		return reportTask;
	}

	public void setReportTask(ReportTask reportTask) {
		this.reportTask = reportTask;
	}

}