package org.sap.era.persistence;

import java.util.Date;
import java.util.List;
import org.sap.era.main.*;
import javax.persistence.*;

import static javax.persistence.TemporalType.DATE;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.sap.era.persistence.TableModel;
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
	private int flag;//Model Type
	@Basic
	private int category; //
	@Basic
	private String name;
	@Basic
	private String status;
	@Basic
	@Temporal(DATE)
	private Date createdOn;
	@Basic
	private int createdBy;
	@Basic
	private String modelPath;
	@Basic
	private String description;
	@Basic
	private String modelPath2;
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
	@JsonSerialize(using = CustomDateSerializer.class)  
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
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getModelPath2() {
		return modelPath2;
	}
	public void setModelPath2(String modelPath2) {
		this.modelPath2 = modelPath2;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}



}