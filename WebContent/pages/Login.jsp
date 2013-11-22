<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.sap.era.service.*,org.sap.era.persistence.*,
	org.springframework.context.ApplicationContext,
	org.springframework.context.support.ClassPathXmlApplicationContext,
	java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<style type="text/css">
<!--
.style1 {font-size: 24px}
-->
</style>
</head>

<body>
<%
String userName = request.getParameter("userName");
String password = request.getParameter("password");
PersonService personService;
ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "beans.xml" });
personService = context.getBean("personService", PersonService.class);
String loginFlag = request.getParameter("loginFlag");
if (loginFlag != null && loginFlag.equals("logout")){
	session.setAttribute("currentUser", null);
}
loginFlag = "";
if (userName== null || userName.equals("")){
	
}else{
	List<Person> list = personService.getPersonsByUserName(userName);
	Person person;
	if(list.size()>0){
		person = list.get(0);
		if (person.getPassword().equals(password)){
			loginFlag = "success";
			session.setAttribute("currentUser", person);
		}
		else
		{
			loginFlag = "failed";
		}
	}else{
		loginFlag = "noUser";
	}
}
%>
<table width="100%" border="0">
  <tr>
    <td width="35%" height="182">&nbsp;</td>
    <td width="30%">&nbsp;</td>
    <td width="35%">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>
    <form name="login" action="">
    <table width="400px" border="1" bordercolor="#999999" bgcolor="#CCCCCC">
      <tr>
        <td height="74" colspan="2"><span class="style1">E-Report Analytics System</span></td>
        </tr>
      <tr>
        <td width="24%">User Name:</td>
        <td width="76%"><input type="text" name="userName" id="textfield" /></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td>
        	<input type="password" name="password" id="textfield2" />

        </td>
      </tr>
      <tr>
        <td colspan="2">
        <div align="left">
         <%
        	if (loginFlag.equals("failed")){
        		%>
        		<a style="color:red">Incorrect password, please input again!</a>
        		<%
        	}
        	if (loginFlag.equals("noUser")){
        		%>
        		<a style="color:red">Incorrect user name, please input again!</a>
        		<%
        	}
        	%>
        	</div>
          <div align="center">
            <input type="submit" name="button" id="button" value="Login" />
          </div></td>
        </tr>
    </table>
    </form>
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="205">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
<%
if (loginFlag.equals("success")){
	%>
	<script language="javascript">
		window.location.href = '<%=request.getContextPath() %>/pages/Index.jsp';
	</script>
<%
}
%>
</html>