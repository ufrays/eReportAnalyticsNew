<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="org.sap.era.service.*,org.sap.era.persistence.*,org.sap.era.service.excel.*,java.util.Map,
	org.springframework.context.ApplicationContext,
	org.springframework.context.support.ClassPathXmlApplicationContext,org.apache.chemistry.opencmis.client.api.Document,
	java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function backToList(){
		window.location.href = '<%=request.getContextPath()%>/pages/reportAdmin/ReportTemplate.jsp';
	}
	function submitQuery() {
		document.forms[0].submit();
	}
</script>
<body>
	<%
		TableGroupModelService tableGroupModelService;
		TableModelService tableModelService;
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "beans.xml" });
		tableGroupModelService = context.getBean("tableGroupModelService", TableGroupModelService.class);
		tableModelService = context.getBean("tableModelService", TableModelService.class);
		String groupID = request.getParameter("groupID");
		String tableModelID = request.getParameter("tableModelID");
		TableGroupModel tableGroupModel;
		if (groupID != null && !groupID.equals("")) {
			//
			tableGroupModel = tableGroupModelService.getTableGroupModelByID(groupID);
			List<ExcelForm> excelForms = tableModelService.convertTableModles2ExcelModels(tableGroupModel.getTableModel());
			//ExcelForm excelForm = new ExcelForm();
			int selectedExcelForm = 0;
	%>
	<h1><%=tableGroupModel.getName()%></h1>
	<form name="query" action="">
		<input type="hidden" name="groupID" id="groupID" value=<%=groupID%> />
		Select Sheet: <select onchange="javaScript:submitQuery()"
			name="tableModelID" id="select">
			<option value="" selected="selected">select...</option>
			<%
				for (int i = 0; i < excelForms.size(); i++) {
						ExcelForm excelForm = excelForms.get(i);
						if (i == 0 && tableModelID == null) {
			%>
			<option selected="selected" value="<%=excelForm.getTableModelID()%>"><%=excelForm.getSheetName()%></option>
			<%
				} else if (tableModelID != null && excelForm.getTableModelID() == Long.parseLong(tableModelID)) {
							selectedExcelForm = i;
			%>
			<option selected="selected" value="<%=excelForm.getTableModelID()%>"><%=excelForm.getSheetName()%></option>
			<%
				} else {
			%>
			<option value="<%=excelForm.getTableModelID()%>"><%=excelForm.getSheetName()%></option>
			<%
				}

					}
			%>
		</select>
	</form>
	<table  border="1">
		<%
			ExcelForm excelForm = excelForms.get(selectedExcelForm);
				Map<String, Control> controlMap = excelForm.getCells();
				int in = 0;
				int rowCount = excelForm.getRows();
				int colCount = excelForm.getCols();

				//loop row
				for (int i = 1; i <= rowCount; i++) {
		%>
		<tr height="20">
			<%
				//loop columns of the row
						for (int j = 1; j <= colCount; j++) {
							Control cell = controlMap
									.get(String.valueOf(excelForm.getStartRow() + i) + "-" + String.valueOf(excelForm.getStartCol() + j));

							if (cell != null) {
								int cellType = cell.getCellType();
								if (cellType == CellModel.CELL_TYPE_LABEL || cellType == CellModel.CELL_TYPE_HIDDEN) {
			%>
			        <td colspan="<%=cell.getColspan()%>" rowspan="<%=cell.getRowspan()%>"><%=cell.getText()%></td>
			<%
				} else if (cellType == CellModel.CELL_TYPE_DATA) {
			%>
			                <td bgcolor="#FFFFCC" colspan="<%=cell.getColspan()%>" rowspan="<%=cell.getRowspan()%>">&nbsp;</td>
			<%
				}else{
					%>
	                <td colspan="<%=cell.getColspan()%>" rowspan="<%=cell.getRowspan()%>">&nbsp;</td>
					<%
				}
							}
						}
			%>
		</tr>
		<%
			}
			}
		%>
	</table>
	<input type="button" name="button2" id="button2"
		onclick="javaScript:backToList()" value="Back" />
</body>
</html>