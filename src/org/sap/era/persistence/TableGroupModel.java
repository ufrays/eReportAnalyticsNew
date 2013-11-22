package org.sap.era.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.TemporalType.DATE;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.sap.era.persistence.TableModel;
import java.util.Collection;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "T_TABLEGROUPMODEL")
@NamedQueries({
	@NamedQuery(name = "AllTableGroupModels", query = "select tgm from TableGroupModel tgm"),
	@NamedQuery(name = "GetTableGroupModelByID", query = "select tgm from TableGroupModel tgm where tgm.id = :groupID")
})
public class TableGroupModel {

	@Id
	@GeneratedValue(strategy = AUTO)
	private long id;
	@Basic
	private int flag;
	@Basic
	private String name;
	@Basic
	@Temporal(DATE)
	private Date createdOn;
	@Basic
	private int createdBy;
	@Basic
	private String modelPath;
	@OneToMany(cascade= CascadeType.ALL,mappedBy="tableGroupModel")
	private List<TableModel> tableModel;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getModelPath() {
		return modelPath;
	}
	public void setModelPath(String modelPath) {
		this.modelPath = modelPath;
	}
	@JsonIgnore
	public List<TableModel> getTableModel() {
		return tableModel;
	}
	public void setTableModel(List<TableModel> tableModel) {
		this.tableModel = tableModel;
	}



}