<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.sap.era.service.*,org.sap.era.persistence.*,
	org.springframework.context.ApplicationContext,
	org.springframework.context.support.ClassPathXmlApplicationContext,
	java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script language="javascript">
function topicReload(path){
	window.parent.frames["leftFrame"].location.href = path;
	//window.parent.frames["leftFrame"].location.reload();
}  

function logout(){
	window.parent.location.href = '<%=request.getContextPath() %>/pages/Login.jsp?loginFlag=logout';
}
</script> 
<body>
<%
Person person = (Person)session.getAttribute("currentUser");
if(person!=null){
%>
<table width="100%" height="100%" border="0">
  <tr>
    <td width="20%" rowspan="2">&nbsp;E-Report Analytics System</td>
    <td width="50%">&nbsp;</td>
    <td width="30%"><div align="right"><%=person.getLastName()+", "+person.getFirstName()+"("+ person.getOrgnazition().getName() +")" %> &nbsp;&nbsp;|&nbsp;&nbsp; <a href="JavaScript:logout()">Logout</a>&nbsp;&nbsp;</div></td>
  </tr>
  <tr>
    <td colspan="2">
    	<a href="JavaScript:topicReload('<%=request.getContextPath() %>/pages/IndexLeft.jsp?tab=1')">Admin</a>&nbsp;&nbsp;|&nbsp;&nbsp;
    	<a href="JavaScript:topicReload('<%=request.getContextPath() %>/pages/IndexLeft.jsp?tab=2')">Reporting</a>&nbsp;&nbsp;|&nbsp;&nbsp;
    	<a href="JavaScript:topicReload('<%=request.getContextPath() %>/pages/IndexLeft.jsp?tab=3')">Management&amp;Analytics</a> &nbsp;&nbsp;|&nbsp;&nbsp;
    	<a href="JavaScript:topicReload('<%=request.getContextPath() %>/pages/IndexLeft.jsp?tab=4')">Report Admin</a></td>
  </tr>
</table>
<%
}
else{
	%>
	<script language="javascript">
	window.parent.location.href = '<%=request.getContextPath() %>/pages/Login.jsp?loginFlag=logout';
	</script> 
	<%	
}
%>
</body>
</html>