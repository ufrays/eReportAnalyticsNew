package org.sap.era.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sap.era.dto.TableGroupModelDTO;
import org.sap.era.persistence.TableGroupModel;
import org.sap.era.persistence.TableModel;
import org.sap.era.service.ExcelReadService;
import org.sap.era.service.TableGroupModelService;
import org.sap.era.service.TableModelService;
import org.sap.era.service.excel.CmisHelper;
import org.sap.era.service.excel.ExcelForm;
import org.sap.era.service.excel.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class ReportTemplateController {

	@Autowired
	@Resource
	private TableGroupModelService tableGroupModelService;

	@Autowired
	@Resource
	private TableModelService tableModelService;

	private static final int BUFFER_SIZE = 4096;

	public ReportTemplateController() {
		// ApplicationContext context = new ClassPathXmlApplicationContext(new
		// String[] { "beans.xml" });
		// tableGroupModelService = context.getBean("tableGroupModelService",
		// TableGroupModelService.class);
	}

	@RequestMapping(value = "/uploadReportTemplate.do", method = RequestMethod.POST)
	@ResponseBody
	public String uploadReportTemplate(HttpServletRequest request) throws IOException {

		TableGroupModel tgm = new TableGroupModel();
		String realPathOfApp = request.getServletContext().getRealPath("");
		//
		CmisHelper cmis = new CmisHelper();
		tgm.setName("name");
		File file = cmis.uploadDocument(realPathOfApp, request);
		cmis.deleteDocumentByName(file.getName());
		Document doc = cmis.addDocument(file);
		file.delete();
		tgm.setModelPath(doc.getId());
		return doc.getId();
	}

	@RequestMapping(value = "/saveReportTemplate", method = RequestMethod.GET)
	@ResponseBody
	public String saveReportTemplate(TableGroupModel tgm) throws IOException, ParseException {
		CmisHelper cmis = new CmisHelper();
		List<ExcelForm> listExcelForm;
		String docID = tgm.getModelPath();
		Document doc = cmis.getDocumentById(docID);
		InputStream is = doc.getContentStream().getStream();
		//
		XSSFWorkbook wb = new XSSFWorkbook(is);
		listExcelForm = ExcelReadService.getAllExcelForm(wb);
		tgm.setModelPath(doc.getId());
		tgm.setCreatedBy(1);
		tgm.setCreatedOn(new java.util.Date());

		// tgm.setTableModel(tableModel)
		if (tgm.getId() > 0) {
			TableGroupModel tgm_edit = tableGroupModelService.getTableGroupModelByID(tgm.getId());
			tgm_edit.setName(tgm.getName());
			tgm_edit.setDescription(tgm.getDescription());
			tgm_edit.setCategory(tgm.getCategory());
			tableGroupModelService.updateTableGroupModel(tgm_edit);
		} else {
			tableGroupModelService.addTableGroupModel(tgm, listExcelForm);
		}

		return "The report template was saved!";
	}

	@RequestMapping(value = "/deleteReportTemplate", method = RequestMethod.GET)
	@ResponseBody
	public String deleteReportTemplate(String groupID) {
		tableGroupModelService.deleteTableGroupModel(Long.parseLong(groupID));
		return "The report template was deleted!";
	}

	@RequestMapping(value = "/releaseReportTemplate", method = RequestMethod.GET)
	@ResponseBody
	public String releaseReportTemplate(String groupID) {
		TableGroupModel tgm_release = tableGroupModelService.getTableGroupModelByID(Long.parseLong(groupID));
		tgm_release.setStatus("Released");
		tableGroupModelService.updateTableGroupModel(tgm_release);
		return "The report template was released!";
	}

	@RequestMapping(value = "/getReportTemplateList", method = RequestMethod.GET)
	@ResponseBody
	public List<TableGroupModelDTO> getReportTemplateList() {
		List<TableGroupModelDTO> list = tableGroupModelService.getAllTableGroupModelDTO();
		return list;

	}

	// getReportTemplateByID reportID
	@RequestMapping(value = "/getReportTemplateByID", method = RequestMethod.GET)
	@ResponseBody
	public TableGroupModel getReportTemplateByID(String groupID) {
		TableGroupModel tableGroupModel = tableGroupModelService.getTableGroupModelByID(Long.parseLong(groupID));
		return tableGroupModel;

	}

	// getTableModelByGroupID GroupID
	@RequestMapping(value = "/getTableModelByGroupID", method = RequestMethod.GET)
	@ResponseBody
	public List<TableModel> getTableModelByGroupID(String groupID) {
		List<TableModel> list = tableModelService.getAllTableModelsByTableGroupModel(groupID);
		return list;

	}

	// getTableModelByGroupID GroupID
	@RequestMapping(value = "/getTableGroupModelByModelIDTest", method = RequestMethod.GET)
	@ResponseBody
	public TableGroupModel getTableGroupModelByModelIDTest(String groupID) {
		List<TableModel> list = tableModelService.getAllTableModelsByTableGroupModel(groupID);
		return list.get(0).getTableGroupModel();

	}

	// Delete All Documents
	@RequestMapping(value = "/deleteAllDocument", method = RequestMethod.GET)
	@ResponseBody
	public String deleteAllDocument() {
		CmisHelper cmis = new CmisHelper();
		cmis.deleteAllDocument();
		return "Deleted All Documents!";

	}

	@RequestMapping(value = "/download/TemplateFile", method = RequestMethod.GET)
	public void downloadTemplateFile(String docID, HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get absolute path of the application
		ServletContext context = request.getServletContext();
		String appPath = context.getRealPath("");
		System.out.println("appPath = " + appPath);

		// construct the complete absolute path of the file
		CmisHelper cmis = new CmisHelper();
		Document doc = cmis.getDocumentById(docID);
		FileInputStream inputStream = (FileInputStream) doc.getContentStream().getStream();

		// get MIME type of the file
		String mimeType = doc.getContentStreamMimeType();
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) doc.getContentStreamLength());

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", doc.getName());
		response.setHeader(headerKey, headerValue);

		// get output stream of the response
		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();
	}
}
