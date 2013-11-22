package org.sap.era.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.sap.era.service.excel.Control;
import org.sap.era.service.excel.ExcelForm;
import org.sap.era.service.excel.LabelControl;
import org.sap.era.service.excel.ParseException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.sap.era.service.excel.SpanCell;

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

	public void setTableGroupModelService(
			TableGroupModelService tableGroupModelService) {
		this.tableGroupModelService = tableGroupModelService;
	}

	
	public static List<ExcelForm> getAllExcelForm(XSSFWorkbook wb) throws FileNotFoundException, IOException, ParseException{
		List<ExcelForm> list = new ArrayList<ExcelForm>();
		ExcelForm excelForm;
		
		for(int i = 0 ; i < wb.getNumberOfSheets(); i++){
			excelForm = parseExcelFile(wb,String.valueOf(i));
			list.add(excelForm);
		}
		return list;
	}
	
	
	//parse the single worksheet into object-ExcelForm
	public static ExcelForm parseExcelFile(XSSFWorkbook wb, String sheetId) throws ParseException{
		ExcelForm excelForm = new ExcelForm();
		try{
			//XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
			XSSFSheet sheet = wb.getSheetAt( Integer.parseInt(sheetId) );
			
			int startRow = sheet.getFirstRowNum();
			int totalRows = sheet.getLastRowNum();
			int startCol = 0;
			int totalCols = 0;
			XSSFRow row = null;
			// get all SpanCells in this Sheet.
			List<SpanCell> spanCells = getSpanCells(sheet); 
			// get all Cells and SpanCell
			for (int i = startRow; i <= totalRows; i++){
				row = sheet.getRow(i);
				int firstCellNum = row.getFirstCellNum();
				int lastCellNum = row.getLastCellNum();
				//get start column & total columns
				if (i == startRow){
					startCol= firstCellNum;
					totalCols= lastCellNum;
				}
				else{
					if (startCol > firstCellNum ) startCol= firstCellNum;					
					if (totalCols < lastCellNum ) totalCols= lastCellNum;
				}
				Cell cell = null;
				for(int j = firstCellNum; j < lastCellNum; j++){
					cell = row.getCell(j);
					boolean isMergedCell = false; //is merged cell?
					SpanCell spanCell = null;
					//Case1:check if the cell is the spanCell.
					if (spanCells != null && spanCells.size() > 0){
						spanCell = lookForSpanCell(spanCells,i,j);
					}
					
					if (spanCell != null){
						//Here, we only need the cell in the first row & column
						if ( i == spanCell.getFirstrow() && j == spanCell.getFirstcol()){
							//excelForm.addCell(i, j, new Control());
							//continue;
							isMergedCell=true;
						}else{
							continue;
						}
						
					}
					//Add all cells into excelForm (spanCell & single Cell)
					Control control = new LabelControl();
					if (isMergedCell){
						control.setIsMerge(isMergedCell);
						control.setRowspan(spanCell.getRowspan()+1);//"+1" is for html 
						control.setColspan(spanCell.getColspan()+1);//"+1" is for html 
					}
					if (cell.getCellType() == cell.CELL_TYPE_BLANK){
						control.setCellType(control.CONTROL_TYPE_EMPTY);
						control.setText("");
						excelForm.addCell(i+1, j+1, control);
					}
					else if (cell.toString().startsWith("#N")){
						control.setCellType(control.CONTROL_TYPE_INPUT_NUMERIC);
						control.setText(cell.toString());
						excelForm.addCell(i+1, j+1, control);
					}
					else if (cell.toString().startsWith("#T")){
						control.setCellType(control.CONTROL_TYPE_INPUT_TEXT);
						control.setText(cell.toString());
						excelForm.addCell(i+1, j+1, control);
					}else{
						control.setCellType(control.CONTROL_TYPE_LABEL);
						control.setText(cell.toString());
						excelForm.addCell(i+1, j+1, control);
					}
				}
			}
			//set the start row
			excelForm.setSheetID(Integer.parseInt(sheetId));
			excelForm.setSheetName(sheet.getSheetName());
			excelForm.setStartRow(startRow);
			excelForm.setRows(totalRows - startRow + 1);
			excelForm.setStartCol(startCol);
			excelForm.setCols(totalCols - startCol);
			
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return excelForm;
	}
	
	/*
	 * return all MergedRange Cell of a sheet into list<SpanCell>
	 */
	public static List<SpanCell> getSpanCells(Sheet sheet){
		//
		List<SpanCell> spanCells=new ArrayList<SpanCell>();
		int sheetmergerCount = sheet.getNumMergedRegions();
		CellRangeAddress ra = null;
		for (int i = 0; i < sheetmergerCount; i++){
			ra = sheet.getMergedRegion(i);
			SpanCell spanCell = new SpanCell();
			Cell cell = sheet.getRow(ra.getFirstRow()).getCell(ra.getFirstColumn()); // Cell
			spanCell.setCell(cell); 
			spanCell.setFirstcol(ra.getFirstColumn());
			spanCell.setFirstrow(ra.getFirstRow());
			spanCell.setLastcol(ra.getLastColumn());
			spanCell.setLastrow(ra.getLastRow());
			spanCell.setColspan( ra.getLastColumn() - ra.getFirstColumn() ); // colSpan
			spanCell.setRowspan(ra.getLastRow() - ra.getFirstRow()); // rowSpan
			spanCells.add(spanCell);
		}
		
		return spanCells;
	}
	
	/*
	 * return the SpanCell if it can find it, else return null
	 */
	public static SpanCell lookForSpanCell(List<SpanCell> spanCells, int row, int col ){
		SpanCell spanCell = null;
		int tempRow = 0;
		int tempCol = 0;
		//find the spanCell: if find, return the object; else return null
		for(int i = 0; i < spanCells.size(); i++){ 
			spanCell = spanCells.get(i);
			//If the cell is SpanCell
			if (row >= spanCell.getFirstrow() && row <= spanCell.getLastrow()){
				if(col >= spanCell.getFirstcol() && col <= spanCell.getLastcol()){
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
		}
		catch (Exception ex) {

		}
	}
	
	
	
}
