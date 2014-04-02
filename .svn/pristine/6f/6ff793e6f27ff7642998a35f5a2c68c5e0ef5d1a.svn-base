<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.sap.era.service.*,org.sap.era.persistence.*,
	org.springframework.context.ApplicationContext,
	org.springframework.context.support.ClassPathXmlApplicationContext,
	java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- TemplateBeginEditable name="doctitle" -->
<title></title>
<!-- TemplateEndEditable -->
<!-- TemplateBeginEditable name="head" -->
<!-- TemplateEndEditable -->
<script type="text/javascript">
function submitSave(){
	if (document.getElementById("parentOrg").value ==null){
		alert("please select the parent orgnazition!");
		return;
	}
	document.getElementById("saveOrg").value = "save";
	document.orgnazitonForm.submit(); 
	
}
</script>
</head>

<body>
<%
PersonService personService;
OrgnazitionService orgnazitionService;
ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "beans.xml" });
orgnazitionService = context.getBean("orgnazitionService", OrgnazitionService.class);

String parentOrgID = request.getParameter("parentOrgID");
List<Orgnazition> resultListByParent;
List<Orgnazition> resultListAll= orgnazitionService.getAllOrgnazitions();
Orgnazition currentOrg = new Orgnazition();
String parentOrgName = "";

if (parentOrgID!=null && !parentOrgID.equals("")){
	resultListByParent= orgnazitionService.getOrgnazitionsByParentID(parentOrgID);
	currentOrg = orgnazitionService.getOrgnazitionByID(parentOrgID);
	parentOrgName = currentOrg.getName();
}else{
	resultListByParent = resultListAll;
}

String parm;
parm = request.getParameter("saveOrg");
Orgnazition orgnazition = new Orgnazition();
if (parm!=null && parm.equals("save")){
	Orgnazition pOrg = new Orgnazition();
	pOrg.setId(Long.parseLong(parentOrgID));
	pOrg.setName(parentOrgName);
	orgnazition.setName(request.getParameter("orgName"));
	orgnazition.setParentOrgnazition(pOrg);
	orgnazition.setOrgnazitionLevel(currentOrg.getOrgnazitionLevel() + 1);
	orgnazitionService.addOrgnazition(orgnazition);
}
%>
<form id="org" name="orgnazitonForm"action="" method="post">
<table width="100%%" border="0">
  
  <tr>
    <td width="26%">&nbsp;</td>
    <td width="74%">Parent Orgnazition:<%=parentOrgName%>&nbsp;&nbsp;&nbsp;&nbsp; Orgnazition Name:
      <input type="text" name="orgName" id="orgName" />
      <input type="hidden" name="parentOrg" id ="parentOrg" value="<%=parentOrgID%>"/>
      <input type="hidden" name="saveOrg" id ="saveOrg" value=""/>
    <input type="button" name = "button2" id="button2" onclick="javaScript:submitSave()" value="Add Orgnazition " /></td>
  </tr>

  <tr>
     <td rowspan="2" valign="top"><table width="100%%" border="0">
     <%
     if(resultListAll.size()>0){
    	 for(int i = 0 ; i < resultListAll.size() ; i++){
    		 String prefix = "";
    		 for (int j = 0 ; j <  resultListAll.get(i).getOrgnazitionLevel(); j++ ){
    			 prefix = prefix + "--";
    		 }
    		 
     %>
      <tr>
        <td><a href= "<%=request.getContextPath() +"/pages/admin/OrgStructure.jsp?parentOrgID="+resultListAll.get(i).getId() %>"><%=prefix+ resultListAll.get(i).getName() %></a></td>
      </tr>
      <%
    	 	}
    	 }
      %>
    </table></td>
    <td><table width="100%" border="1" >
      <tr>
        <th colspan="5"><%=resultListByParent.size() %> Entries in the Database</th>
      </tr>
      <tr>
        <th width="5%">ID</th>
        <th width="20%">Parent Orgnazition</th>
        <th width="40%">Name</th>
        <th width="20%">Report Derict</th>
        <th width="15%">&nbsp;</th>
      </tr>
      <%
      if(resultListByParent.size()>0){
    	  Orgnazition org = null;
    	  for(int i = 0; i < resultListByParent.size(); i++ ){
    		  org = resultListByParent.get(i);
      %>
      <tr>
        <td><%=org.getId() %></td>
        <td><%=org.getParentName() %></td>
        <td><%=org.getName() %></td>
        <td><%=org.getReportDirect() %></td>
        <td><input type="submit" name="button" id="button" value="Delete" /></td>
      </tr>
	<%
      	}
      }
	%>
    </table>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
 </form>

</body>
</html>
