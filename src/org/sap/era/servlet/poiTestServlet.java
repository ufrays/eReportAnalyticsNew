package org.sap.era.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * Servlet implementation class poiTestServlet
 */
public class poiTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public poiTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//FileInputStream fs = new FileInputStream("C:\\temp\\test.xlsx");
		XSSFWorkbook wbs = new XSSFWorkbook(new FileInputStream("C:\\temp\\test.xlsx"));
		XSSFSheet sheets = wbs.getSheetAt(0);
		Cell cell_1_1 = sheets.getRow(0).getCell(0);
		//cell_1_1.get
		Cell cell_1_2 = sheets.getRow(0).getCell(1);
		Cell cell_2_1 = sheets.getRow(1).getCell(0);
		
		Cell cell_4_1 = sheets.getRow(3).getCell(0);
		Cell cell_1_4 = sheets.getRow(0).getCell(3);
		String test = cell_1_1.toString();
		
		int i = 0;

		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-Disposition","attachment;   filename=\""  +"test.xls"  +   "\""); 
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("new sheet");
		Row row = sheet.createRow((short) 1);
		Cell cell = row.createCell((short) 1);
		cell.setCellValue("This is a test of merging");
		sheet.addMergedRegion(new CellRangeAddress(
		1, //first row (0-based)
		1, //last row (0-based)
		1, //first column (0-based)
		2 //last column (0-based)
		));
		// Write the output to a file
		OutputStream  fileOut = response.getOutputStream();//new FileOutputStream("workbook.xls");
		wb.write(fileOut);
		response.flushBuffer();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
