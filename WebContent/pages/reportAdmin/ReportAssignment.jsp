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
	window.location.href = '<%=request.getContextPath() %>/reportAdmin/toAddReportAssignment.do'
}
function reloadPage(){
	window.location.reload(false);
}
function toEdit(ID){
	window.location.href = '<%=request.getContextPath() %>/pages/reportAdmin/EditReportAssignment.jsp?groupID='+ID;
}
</script>
<body>
<%
TableGroupAssignmentService tableGroupAssignmentService;
ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "beans.xml" });
tableGroupAssignmentService = context.getBean("tableGroupAssignmentService", TableGroupAssignmentService.class);
List<TableGroupAssignment> list = tableGroupAssignmentService.getAllTableGroupAssignments();

%>
<input type="button" name = "button2" id="button2" onclick="javaScript:toAdd()" value="Add Report Assignment" />
<input type="button" name = "button2" id="button2" onclick="javaScript:reloadPage()" value="Refresh" />
<table width="100%" border="1" >
      <tr>
        <th colspan="6"><%=list.size()%> Entries in the Database</th>
      </tr>
      <tr>
        <th width="10%">ID</th>
        <th width="20%">Name</th>
        <th width="20%">Description</th>
        <th width="20%">Assigned Orgnazitions</th>
        <th width="10%">Status</th>
        <th width="20%">&nbsp;</th>
      </tr>
      <%
		for(int i = 0 ; i < list.size(); i++){
			TableGroupAssignment tga = list.get(i);
			
      %>
      <tr>
        <td><%=tga.getId()%></td>
        <td><a><%=tga.getName() %></a></td>
        <td><%=tga.getDescription() %></td>
        <td><%=tga.getAssignedOrgnazition().size()%></td>
        <td><%=0%></td>
        <td>
        <input type="button" name="Delete" id="button" value="Delete" />
        <input type="button" name="Edit" id="button" value="Edit" onclick="javaScript:toEdit(<%=tga.getId()%>)"/>
        <input type="button" name="GenerateTask" id="button" value="Generate Task" onclick="javaScript:GenerateTask(<%=tga.getId()%>)"/>
        </td>
      </tr>
	<%
		}
	%>
    </table>
</body>
</html>