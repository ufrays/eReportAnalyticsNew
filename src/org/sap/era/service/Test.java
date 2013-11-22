package org.sap.era.service;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sap.era.service.*;
import org.sap.era.service.excel.*;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream("C:\\temp\\test.xlsx"));
			XSSFSheet sheet = wb.getSheetAt(0);
			ExcelForm excelForm = new ExcelForm();
			
			excelForm = ExcelReadService.parseExcelFile(new XSSFWorkbook (new FileInputStream("C:\\temp\\test.xlsx")), "Sheet1");
			Set set = excelForm.getCells().entrySet();
			Iterator itr = set.iterator();
			while (itr.hasNext()) {
				Map.Entry me = (Map.Entry) itr.next();
				String[] zuobiao = ((String) me.getKey()).split("-");
				int rowId = Integer.parseInt(zuobiao[0]);
				int columnId = Integer.parseInt(zuobiao[1]);
				int type = ((Control) me.getValue()).getCellType();
				String value = ((Control) me.getValue()).getText();
				boolean isMerge = ((Control) me.getValue()).getIsMerge();
				int colspan = ((Control) me.getValue()).getColspan();
				int rowspan = ((Control) me.getValue()).getRowspan();			
				System.out.println("INSERT INTO CellModel(tableModel,type,label,row,col,colspan,rowspan ) VALUES  (50," 
						 + type+",'"+ value +"',"+ (rowId) +","+ columnId + ","+colspan+","+rowspan+")");
			
			}
			int i = 0;
			i =2;
		}
		catch (Exception ex) {

		}
	}

}
