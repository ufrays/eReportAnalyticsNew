package org.sap.era.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.sap.era.dao.TableGroupModelDAO;
import org.sap.era.dto.TableGroupModelDTO;
import org.sap.era.persistence.CellModel;
import org.sap.era.persistence.TableGroupModel;
import org.sap.era.persistence.TableModel;
import org.sap.era.service.excel.Control;
import org.sap.era.service.excel.ExcelForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "tableGroupModelService")
public class TableGroupModelService {

	@Autowired
	@Resource
	private TableGroupModelDAO tableGroupModelDAO;

	private PersonService personService;

	public void setTableGroupModelDAO(TableGroupModelDAO tableGroupModelDAO) {
		this.tableGroupModelDAO = tableGroupModelDAO;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	/**
	 * 
	 * @return
	 */
	public List<TableGroupModel> getAllTableGroupModels() {
		return this.tableGroupModelDAO.getAllTableGroupModels();

	}

	/**
	 * 
	 * @return
	 */
	public List<TableGroupModel> getAllReleasedTableGroupModels() {
		return this.tableGroupModelDAO.getAllReleasedTableGroupModels();

	}

	public List<TableGroupModelDTO> getAllTableGroupModelDTO() {
		List<TableGroupModel> list = this.tableGroupModelDAO.getAllTableGroupModels();
		List<TableGroupModelDTO> listDTO = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			TableGroupModelDTO dto = new TableGroupModelDTO();
			// Person person =
			// personService.getPersonsByID(list.get(i).getCreatedBy());
			dto.setId(list.get(i).getId());
			dto.setName(list.get(i).getName());
			dto.setFlag(TableGroupModel.TABLEGROUPMODEL_FLAG[list.get(i).getFlag()]);
			dto.setStatus(list.get(i).getStatus());
			dto.setCreatedOn(list.get(i).getCreatedOn());
			dto.setCreatedBy("Test Person, 003");// (person.getLastName()
													// + ", " +
													// person.getFirstName());
			dto.setCategory(TableGroupModel.TABLEGROUPMODEL_CATEGORY[list.get(i).getCategory()]);
			listDTO.add(dto);
		}
		return listDTO;
	}

	/**
	 * 
	 * @param groupID
	 * @return
	 */
	public TableGroupModel getTableGroupModelByID(long groupID) {
		return this.tableGroupModelDAO.getTableGroupModelByID(groupID);

	}

	/**
	 * 
	 * @param tableGroupModel
	 */
	public void addTableGroupModel(TableGroupModel tableGroupModel) {
		this.tableGroupModelDAO.addTableGroupModel(tableGroupModel);
	}

	/**
	 * 
	 * @param tableGroupModel
	 */
	public void updateTableGroupModel(TableGroupModel tableGroupModel) {
		this.tableGroupModelDAO.updateTableGroupModel(tableGroupModel);
	}

	/**
	 * 
	 * @param long
	 */
	public void deleteTableGroupModel(long groupID) {
		this.tableGroupModelDAO.delete(groupID, TableGroupModel.class);
	}

	/**
	 * 
	 * @param tableGroupModel
	 * @param list
	 */
	public void addTableGroupModel(TableGroupModel tableGroupModel, List<ExcelForm> list) {

		List<TableModel> listTableModel = new ArrayList<TableModel>();
		System.out.println("Data-Start");
		for (int i = 0; i < list.size(); i++) {
			TableModel tableModel = new TableModel();

			ExcelForm excelForm = list.get(i);

			tableModel.setSheetID(excelForm.getSheetID());
			tableModel.setSheetName(excelForm.getSheetName());
			tableModel.setRow(excelForm.getRows());
			tableModel.setStartRow(excelForm.getStartRow());
			tableModel.setCol(excelForm.getCols());
			tableModel.setStartCol(excelForm.getStartCol());
			tableModel.setTableGroupModel(tableGroupModel);

			Map<String, Control> cells = excelForm.getCells();

			Iterator<String> it = cells.keySet().iterator();
			List<CellModel> listCellModel = new ArrayList<CellModel>();
			System.out.println("Data-Sheet:" + excelForm.getSheetName());
			while (it.hasNext()) {

				String key = it.next().toString();
				String[] keyTemp = key.split("-");
				int col = Integer.parseInt(keyTemp[1]);
				int row = Integer.parseInt(keyTemp[0]);
				Control cellControl = cells.get(key);
				CellModel cellModel = new CellModel();
				cellModel.setCol(col);
				cellModel.setRow(row);
				if (cellControl.getIsMerge()) {
					cellModel.setColspan(cellControl.getColspan());
				}
				cellModel.setLabel(cellControl.getText());
				cellModel.setRowspan(cellControl.getRowspan());
				cellModel.setType(cellControl.getCellType());
				cellModel.setDataType(cellControl.getCellDataType());
				cellModel.setTableModel(tableModel);
				listCellModel.add(cellModel);
				System.out.println("Data-Cell:" + cellModel.getKey() + "-value:" + cellModel.getLabel());

			}
			tableModel.setCellModel(listCellModel);
			listTableModel.add(tableModel);

		}
		tableGroupModel.setTableModel(listTableModel);

		this.tableGroupModelDAO.addTableGroupModel(tableGroupModel);
	}

}
