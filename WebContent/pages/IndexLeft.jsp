<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- TemplateBeginEditable name="doctitle" -->
<title></title>
<!-- TemplateEndEditable -->
<!-- TemplateBeginEditable name="head" -->
<!-- TemplateEndEditable -->
</head>
<script language="javascript">
function letfReload(path){
	window.parent.frames["mainFrame"].location.href = path;
	//window.parent.frames["mainFrame"].location.reload();  
}  
</script> 
<body>
<p>
<%
String tab = request.getParameter("tab");
if(tab==null || tab.equals("1")){
%>
<li><a href="JavaScript:letfReload('<%=request.getContextPath() %>/pages/admin/OrgStructure.jsp')">Orgnazition Structure</a></li>
<li><a href="JavaScript:letfReload('<%=request.getContextPath() %>/pages/admin/BusinessUser.jsp')">Business User</a></li>
<%
}
if(tab!=null && tab.equals("2")){
%>
<li><a href="JavaScript:letfReload('<%=request.getContextPath() %>/pages/admin/#')">Pending Reports</a></li>
<li><a href="JavaScript:letfReload('<%=request.getContextPath() %>/pages/admin/#')">Finished Reports</a></li>
<%
}
if(tab!=null && tab.equals("3")){
%>
<li><a href="JavaScript:letfReload('<%=request.getContextPath() %>/pages/admin/#')">Report Management</a></li>
<li><a href="JavaScript:letfReload('<%=request.getContextPath() %>/pages/admin/#')">Report Analytics</a></li>
<li><a href="JavaScript:letfReload('<%=request.getContextPath() %>/pages/admin/#')">Custom Analytics</a></li>
<%
}
if(tab!=null && tab.equals("4")){
%>
<li><a href="JavaScript:letfReload('<%=request.getContextPath() %>/pages/reportAdmin/ReportTemplate.jsp')">Report Template</a></li>
<li><a href="JavaScript:letfReload('<%=request.getContextPath() %>/pages/reportAdmin/ReportAssignment.jsp')">Report Assignment</a></li>
<%
}
%>

</p>
</body>
</html>
