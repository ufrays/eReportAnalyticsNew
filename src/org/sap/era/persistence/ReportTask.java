package org.sap.era.persistence;

import static javax.persistence.TemporalType.DATE;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.sap.era.main.CustomDateSerializer;

@Entity
@Table(name = "T_REPORTTASK")
@NamedQueries({ @NamedQuery(name = "GetAllReportTask", query = "select t from ReportTask t") })
public class ReportTask {

	@Id
	@GeneratedValue
	private long id;
	@Basic
	private long tableGroupModel;
	@Basic
	private String tableGroupModelName;
	@Basic
	private String durationID;
	@Temporal(DATE)
	private Date startDate;
	@Temporal(DATE)
	private Date endDate;
	@Basic
	private String reportMode;
	@Basic
	private String durationFlag;
	@Basic
	private String durationDepict;
	@Basic
	private Integer status;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "reportTask")
	private List<ReportTaskItem> reportTaskItem;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getReportMode() {
		return reportMode;
	}

	public void setReportMode(String reportMode) {
		this.reportMode = reportMode;
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

	@JsonIgnore
	public List<ReportTaskItem> getReportTaskItem() {
		return reportTaskItem;
	}

	public void setReportTaskItem(List<ReportTaskItem> reportTaskItem) {
		this.reportTaskItem = reportTaskItem;
	}

}