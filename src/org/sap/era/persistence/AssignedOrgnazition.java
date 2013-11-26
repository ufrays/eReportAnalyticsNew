package org.sap.era.persistence;

import javax.persistence.*;

@Entity
@Table(name = "T_ASSIGNEDORGNAZITION")
public class AssignedOrgnazition {

	@Id
	private long id;
	@ManyToOne
	private Orgnazition orgnazition;

	@ManyToOne
	@JoinColumn(name = "tableGroupAssignment", nullable = false)
	private TableGroupAssignment tableGroupAssignment;

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

	public TableGroupAssignment getTableGroupAssignment() {
		return tableGroupAssignment;
	}

	public void setTableGroupAssignment(TableGroupAssignment tableGroupAssignment) {
		this.tableGroupAssignment = tableGroupAssignment;
	}

}