<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>E-Report Collection & Analytics</title>
<script src="resources/sap-ui-core.js" id="sap-ui-bootstrap"
	data-sap-ui-libs="sap.ui.commons,sap.ui.table,sap.ui.ux3,sap.ui.core"
	data-sap-ui-theme="sap_goldreflection">
    
</script>
<!-- add sap.ui.table,sap.ui.ux3 and/or other libraries to 'data-sap-ui-libs' if required -->
<style type="text/css">
.alignCenter {
	margin-left: auto;
	margin-right: auto;
	margin-top: 100px;
}

#loginForm .sapUiGrid .sapUiGridLabel {
	text-align: left;
	width: 150px;
}

.loginSubmit {
	margin-top: 5px;
}

.sapUiTableColCell {
	text-align: left;
}

.loginLabel {
	font-size: 16pt;
}

.alignRight {
	float: right;
}
</style>
<script>
    sap.ui.localResources("ereportanalyticsnew");
    //
    var model = new sap.ui.model.json.JSONModel();
    jQuery.ajax({
		url : "getLoginPerson.do",
		type : 'GET',
		dataType : "json",
		success : function(data) {
		    var obj = data;
		    //alert(obj);
		    console.log(obj);
		    if (obj != null) {
				model.setData(obj);
				sap.ui.getCore().setModel(model, "loginPerson");
				var index = sap.ui.view({
				    viewName : "ereportanalyticsnew.index",
				    type : sap.ui.core.mvc.ViewType.JS
				});
				index.placeAt("content");
		    } else {
				var index = sap.ui.view({
				    viewName : "ereportanalyticsnew.login",
				    type : sap.ui.core.mvc.ViewType.JS
				});
				index.placeAt("content");
		    }
		},
		error : function(jqXHR, textStatus, errorThrown) {
		    sap.ui.commons.MessageBox.alert("Failed to retrieve data: " + textStatus + "\n" + errorThrown);
		}
    });
</script>

</head>
<body class="sapUiBody" role="application">
	<div id="content"></div>
</body>
</html>