sap.ui.controller("ereportanalyticsnew.reportTemplate", {

	/**
	 * Called when a controller is instantiated and its View controls (if
	 * available) are already created. Can be used to modify the View before it
	 * is displayed, to bind event handlers and do other one-time
	 * initialization.
	 * 
	 * @memberOf ereportanalyticsnew.reportTemplate
	 */
	// onInit: function() {
	//
	// },
	getReportTemplateList : function() {
		jQuery.ajax({
			url : "getReportTemplateList.do",
			type : 'GET',
			dataType : "json",
			success : function(data) {
				var oTable = sap.ui.getCore().getControl("ReportTemplate_T1");
				var oModel = new sap.ui.model.json.JSONModel();
				oModel.setData({
					"reportTemplate" : data
				});
				oTable.setModel(oModel);
				oTable.unbindRows().bindRows("/reportTemplate");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				// TODO improve error handling
				sap.ui.commons.MessageBox.alert("Failed to retrieve data: " + textStatus + "\n" + errorThrown);
			}
		});
	},

	createReportTemplate : function() {
		sap.ui.getCore().getControl("myShell").destroyContent();
		var oCreateReportTemplate = sap.ui.view({
			viewName : "ereportanalyticsnew.addReportTemplate",
			type : sap.ui.core.mvc.ViewType.JS
		});
		sap.ui.getCore().getControl("myShell").setContent(oCreateReportTemplate);
	},

	editReportTemplate : function(oTable) {
		var oModel = oTable.getModel();
		var selRowContext = oTable.getContextByIndex(oTable.getSelectedIndex());
		var selectedID = oModel.getProperty("id", selRowContext);
		var selectedStatus = oModel.getProperty("status", selRowContext);
		sap.ui.getCore().getControl("myShell").destroyContent();
		var oReportTemplate = sap.ui.view({
			viewName : "ereportanalyticsnew.addReportTemplate",
			type : sap.ui.core.mvc.ViewType.JS
		});
		sap.ui.getCore().getControl("myShell").setContent(oReportTemplate);
		oReportTemplate.getController().onEditOrViewInit(selectedStatus, selectedID);
	},

	deleteReportTemplate : function(oTable) {
	    var _this = this;
		var oModel = oTable.getModel();
		var selRowContext = oTable.getContextByIndex(oTable.getSelectedIndex());
		var selectedID = oModel.getProperty("id", selRowContext);
	    jQuery
		.ajax({
		    url : "deleteReportTemplate.do?groupID="+selectedID,
		    type : 'GET',
		    dataType : "text",
		    success : function(data) {
			sap.ui.commons.MessageBox.show(data, "SUCCESS",	data);
			_this.getReportTemplateList();
		    },
		    error : function(jqXHR, textStatus, errorThrown) {
			// TODO improve error handling
			sap.ui.commons.MessageBox.alert("Could not execute action: "+ textStatus);
		    }
		});
	},

	releaseReportTemplate : function(oTable) {
	    var _this = this;
		var oModel = oTable.getModel();
		var selRowContext = oTable.getContextByIndex(oTable.getSelectedIndex());
		var selectedID = oModel.getProperty("id", selRowContext);
	    jQuery
		.ajax({
		    url : "releaseReportTemplate.do?groupID="+selectedID,
		    type : 'GET',
		    dataType : "text",
		    success : function(data) {
			sap.ui.commons.MessageBox.show(data, "SUCCESS",	data);
			_this.getReportTemplateList();
		    },
		    error : function(jqXHR, textStatus, errorThrown) {
			// TODO improve error handling
			sap.ui.commons.MessageBox.alert("Could not execute action: "+ textStatus);
		    }
		});
	},
	
	tableRowSelected : function(oTable) {
		var oModel = oTable.getModel();
		var selRowContext = oTable.getContextByIndex(oTable.getSelectedIndex());
		var selectedStatus = oModel.getProperty("status", selRowContext);
		console.log(selectedStatus);
		if (oTable.getSelectedIndex() != -1) {
			if (selectedStatus == "New") {
				sap.ui.getCore().getControl("reportTemplate.B0").setEnabled(true);
				sap.ui.getCore().getControl("reportTemplate.B1").setEnabled(true);
				sap.ui.getCore().getControl("reportTemplate.B3").setEnabled(true);
			} else {
				sap.ui.getCore().getControl("reportTemplate.B0").setEnabled(true);
				sap.ui.getCore().getControl("reportTemplate.B1").setEnabled(false);
				sap.ui.getCore().getControl("reportTemplate.B3").setEnabled(false);
			}
		} else {
			sap.ui.getCore().getControl("reportTemplate.B0").setEnabled(true);
			sap.ui.getCore().getControl("reportTemplate.B1").setEnabled(false);
			sap.ui.getCore().getControl("reportTemplate.B3").setEnabled(false);
		}

	},

/**
 * Similar to onAfterRendering, but this hook is invoked before the controller's
 * View is re-rendered (NOT before the first rendering! onInit() is used for
 * that one!).
 * 
 * @memberOf ereportanalyticsnew.reportTemplate
 */
// onBeforeRendering: function() {
//
// },
/**
 * Called when the View has been rendered (so its HTML is part of the document).
 * Post-rendering manipulations of the HTML could be done here. This hook is the
 * same one that SAPUI5 controls get after being rendered.
 * 
 * @memberOf ereportanalyticsnew.reportTemplate
 */
// onAfterRendering: function() {
//
// },
/**
 * Called when the Controller is destroyed. Use this one to free resources and
 * finalize activities.
 * 
 * @memberOf ereportanalyticsnew.reportTemplate
 */
// onExit: function() {
//
// }
});