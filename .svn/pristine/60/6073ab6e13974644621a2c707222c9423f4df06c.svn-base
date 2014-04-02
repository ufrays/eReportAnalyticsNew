package org.sap.era.service;

import java.util.List;

import javax.annotation.Resource;

import org.sap.era.dao.CellModelDAO;
import org.sap.era.persistence.CellModel;
import org.sap.era.persistence.TableModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "cellModelService")
public class CellModelService {

	@Autowired
	@Resource
	private CellModelDAO cellModelDAO;

	public void setCellModelDAO(CellModelDAO cellModelDAO) {
		this.cellModelDAO = cellModelDAO;
	}

	/**
	 * 
	 * @param cellModel
	 * @param TableModelID
	 */
	public void addCellModel(CellModel cellModel, int TableModelID) {
		TableModel tableModel = new TableModel();
		tableModel.setId(TableModelID);
		cellModel.setTableModel(tableModel);
		this.cellModelDAO.addCellModel(cellModel);
	}

	/**
	 * 
	 * @param tableModelID
	 * @return
	 */
	public List<CellModel> getAllCellModelsByTableModel(long tableModelID) {
		return this.cellModelDAO.getAllCellModelsByTableModel(tableModelID);
	}

	/**
	 * 
	 * @param tableModelID
	 * @return
	 */
	public List<CellModel> getAllCellModelsforLabelByTableModel(long tableModelID) {
		return this.cellModelDAO.getAllCellModelsforLabelByTableModel(tableModelID);
	}

	/**
	 * 
	 * @param tableModelID
	 * @return
	 */
	public List<CellModel> getAllCellModelsforParmByTableModel(long tableModelID) {
		return this.cellModelDAO.getAllCellModelsforParmByTableModel(tableModelID);
	}

	/**
	 * 
	 * @param tableModelID
	 * @return
	 */
	public List<CellModel> getAllCellModelsforDataByTableModel(long tableModelID) {
		return this.cellModelDAO.getAllCellModelsforDataByTableModel(tableModelID);
	}
}
