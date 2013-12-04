sap.ui.controller("ereportanalyticsnew.addReportTemplate", {

    /**
     * Called when a controller is instantiated and its View controls (if
     * available) are already created. Can be used to modify the View before it
     * is displayed, to bind event handlers and do other one-time
     * initialization.
     * 
     * @memberOf ereportanalyticsnew.addReportTemplate
     */
    onInit : function() {
	var model = new sap.ui.model.json.JSONModel();
	this.getView().setModel(model);
	var oModel = this.getView().getModel();
	oModel.getData().newTemplate = {
	    "status" : "New",
	    "flag" : 0
	};

    },

    onEditOrViewInit : function(editOrView, reportID) {
	var model = new sap.ui.model.json.JSONModel();
	this.getView().setModel(model);
	var oModel = this.getView().getModel();
	jQuery
		.ajax({
		    url : "getReportTemplateByID.do?groupID=" + reportID,
		    type : 'GET',
		    dataType : "json",
		    success : function(data) {

			console.log("data:" + data);
			console.log("newTemplate-data:"
				+ oModel.getData().newTemplate);
			// oModel.getData().newTemplate = data;
			oModel.setData({
			    newTemplate : data
			});
			console.log("newTemplate-data2:"
				+ oModel.getData().newTemplate);
		    },
		    error : function(jqXHR, textStatus, errorThrown) {
			// TODO improve error handling
			sap.ui.commons.MessageBox
				.alert("Failed to retrieve data: " + textStatus
					+ "\n" + errorThrown);
		    }
		});
	if (editOrView == "New") {
	    sap.ui.getCore().getControl("addReportTemplate.flag").setEnabled(
		    false);
	    sap.ui.getCore().getControl("addReportTemplate.upload").setEnabled(
		    false);
	} else {
	    sap.ui.getCore().getControl("addReportTemplate.flag").setEnabled(
		    false);
	    sap.ui.getCore().getControl("addReportTemplate.upload").setEnabled(
		    false);
	    sap.ui.getCore().getControl("addReportTemplate.category")
		    .setEnabled(false);
	    sap.ui.getCore().getControl("addReportTemplate.name").setEnabled(
		    false);
	    sap.ui.getCore().getControl("addReportTemplate.description")
		    .setEnabled(false);
	}

    },

    saveTemplate : function() {
	var _this = this;
	var oModel = _this.getView().getModel();
	var newTemplate = oModel.getProperty("/newTemplate");
	console.log(JSON.stringify(newTemplate));
	jQuery
		.ajax({
		    url : "saveReportTemplate.do",
		    data : newTemplate,
		    type : 'GET',
		    dataType : "text",
		    success : function(data) {
			sap.ui.commons.MessageBox.show(data, "SUCCESS",
				"Save Success");
			sap.ui.getCore().getControl("myShell").destroyContent();
			var oReportTemplate = sap.ui.view({
			    id : 'idReportTemplate',
			    viewName : 'ereportanalyticsnew.reportTemplate',
			    type : sap.ui.core.mvc.ViewType.JS
			});
			sap.ui.getCore().getControl("myShell").setContent(oReportTemplate);
			sap.ui.getCore().getControl("idReportTemplate").getController().getReportTemplateList();
		    },
		    error : function(jqXHR, textStatus, errorThrown) {
			// TODO improve error handling
			sap.ui.commons.MessageBox
				.alert("Could not create report template: "
					+ textStatus);
		    }
		});
    },

    cancel : function() {
	var oModel = this.getView().getModel();
	oModel.getData().newTemplate = {};
	sap.ui.getCore().getControl("myShell").destroyContent();
	var oReportTemplate = sap.ui.view({
	    id : 'idReportTemplate',
	    viewName : 'ereportanalyticsnew.reportTemplate',
	    type : sap.ui.core.mvc.ViewType.JS
	});
	//oShell.setContent(oReportTemplate);
	sap.ui.getCore().getControl("myShell").setContent(oReportTemplate);
	sap.ui.getCore().getControl("idReportTemplate").getController().getReportTemplateList();
    },
    
    setHTMLContent : function(html) {
	var _this = this;
	var oModel = _this.getView().getModel();
	var newTemplate = oModel.getProperty("/newTemplate");
	var id = eval(newTemplate).id; 
	html.setContent("<iframe src='/eReportAnalyticsGit/pages/reportAdmin/ReportTemplatePreview.jsp?groupID="+id+"' height='550px' width='100%' scrolling='yes'/>");
    }

/**
 * Similar to onAfterRendering, but this hook is invoked before the controller's
 * View is re-rendered (NOT before the first rendering! onInit() is used for
 * that one!).
 * 
 * @memberOf ereportanalyticsnew.addReportTemplate
 */
// onBeforeRendering: function() {
//
// },
/**
 * Called when the View has been rendered (so its HTML is part of the document).
 * Post-rendering manipulations of the HTML could be done here. This hook is the
 * same one that SAPUI5 controls get after being rendered.
 * 
 * @memberOf ereportanalyticsnew.addReportTemplate
 */
// onAfterRendering: function() {
//
// },
/**
 * Called when the Controller is destroyed. Use this one to free resources and
 * finalize activities.
 * 
 * @memberOf ereportanalyticsnew.addReportTemplate
 */
// onExit: function() {
//
// }
});