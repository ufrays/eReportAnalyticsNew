package org.sap.era.service;

import java.util.List;

import javax.annotation.Resource;

import org.sap.era.dao.TableGroupAssignmentDAO;
import org.sap.era.persistence.Orgnazition;
import org.sap.era.persistence.TableGroupAssignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "tableGroupAssignmentService")
public class TableGroupAssignmentService {

	@Autowired
	@Resource
	private TableGroupAssignmentDAO tableGroupAssignmentDAO;

	/**
	 * 
	 * @return
	 */
	public List<TableGroupAssignment> getAllTableGroupAssignments() {
		return tableGroupAssignmentDAO.getAllTableGroupAssignments();
	}

	/**
	 * 
	 * @param ID
	 * @return
	 */
	public TableGroupAssignment getTableGroupAssignmentByID(long ID) {
		return tableGroupAssignmentDAO.getTableGroupAssignmentByID(ID);
	}

	/**
	 * 
	 * @param tableGroupAssignment
	 */
	public void addTableGroupAssignment(TableGroupAssignment tableGroupAssignment) {
		tableGroupAssignmentDAO.addTableGroupAssignment(tableGroupAssignment);
	}

	/**
	 * 
	 * @param tableGroupAssignmentID
	 * @return
	 */
	public List<Orgnazition> getUnselectedOrgnazition(long tableGroupAssignmentID) {
		return tableGroupAssignmentDAO.getUnselectedOrgnazition(tableGroupAssignmentID);
	}

}
