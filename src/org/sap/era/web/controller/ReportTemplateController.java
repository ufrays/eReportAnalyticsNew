package org.sap.era.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sap.era.persistence.TableGroupModel;
import org.sap.era.service.ExcelReadService;
import org.sap.era.service.TableGroupModelService;
import org.sap.era.service.excel.CmisHelper;
import org.sap.era.service.excel.ExcelForm;
import org.sap.era.service.excel.ParseException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class ReportTemplateController {

	private TableGroupModelService tableGroupModelService;

	public ReportTemplateController() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "beans.xml" });
		tableGroupModelService = context.getBean("tableGroupModelService", TableGroupModelService.class);
	}

	/**
	 * Return all the report template as list with non-paging
	 * 
	 * @author I071053
	 * @param request
	 *            Standard servlet request context
	 * @return All the report template as list
	 */
	@RequestMapping(value = "/getReportTemplateList.do", method = RequestMethod.GET)
	@ResponseBody
	public List<TableGroupModel> getReportTemplateList(HttpServletRequest request) {
		return tableGroupModelService.getAllTableGroupModels();
	}

	@RequestMapping(value = "/uploadReportTemplate.do", method = RequestMethod.POST)
	@ResponseBody
	public TableGroupModel uploadReportTemplate(HttpServletRequest request) throws IOException {

		TableGroupModel tgm = new TableGroupModel();
		String realPathOfApp = request.getServletContext().getRealPath("");
		//
		CmisHelper cmis = new CmisHelper();
		tgm.setName("name");
		File file = cmis.uploadDocument(realPathOfApp, request);
		Document doc = cmis.addDocument(file);
		file.delete();
		tgm.setModelPath(doc.getId());
		return tgm;
	}

	@RequestMapping(value = "/saveReportTemplate", method = RequestMethod.POST)
	@ResponseBody
	public String saveReportTemplate(TableGroupModel tgm) throws IOException, ParseException {
		CmisHelper cmis = new CmisHelper();
		List<ExcelForm> listExcelForm;
		String docID = tgm.getModelPath();
		Document doc = cmis.getDocumentById(docID);
		InputStream is = doc.getContentStream().getStream();
		FileInputStream fis = (FileInputStream) is;
		//
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		listExcelForm = ExcelReadService.getAllExcelForm(wb);
		tgm.setModelPath(doc.getId());
		tgm.setCreatedBy(1);
		tgm.setCreatedOn(new java.util.Date());
		tgm.setFlag(0);
		// tgm.setTableModel(tableModel)
		tableGroupModelService.addTableGroupModel(tgm, listExcelForm);
		return "The report template was saved!";
	}

}
