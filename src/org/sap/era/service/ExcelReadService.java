package org.sap.era.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sap.era.persistence.CellData;
import org.sap.era.persistence.CellModel;
import org.sap.era.persistence.TableModel;
import org.sap.era.service.excel.Control;
import org.sap.era.service.excel.ExcelForm;
import org.sap.era.service.excel.LabelControl;
import org.sap.era.service.excel.ParseException;
import org.sap.era.service.excel.SpanCell;
import org.sap.era.service.validation.ValidatorError;
import org.sap.era.service.validation.ValidatorSheetErrors;

public class ExcelReadService {

	private CellModelService cellModelService;

	private TableModelService tableModelService;

	private TableGroupModelService tableGroupModelService;

	public CellModelService getCellModelService() {
		return cellModelService;
	}

	public void setCellModelService(CellModelService cellModelService) {
		this.cellModelService = cellModelService;
	}

	public TableModelService getTableModelService() {
		return tableModelService;
	}

	public void setTableModelService(TableModelService tableModelService) {
		this.tableModelService = tableModelService;
	}

	public TableGroupModelService getTableGroupModelService() {
		return tableGroupModelService;
	}

	public void setTableGroupModelService(TableGroupModelService tableGroupModelService) {
		this.tableGroupModelService = tableGroupModelService;
	}

	public static List<ExcelForm> getAllExcelForm(XSSFWorkbook wb) throws FileNotFoundException, IOException, ParseException {
		List<ExcelForm> list = new ArrayList<ExcelForm>();
		ExcelForm excelForm;

		for (int i = 0; i < wb.getNumberOfSheets(); i++) {
			excelForm = parseExcelFile(wb, String.valueOf(i));
			list.add(excelForm);
		}
		return list;
	}

	// parse the single worksheet into object-ExcelForm
	public static ExcelForm parseExcelFile(XSSFWorkbook wb, String sheetId) throws ParseException {
		ExcelForm excelForm = new ExcelForm();
		try {
			// XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = wb.getSheetAt(Integer.parseInt(sheetId));

			int startRow = sheet.getFirstRowNum();
			int totalRows = sheet.getLastRowNum();
			int startCol = 0;
			int totalCols = 0;
			XSSFRow row = null;
			// get all SpanCells in this Sheet.
			List<SpanCell> spanCells = getSpanCells(sheet);
			// get all Cells and SpanCell
			for (int i = startRow; i <= totalRows; i++) {
				row = sheet.getRow(i);
				int firstCellNum = row.getFirstCellNum();
				int lastCellNum = row.getLastCellNum();
				// get start column & total columns
				if (i == startRow) {
					startCol = firstCellNum;
					totalCols = lastCellNum;
				} else {
					if (startCol > firstCellNum)
						startCol = firstCellNum;
					if (totalCols < lastCellNum)
						totalCols = lastCellNum;
				}
				Cell cell = null;
				for (int j = firstCellNum; j < lastCellNum; j++) {
					cell = row.getCell(j);
					boolean isMergedCell = false; // is merged cell?
					SpanCell spanCell = null;
					// Case1:check if the cell is the spanCell.
					if (spanCells != null && spanCells.size() > 0) {
						spanCell = lookForSpanCell(spanCells, i, j);
					}

					if (spanCell != null) {
						// Here, we only need the cell in the first row & column
						if (i == spanCell.getFirstrow() && j == spanCell.getFirstcol()) {
							// excelForm.addCell(i, j, new Control());
							// continue;
							isMergedCell = true;
						} else {
							continue;
						}

					}
					// Add all cells into excelForm (spanCell & single Cell)
					Control control = new LabelControl();
					if (isMergedCell) {
						control.setIsMerge(isMergedCell);
						control.setRowspan(spanCell.getRowspan() + 1);// "+1" is
																		// for
																		// html
						control.setColspan(spanCell.getColspan() + 1);// "+1" is
																		// for
																		// html
					}
					if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
						control.setCellType(CellModel.CELL_TYPE_BLANK);
						control.setText("");
						excelForm.addCell(i + 1, j + 1, control);
					} else if (cell.toString().startsWith("#N")) {
						control.setCellType(CellModel.CELL_TYPE_DATA);
						control.setCellDataType(CellModel.CELL_DATA_TYPE_NUMERIC);
						control.setText(cell.toString());
						excelForm.addCell(i + 1, j + 1, control);
					} else if (cell.toString().startsWith("#T")) {
						control.setCellType(CellModel.CELL_TYPE_DATA);
						control.setCellDataType(CellModel.CELL_DATA_TYPE_STRING);
						control.setText(cell.toString());
						excelForm.addCell(i + 1, j + 1, control);
					} else {
						control.setCellType(CellModel.CELL_TYPE_LABEL);
						control.setCellDataType(CellModel.CELL_DATA_TYPE_STRING);
						control.setText(cell.toString());
						excelForm.addCell(i + 1, j + 1, control);
					}
				}
			}
			// set the start row
			excelForm.setSheetID(Integer.parseInt(sheetId));
			excelForm.setSheetName(sheet.getSheetName());
			excelForm.setStartRow(startRow);
			excelForm.setRows(totalRows - startRow + 1);
			excelForm.setStartCol(startCol);
			excelForm.setCols(totalCols - startCol);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return excelForm;
	}

