<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.sap.era.service.*,org.sap.era.persistence.*,org.sap.era.service.excel.*,
	org.springframework.context.ApplicationContext,
	org.springframework.context.support.ClassPathXmlApplicationContext,org.apache.chemistry.opencmis.client.api.Document,
	java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function toAdd(){
	window.location.href = '<%=request.getContextPath() %>/pages/reportAdmin/AddReportTemplate.jsp'
}
function reloadPage(){
	window.location.reload(false);
}
function toPreview(groupID){
	window.location.href = '<%=request.getContextPath() %>/pages/reportAdmin/ReportTemplatePreview.jsp?groupID='+groupID;
}
</script>
<body>
<%
CmisHelper cmisHelper = CmisHelper.retrieveCmisHelperClass(request);
TableGroupModelService tableGroupModelService;
ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "beans.xml" });
tableGroupModelService = context.getBean("tableGroupModelService", TableGroupModelService.class);
List<TableGroupModel> tableGroupList = tableGroupModelService.getAllTableGroupModels();
%>
<input type="button" name = "button2" id="button2" onclick="javaScript:toAdd()" value="Add Report Template" />
<input type="button" name = "button2" id="button2" onclick="javaScript:reloadPage()" value="Refresh" />
<table width="100%" border="1" >
      <tr>
        <th colspan="6"><%=tableGroupList.size()%> Entries in the Database</th>
      </tr>
      <tr>
        <th width="10%">ID</th>
        <th width="20%">Report Name</th>
        <th width="20%">Created By</th>
        <th width="20%">Created Date</th>
        <th width="10%">Status</th>
        <th width="20%">&nbsp;</th>
      </tr>
      <%
	if(tableGroupList.size()>0){
		TableGroupModel tableGroup;
		Document doc = null;
		for(int i = 0; i < tableGroupList.size(); i++){
			tableGroup = tableGroupList.get(i);
			doc = cmisHelper.getDocumentById(tableGroup.getModelPath());
			MyDocsDTO docDTO = cmisHelper.convertCmisDocToMyDocsDTO(doc);
      %>
      <tr>
        <td><%=tableGroup.getId()%></td>
        <td><a href='<%=request.getContextPath()+"/FileDownloadServlet"+docDTO.getDownloadLink() %>'><%=tableGroup.getName() %></a></td>
        <td><%=tableGroup.getCreatedBy()%></td>
        <td><%=tableGroup.getCreatedOn()%></td>
        <td><%=tableGroup.getFlag() %></td>
        <td>
        <input type="submit" name="Delete" id="button" value="Delete" />
        <input type="button" name="Preview" id="button" value="Preview" onclick="javaScript:toPreview(<%=tableGroup.getId()%>)"/>
        </td>
      </tr>
	<%
		}
	}
	%>
    </table>
</body>
</html>