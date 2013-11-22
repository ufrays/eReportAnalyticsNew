package org.sap.era.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sap.era.dao.TableModelDAO;
import org.sap.era.persistence.CellModel;
import org.sap.era.persistence.TableGroupModel;
import org.sap.era.persistence.TableModel;
import org.sap.era.service.excel.Control;
import org.sap.era.service.excel.ExcelForm;
import org.sap.era.service.excel.LabelControl;

public class TableModelService {

	private TableModelDAO tableModelDAO;

	public TableModelDAO getTableModelDAO() {
		return tableModelDAO;
	}

	public void setTableModelDAO(TableModelDAO tableModelDAO) {
		this.tableModelDAO = tableModelDAO;
	}
	
	//
	public List<TableModel> getAllTableModelsByTableGroupModel(String tableGroupModelID) {
		return this.tableModelDAO.getAllTableModelsByTableGroupModel(tableGroupModelID);
	}
	
	//
	public void addTableModel(TableModel tableModel, int tableGroupModelID) {
		TableGroupModel tableGroupModel= new TableGroupModel();
		tableGroupModel.setId(tableGroupModelID);
		tableModel.setTableGroupModel(tableGroupModel);
		this.tableModelDAO.addTableModel(tableModel);
	}
	
	//Convert the tableModel List into Excel Form List
	public List<ExcelForm> convertTableModles2ExcelModels(List<TableModel> tableModels){
		List<ExcelForm> excelFormList = new ArrayList<ExcelForm>();
		
		TableModel tableModel;
		List<CellModel> cellModels;
		for(int i = 0; i< tableModels.size(); i++){
			ExcelForm excelForm = new ExcelForm();
			tableModel = tableModels.get(i);
			excelForm.setCols(tableModel.getCol());
			excelForm.setRows(tableModel.getRow());
			excelForm.setSheetID(tableModel.getSheetID());
			excelForm.setSheetName(tableModel.getSheetName());
			excelForm.setStartCol(tableModel.getStartCol());
			excelForm.setStartRow(tableModel.getStartRow());
			excelForm.setTableModelID(tableModel.getId());//
			cellModels = tableModel.getCellModel();
			Map<String,Control> cells  = new HashMap<String,Control>();
			for(int j = 0; j< cellModels.size(); j++){
				CellModel cell = cellModels.get(j);
				String key = cell.getKey();
				Control control  = new LabelControl(cell.getLabel(),cell.getColspan(),cell.getRowspan(),cell.isMerged(),cell.getType());
				cells.put(key, control);
			}
			excelForm.setCells(cells);
			excelFormList.add(excelForm);
		}
		return excelFormList;
	}
	
}