	//
	public List readDataCellsFromTableModelGroup(Document doc, String tableModelGroupID) {
		//
		List<TableModel> list = (List<TableModel>) tableModelService.getAllTableModelsByTableGroupModel(tableModelGroupID);
		for (int i = 0; i < list.size(); i++) {

		}

		return null;
	}

	/*
	 * Read the "fixed-cells" type sheet, return the model & basic format
	 * errors, and the CellData; (Basic Validate: Template Consistancy & Cell
	 * DataType Validation)
	 * 
	 * @Parm:Document TableModel
	 * 
	 * @Return:ValidatorSheetErrors
	 * 
	 * @Author:Jason.Zan
	 * 
	 * @Date:2013.11.24
	 */
	private ValidatorSheetErrors readFixedCellsFromTableModel(Document doc, TableModel tableModel) {
		InputStream is;
		XSSFWorkbook wb;
		XSSFSheet sheet;
		int excelSheetID = tableModel.getSheetID();
		String excelSheetName = tableModel.getSheetName();
		HashMap<String, CellData> cellsValue = new HashMap<String, CellData>();
		List<ValidatorError> modelErrors = new ArrayList<ValidatorError>();
		List<ValidatorError> formatErrors = new ArrayList<ValidatorError>();
		List<CellData> cellDataList = new ArrayList<CellData>();
		// get the cells for data abstraction
		try {
			is = doc.getContentStream().getStream();
			wb = new XSSFWorkbook(is);
			sheet = wb.getSheetAt(excelSheetID);
			// 1. validate for the cells of "LABEL","DATA", "PARM" type
			// get all cells
			List<CellModel> cellsForValue = cellModelService.getAllCellModelsByTableModel(tableModel.getId());
			int cellCount = cellsForValue.size();
			XSSFRow excelRow = null;

			for (int i = 0; i < cellCount; i++) {
				CellModel cellModel = cellsForValue.get(i); // CellModel
															// instance
				int row = cellModel.getRow();
				int column = cellModel.getCol();
				int cellType = cellModel.getType();
				int cellDataType = cellModel.getDataType();
				//
				excelRow = sheet.getRow(row);
				Cell cell = excelRow.getCell(column);
				if (cellType == CellModel.CELL_TYPE_LABEL) {
					// 1. check equals (model check)
					if (!cellModel.getLabel().equals(cell.getStringCellValue())) {
						modelErrors.add(new ValidatorError("Cell-[row:" + row + ",column:" + column + "] isn't consistent.",
								ValidatorError.LEVEL_ERROR));
					}
				} else if (cellType == CellModel.CELL_TYPE_DATA) {
					CellData cellData = new CellData(); // The cellData for the
														// corresponding
														// cellModel.
					// 2. check value type
					String value = cell.toString();
					if (cellDataType == CellModel.CELL_DATA_TYPE_NUMERIC) {// for
																			// Numeric
																			// cell
						// validate
						if (cell.getCellType() != Cell.CELL_TYPE_NUMERIC) {
							formatErrors.add(new ValidatorError("Cell-[row:" + row + ",column:" + column + "] isn't numeric type.",
									ValidatorError.LEVEL_ERROR));
						} else {
							cellData.setDataDouble(Double.parseDouble(value));
						}
					} else if (cell.getCellType() == CellModel.CELL_DATA_TYPE_STRING) {// for
																						// String
																						// cell
						cellData.setDataText(value);
						// validate? -- no need for String type
						// if (cell.getCellType() != Cell.CELL_TYPE_STRING){
						// formatErrors.add(new
						// ValidatorError("Cell-[row:"+row+",column:"+column+"] isn't  type.",ValidatorError.LEVEL_ERROR));
						// }
					} else {
						cellData.setDataText(value);
					}
					// add the cellData into result list
					cellData.setCol(column);
					cellData.setRow(row);
					cellData.setType(cellDataType);
					cellDataList.add(cellData);
				} else if (cellType == CellModel.CELL_TYPE_PARM) {
					// 3. check parm type
					// ignore....
				} else if (cellType == CellModel.CELL_TYPE_BLANK) {
					// 4. check if it is blank
					// ignore....
				} else {

				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return errors
		ValidatorSheetErrors validatorSheetErrors = new ValidatorSheetErrors();
		validatorSheetErrors.setSheetID(tableModel.getSheetID());
		validatorSheetErrors.setSheetName(tableModel.getSheetName());
		validatorSheetErrors.setModelErrors(modelErrors);
		validatorSheetErrors.setFormatErrors(formatErrors);
		return validatorSheetErrors;
	}

	/*
	 * Read the "MiltiLine-cells" type sheet, return the model & basic format
	 * errors, and the CellData; (Basic Validate: Template Consistancy & Cell
	 * DataType Validation)
	 * 
	 * @Parm:Document TableModel
	 * 
	 * @Return:ValidatorSheetErrors
	 * 
	 * @Author:Jason.Zan
	 * 
	 * @Date:2013.11.25 //Note:need more: If the cell is the first column, then
	 * to judge how many rows via the column of the cell.#K
	 */
	private ValidatorSheetErrors readMiltiLineCellsFromTableModel(Document doc, TableModel tableModel) {
		InputStream is;
		XSSFWorkbook wb;
		XSSFSheet sheet;
		int excelSheetID = tableModel.getSheetID();
		String excelSheetName = tableModel.getSheetName();
		HashMap<String, CellData> cellsValue = new HashMap<String, CellData>();
		List<ValidatorError> modelErrors = new ArrayList<ValidatorError>();
		List<ValidatorError> formatErrors = new ArrayList<ValidatorError>();
		List<CellData> cellDataList = new ArrayList<CellData>();
		// get the cells for data abstraction
		try {
			is = doc.getContentStream().getStream();
			wb = new XSSFWorkbook(is);
			sheet = wb.getSheetAt(excelSheetID);
			// 1. validate for the cells of "LABEL"
			List<CellModel> cellsForLabel = cellModelService.getAllCellModelsforLabelByTableModel(tableModel.getId());// get
																														// all
																														// cells
			XSSFRow excelRow = null;
			for (int i = 0; i < cellsForLabel.size(); i++) {
				CellModel cellModel = cellsForLabel.get(i); // CellModel
															// instance
				int row = cellModel.getRow();
				int column = cellModel.getCol();
				excelRow = sheet.getRow(row);
				Cell cell = excelRow.getCell(column);
				if (!cellModel.getLabel().equals(cell.getStringCellValue())) {
					modelErrors.add(new ValidatorError("Cell-[row:" + row + ",column:" + column + "] isn't consistent.", ValidatorError.LEVEL_ERROR));
				}
			}
			// 2. validate for the cells of "PARM" type

			// 3. validate for the cells of "DATA"//, "PARM" type &
			List<CellModel> cellsForValue = cellModelService.getAllCellModelsforDataByTableModel(tableModel.getId());// get
																														// all
																														// cells
			int totalRowNum = sheet.getLastRowNum();
			for (int i = 0; i < cellsForValue.size(); i++) {
				CellModel cellModel = cellsForValue.get(i); // CellModel
															// instance
				int row = cellModel.getRow();
				int column = cellModel.getCol();
				int cellDataType = cellModel.getDataType();
				//
				for (int j = row; j < totalRowNum + 1; j++) {
					excelRow = sheet.getRow(row);
					Cell cell = excelRow.getCell(column);
					CellData cellData = new CellData(); // The cellData for the
														// corresponding
														// cellModel.
					// 2. check value type
					String value = cell.toString();
					if (cellDataType == CellModel.CELL_DATA_TYPE_NUMERIC) {// for
																			// Numeric
																			// cell
						// validate
						if (cell.getCellType() != Cell.CELL_TYPE_NUMERIC) {
							formatErrors.add(new ValidatorError("Cell-[row:" + row + ",column:" + column + "] isn't numeric type.",
									ValidatorError.LEVEL_ERROR));
						} else {
							cellData.setDataDouble(Double.parseDouble(value));
						}
					} else if (cell.getCellType() == CellModel.CELL_DATA_TYPE_STRING) {// for
																						// String
																						// cell
						cellData.setDataText(value);
					} else {
						cellData.setDataText(value);
					}
					// add the cellData into result list
					cellData.setCol(column);
					cellData.setRow(row);
					cellData.setType(cellDataType);
					cellDataList.add(cellData);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return errors
		ValidatorSheetErrors validatorSheetErrors = new ValidatorSheetErrors();
		validatorSheetErrors.setSheetID(tableModel.getSheetID());
		validatorSheetErrors.setSheetName(tableModel.getSheetName());
		validatorSheetErrors.setModelErrors(modelErrors);
		validatorSheetErrors.setFormatErrors(formatErrors);
		return validatorSheetErrors;
	}

	/*
	 * return all MergedRange Cell of a sheet into list<SpanCell>
	 */
	public static List<SpanCell> getSpanCells(Sheet sheet) {
		//
		List<SpanCell> spanCells = new ArrayList<SpanCell>();
		int sheetmergerCount = sheet.getNumMergedRegions();
		CellRangeAddress ra = null;
		for (int i = 0; i < sheetmergerCount; i++) {
			ra = sheet.getMergedRegion(i);
			SpanCell spanCell = new SpanCell();
			Cell cell = sheet.getRow(ra.getFirstRow()).getCell(ra.getFirstColumn()); // Cell
			spanCell.setCell(cell);
			spanCell.setFirstcol(ra.getFirstColumn());
			spanCell.setFirstrow(ra.getFirstRow());
			spanCell.setLastcol(ra.getLastColumn());
			spanCell.setLastrow(ra.getLastRow());
			spanCell.setColspan(ra.getLastColumn() - ra.getFirstColumn()); // colSpan
			spanCell.setRowspan(ra.getLastRow() - ra.getFirstRow()); // rowSpan
			spanCells.add(spanCell);
		}

		return spanCells;
	}

	/*
	 * return the SpanCell if it can find it, else return null
	 */
	public static SpanCell lookForSpanCell(List<SpanCell> spanCells, int row, int col) {
		SpanCell spanCell = null;
		int tempRow = 0;
		int tempCol = 0;
		// find the spanCell: if find, return the object; else return null
		for (int i = 0; i < spanCells.size(); i++) {
			spanCell = spanCells.get(i);
			// If the cell is SpanCell
			if (row >= spanCell.getFirstrow() && row <= spanCell.getLastrow()) {
				if (col >= spanCell.getFirstcol() && col <= spanCell.getLastcol()) {
					return spanCell;
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("C:\\temp\\test.xlsx"));
			XSSFSheet sheet = wb.getSheetAt(0);
			int i = 0;
		} catch (Exception ex) {

		}
	}
}
