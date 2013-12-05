<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>E-Report Collection & Analytics</title>
		<script src="resources/sap-ui-core.js"
				id="sap-ui-bootstrap"
				data-sap-ui-libs="sap.ui.commons,sap.ui.table,sap.ui.ux3,sap.ui.core"
				data-sap-ui-theme="sap_goldreflection">
		</script>
		<!-- add sap.ui.table,sap.ui.ux3 and/or other libraries to 'data-sap-ui-libs' if required -->

		<script>
				sap.ui.localResources("ereportanalyticsnew");
				var view = sap.ui.view({id:"idindex1", viewName:"ereportanalyticsnew.index", type:sap.ui.core.mvc.ViewType.JS});
				view.placeAt("content");
		</script>

	</head> 
	<body class="sapUiBody" role="application">
		<div id="content"></div>
	</body>
</html>