package org.sap.era.persistence;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_ASSIGNED_ORGNAZITION")
public class AssignedOrgnazition {

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	private Orgnazition orgnazition;

	@ManyToOne(fetch = FetchType.LAZY)
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