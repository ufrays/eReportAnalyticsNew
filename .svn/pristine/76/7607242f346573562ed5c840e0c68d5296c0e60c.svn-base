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
personService = context.getBean("personService", PersonService.class);

String parentOrgID = request.getParameter("parentOrgID");
List<Person> resultPersonListByOrgID;
List<Orgnazition> resultListAll= orgnazitionService.getAllOrgnazitions();
Orgnazition currentOrg = new Orgnazition();
String parentOrgName = "";

if (parentOrgID!=null && !parentOrgID.equals("")){
	resultPersonListByOrgID= personService.getPersonsByOrgID(parentOrgID);
	currentOrg = orgnazitionService.getOrgnazitionByID(parentOrgID);
	parentOrgName = currentOrg.getName();
}else{
	resultPersonListByOrgID= personService.getAllPersons();
}

String parm;
parm = request.getParameter("saveOrg");
Person person = new Person();
if (parm!=null && parm.equals("save")){
	Orgnazition pOrg = new Orgnazition();
	pOrg.setId(Long.parseLong(parentOrgID));
	pOrg.setName(parentOrgName);
	person.setName(request.getParameter("personName"));
	person.setOrgnazition(pOrg);
	person.setPassword("Welcome1");
	personService.addPerson(person);
}
%>
<form id="org" name="orgnazitonForm"action="" method="post">
<table width="100%%" border="0">
  
  <tr>
    <td width="26%">&nbsp;</td>
    <td width="74%">Orgnazition:<%=parentOrgName%>&nbsp;&nbsp;&nbsp;&nbsp; User Name:
      <input type="text" name="orgName" id="orgName" />
      <input type="hidden" name="parentOrg" id ="parentOrg" value="<%=parentOrgID%>"/>
      <input type="hidden" name="saveOrg" id ="saveOrg" value=""/>
    <input type="button" name = "button2" id="button2" onclick="javaScript:submitSave()" value="Add Business User " /></td>
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
    		 Orgnazition org2 = resultListAll.get(i);
    		 List<Person> person2 = org2.getPerson();
    		 int tem2 = person2.size();
     %>
      <tr>
        <td><a href= "<%=request.getContextPath() +"/pages/admin/BusinessUser.jsp?parentOrgID="+resultListAll.get(i).getId() %>"><%=prefix+ resultListAll.get(i).getName()+"("+ String.valueOf(tem2) +")" %></a></td>
      </tr>
      <%
    	 	}
    	 }
      %>
    </table></td>
    <td><table width="100%" border="1" >
      <tr>
        <th colspan="5"><%=resultPersonListByOrgID.size() %> Entries in the Database</th>
      </tr>
      <tr>
        <th width="5%">ID</th>
        <th width="20%">Orgnazition</th>
        <th width="40%">User Name</th>
        <th width="20%">Password</th>
        <th width="15%">&nbsp;</th>
      </tr>
      <%
      if(resultPersonListByOrgID.size()>0){
    	  Person personTemp = null;
    	  for(int i = 0; i < resultPersonListByOrgID.size(); i++ ){
    		  personTemp = resultPersonListByOrgID.get(i);
      %>
      <tr>
        <td><%=personTemp.getId() %></td>
        <td><%=personTemp.getOrgnazition().getName() %></td>
        <td><%=personTemp.getName() %></td>
        <td><%=personTemp.getPassword() %></td>
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
