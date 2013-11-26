package org.sap.era.service;

import java.util.List;

import org.sap.era.dao.CellModelDAO;
import org.sap.era.persistence.CellModel;
import org.sap.era.persistence.TableModel;

public class CellModelService {

	private CellModelDAO cellModelDAO;

	public CellModelDAO getCellModelDAO() {
		return cellModelDAO;
	}

	public void setCellModelDAO(CellModelDAO cellModelDAO) {
		this.cellModelDAO = cellModelDAO;
	}

	//
	public List<CellModel> getAllCellModelsByTableModel(String tableModelID) {

		return this.cellModelDAO.getAllCellModelsByTableModel(tableModelID);

	}

	//
	public void addCellModel(CellModel cellModel, int TableModelID) {
		TableModel tableModel = new TableModel();
		tableModel.setId(TableModelID);
		cellModel.setTableModel(tableModel);
		this.cellModelDAO.addCellModel(cellModel);
	}

}
