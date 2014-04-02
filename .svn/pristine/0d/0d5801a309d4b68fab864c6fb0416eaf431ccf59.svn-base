<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.sap.era.service.*,org.sap.era.persistence.*,org.sap.era.service.excel.*,
	org.springframework.context.ApplicationContext,java.io.InputStream,java.io.FileInputStream,org.apache.poi.xssf.usermodel.XSSFWorkbook,
	org.springframework.context.support.ClassPathXmlApplicationContext,org.apache.chemistry.opencmis.client.api.Document,
	java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function backToList(){
		window.location.href = '<%=request.getContextPath() %>/pages/reportAdmin/ReportAssignment.jsp'
	}
</script>
<body>
<%
OrgnazitionService orgnazitionService;
ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "beans.xml" });
orgnazitionService = context.getBean("orgnazitionService", OrgnazitionService.class);
TableGroupModelService tableGroupModelService;
tableGroupModelService = context.getBean("tableGroupModelService", TableGroupModelService.class);
List<Orgnazition> orgListAll= orgnazitionService.getAllOrgnazitions();
List<TableGroupModel> tableGroupModelList = tableGroupModelService.getAllTableGroupModels();

%>
 <form:form commandName="add" name="add" action="/reportAdmin/addReportAssignment.do">
<table width="100%" border="1">
  <tr>
    <td colspan="2">Add Report Assignment:</td>
  </tr>
  <tr>
    <td width="20%">Name:</td>
    <td width="80%">
    
    </td>
  </tr>
  <tr>
    <td width="20%">Report Template:</td>
    <td width="80%">
    	<select name="reportTemplate" id="select">
    	    <%
			    for(int i =0 ;i<tableGroupModelList.size();i++){
		    %>
	      		<option value="<%=tableGroupModelList.get(i).getId()%>"><%=tableGroupModelList.get(i).getName()%></option>
		    <%
		   		}
		    %>
		</select>
    </td>
  </tr>
  <tr>
    <td>Description:</td>
    <td>
    <textarea name="description" id="textarea" cols="45" rows="5"></textarea>
    </td>
  </tr>
  <tr>
    <td>Duration Mode:</td>
    <td>
	    <select name="durationMode" id="select">
	      <option value=""></option>
		  <option value="YEAR">YEAR</option>
		  <option value="QUARTER">QUARTER</option>
		  <option value="MONTH">MONTH</option>
		  <option value="TENDAYS">TENDAYS</option>
		</select>
    </td>
  </tr>
    <tr>
    <td>Select Orgnazition:</td>
    <td>
	    <table width="25%" border="0">
		  <tr>
		    <td width="19%" rowspan="4"><select name="select" style="width:300px" size="30" multiple="multiple" id="select">
		         <%
			     if(orgListAll.size()>0){
			    	 for(int i = 0 ; i < orgListAll.size() ; i++){
			    		 String prefix = "";
			    		 for (int j = 0 ; j <  orgListAll.get(i).getOrgnazitionLevel(); j++ ){
			    			 prefix = prefix + "-";
			    		 }
			    		 
			     %>
		      			<option value="<%=orgListAll.get(i).getId()%>"><%=prefix+ orgListAll.get(i).getName() %></option>
		           <%
			    	 	}
			    	 }
			      %>
		    </select></td>
		    <td width="5%" height="184">&nbsp;</td>
		    <td width="76%" rowspan="4"><select name="select2" style="width:300px" size="30" multiple="multiple" id="select2">
		      
		    </select></td>
		  </tr>
		  <tr>
		    <td height="28"><div align="center">
		      <input type="button" name="button" id="button" value="&gt;&gt;" />
		    </div></td>
		  </tr>
		  <tr>
		    <td height="26"><div align="center">
		      <input type="button" name="button2" id="button2" value="&lt;&lt;" />
		    </div></td>
		  </tr>
		  <tr>
		    <td height="21">&nbsp;</td>
		  </tr>
		</table>
    </td>
  </tr>
  <tr>
    <td colspan="2"><input type="submit" name="button" id="button" value="Save" />
    <input type="button" name="button2" id="button2" onclick="javaScript:backToList()" value="Back" /></td>
  </tr>
</table>
</form:form >
</body>
</html>