package org.sap.era.persistence;

import java.util.List;

import javax.persistence.*;
import org.sap.era.persistence.TableGroupModel;

@Entity
@Table(name = "T_TABLEGROUPASSIGNMENT")
@NamedQueries({
	@NamedQuery(name = "AllTableGroupAssignments", query = "select tga from TableGroupAssignment tga"),
	@NamedQuery(name = "GetTableGroupAssignmentByID", query = "select tga from TableGroupAssignment tga where tga.id = :assignmentID")
})
public class TableGroupAssignment {

	@Id
	private long id;

	@Basic
	private String durationModel;

	@ManyToOne
	private TableGroupModel tableGroupModel;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tableGroupAssignment")
	private List<AssignedOrgnazition> assignedOrgnazition;

	@Basic
	private String name;

	@Basic
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDurationModel() {
		return durationModel;
	}

	public void setDurationModel(String durationModel) {
		this.durationModel = durationModel;
	}

	public TableGroupModel getTableGroupModel() {
		return tableGroupModel;
	}

	public void setTableGroupModel(TableGroupModel tableGroupModel) {
		this.tableGroupModel = tableGroupModel;
	}

	public List<AssignedOrgnazition> getAssignedOrgnazition() {
		return assignedOrgnazition;
	}

	public void setAssignedOrgnazition(
			List<AssignedOrgnazition> assignedOrgnazition) {
		this.assignedOrgnazition = assignedOrgnazition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}