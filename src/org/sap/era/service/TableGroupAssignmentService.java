package org.sap.era.service;

import java.util.List;

import org.sap.era.dao.TableGroupAssignmentDAO;
import org.sap.era.persistence.Orgnazition;
import org.sap.era.persistence.TableGroupAssignment;

public class TableGroupAssignmentService {

	private TableGroupAssignmentDAO tableGroupAssignmentDAO;

	public TableGroupAssignmentDAO getTableGroupAssignmentDAO() {
		return tableGroupAssignmentDAO;
	}

	public void setTableGroupAssignmentDAO(
			TableGroupAssignmentDAO tableGroupAssignmentDAO) {
		this.tableGroupAssignmentDAO = tableGroupAssignmentDAO;
	}
	
	//
	public List<TableGroupAssignment> getAllTableGroupAssignments(){
		return tableGroupAssignmentDAO.getAllTableGroupAssignments();
	}
	
	//
	public TableGroupAssignment getTableGroupAssignmentByID(long ID){
		return tableGroupAssignmentDAO.getTableGroupAssignmentByID(ID);
	}
	
	//
	public void addTableGroupAssignment(TableGroupAssignment tableGroupAssignment){
		tableGroupAssignmentDAO.addTableGroupAssignment(tableGroupAssignment);
	}
	
	//
	public List<Orgnazition> getUnselectedOrgnazition(long tableGroupAssignmentID){
		return tableGroupAssignmentDAO.getUnselectedOrgnazition(tableGroupAssignmentID);
	}
	
}
