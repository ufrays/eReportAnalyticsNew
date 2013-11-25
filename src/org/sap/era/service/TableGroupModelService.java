package org.sap.era.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.sap.era.dao.TableGroupModelDAO;
import org.sap.era.persistence.CellModel;
import org.sap.era.persistence.TableGroupModel;
import org.sap.era.persistence.TableModel;
import org.sap.era.service.excel.Control;
import org.sap.era.service.excel.ExcelForm;

public class TableGroupModelService {

	private TableGroupModelDAO tableGroupModelDAO;

	public TableGroupModelDAO getTableGroupModelDAO() {
		return tableGroupModelDAO;
	}

	public void setTableGroupModelDAO(TableGroupModelDAO tableGroupModelDAO) {
		this.tableGroupModelDAO = tableGroupModelDAO;
	}
	
	//
	public List<TableGroupModel> getAllTableGroupModels() {
		return this.tableGroupModelDAO.getAllTableGroupModels();
		
	}
	
	//
	public TableGroupModel getTableGroupModelByID(String groupID) {
		return this.tableGroupModelDAO.getTableGroupModelByID(Long.parseLong(groupID));
		
	}
	
	//
	public void addTableGroupModel(TableGroupModel tableGroupModel) {
		this.tableGroupModelDAO.addTableGroupModel(tableGroupModel);
	}
	
	
	//
	public void addTableGroupModel(TableGroupModel tableGroupModel, List<ExcelForm> list) {
		
		List<TableModel> listTableModel = new ArrayList<TableModel>();
		System.out.println("Data-Start");
		for (int i = 0 ; i < list.size() ; i++){
			TableModel tableModel = new TableModel();
			
			ExcelForm excelForm = list.get(i);
			
			tableModel.setSheetID(excelForm.getSheetID());
			tableModel.setSheetName(excelForm.getSheetName());
			tableModel.setRow(excelForm.getRows());
			tableModel.setStartRow(excelForm.getStartRow());
			tableModel.setCol(excelForm.getCols());
			tableModel.setStartCol(excelForm.getStartCol());
			tableModel.setTableGroupModel(tableGroupModel);
			
			Map<String,Control> cells = excelForm.getCells();
			
			Iterator it = cells.keySet().iterator();
			List<CellModel> listCellModel = new ArrayList<CellModel>();
			System.out.println("Data-Sheet:"+excelForm.getSheetName());
			while (it.hasNext()) {
				 
	            String key = it.next().toString();
	            String[] keyTemp = key.split("-");
	            int col = Integer.parseInt(keyTemp[1]);
	            int row = Integer.parseInt(keyTemp[0]);
	            Control cellControl = cells.get(key);
	            CellModel cellModel = new CellModel();
	            cellModel.setCol(col);
	            cellModel.setRow(row);
	            if(cellControl.getIsMerge()){
	            	cellModel.setColspan(cellControl.getColspan());
	            }
	            cellModel.setLabel(cellControl.getText());
	            cellModel.setRowspan(cellControl.getRowspan());
	            cellModel.setType(cellControl.getCellType());
	            cellModel.setDataType(cellControl.getCellDataType());
	            cellModel.setTableModel(tableModel);
	            listCellModel.add(cellModel);
	            System.out.println("Data-Cell:"+cellModel.getKey()+"-value:"+cellModel.getLabel());
	            
	        }
			tableModel.setCellModel(listCellModel);
			listTableModel.add(tableModel);
			
		}
		tableGroupModel.setTableModel(listTableModel);
		
		this.tableGroupModelDAO.addTableGroupModel(tableGroupModel);
	}
	
}
