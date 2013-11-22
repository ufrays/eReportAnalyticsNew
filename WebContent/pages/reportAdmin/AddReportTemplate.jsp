<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.sap.era.service.*,org.sap.era.persistence.*,org.sap.era.service.excel.*,
	org.springframework.context.ApplicationContext,java.io.InputStream,java.io.FileInputStream,org.apache.poi.xssf.usermodel.XSSFWorkbook,
	org.springframework.context.support.ClassPathXmlApplicationContext,org.apache.chemistry.opencmis.client.api.Document,
	java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function backToList(){
		window.location.href = '<%=request.getContextPath() %>/pages/reportAdmin/ReportTemplate.jsp'
	}
</script>
<body>
<%
//DocumentService documentService = new DocumentService();
CmisHelper cmisHelper = CmisHelper.retrieveCmisHelperClass(request);
TableGroupModelService tableGroupModelService;
ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "beans.xml" });
tableGroupModelService = context.getBean("tableGroupModelService", TableGroupModelService.class);
String name = request.getParameter("name");
String filePath = request.getParameter("filePath");
Person currentUser = (Person) session.getAttribute("currentUser");

if(name != null && !name.equals("")  && filePath != null && !filePath.equals("")){
	List<ExcelForm> listExcelForm;
	java.io.File tempFile = new java.io.File(filePath);
	XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));
	listExcelForm = ExcelReadService.getAllExcelForm(wb);
	
    String fileName = tempFile.getName();   
	TableGroupModel tgm = new TableGroupModel();
	tgm.setName(name);
	
	Document doc = cmisHelper.addDocument(tempFile);
	tgm.setModelPath(doc.getId());
	tgm.setCreatedBy( (int)currentUser.getId());
	tgm.setCreatedOn(new java.util.Date());
	tgm.setFlag(0);
	//tgm.setTableModel(tableModel)
	tableGroupModelService.addTableGroupModel(tgm,listExcelForm);
}
%>
<form name="add" action="">
<table width="100%" border="1">
  <tr>
    <td colspan="2">Add Report Template:</td>
  </tr>
  <tr>
    <td width="20%">Template Name:</td>
    <td width="80%"><input type="text" name="name" id="textfield" /></td>
  </tr>
  <tr>
    <td>Template File:</td>
    <td><input type="file" name="filePath" id="fileField" /></td>
  </tr>
  <tr>
    <td>Created By:</td>
    <td><%=((Person)session.getAttribute("currentUser")).getName()%><input type="hidden" name="textfield3" id="textfield3" /></td>
  </tr>
  <tr>
    <td colspan="2"><input type="submit" name="button" id="button" value="Save" />
    <input type="button" name="button2" id="button2" onclick="javaScript:backToList()" value="Back" /></td>
  </tr>
</table>
</form>
</body>
</html>