sap.ui.controller("ereportanalyticsnew.reportManage", {

    /**
     * Called when a controller is instantiated and its View controls (if
     * available) are already created. Can be used to modify the View before it
     * is displayed, to bind event handlers and do other one-time
     * initialization.
     * 
     * @memberOf ereportanalyticsnew.reportManage
     */
    onInit : function() {
	var model = new sap.ui.model.json.JSONModel();
	this.getView().setModel(model);
	var oModel = this.getView().getModel();
	oModel.getData().reportTaskDTO = {};
	//
	jQuery.ajax({
	    url : "getReleasedReportTemplateList.do",
	    type : 'GET',
	    dataType : "json",
	    success : function(data) {
		var oModel = new sap.ui.model.json.JSONModel();
		oModel.setData({ "/reportTemplate" : data});
		sap.ui.getCore().getControl("L_ReportManage1").bindElement("/reportTemplate");
	    },
	    error : function(jqXHR, textStatus, errorThrown) {
		// TODO improve error handling
		sap.ui.commons.MessageBox.alert("Failed to retrieve data: "
			+ textStatus + "\n" + errorThrown);
	    }
	});

    },

    getReportTaskList : function() {

    },

    tableRowSelected : function(oTable) {

    },

    getDetail : function() {

    }
/**
 * Similar to onAfterRendering, but this hook is invoked before the controller's
 * View is re-rendered (NOT before the first rendering! onInit() is used for
 * that one!).
 * 
 * @memberOf ereportanalyticsnew.reportManage
 */
// onBeforeRendering: function() {
//
// },
/**
 * Called when the View has been rendered (so its HTML is part of the document).
 * Post-rendering manipulations of the HTML could be done here. This hook is the
 * same one that SAPUI5 controls get after being rendered.
 * 
 * @memberOf ereportanalyticsnew.reportManage
 */
// onAfterRendering: function() {
//
// },
/**
 * Called when the Controller is destroyed. Use this one to free resources and
 * finalize activities.
 * 
 * @memberOf ereportanalyticsnew.reportManage
 */
// onExit: function() {
//
// }
});