sap.ui.controller("ereportanalyticsnew.ReportTask.ReportTaskItem", {

	/**
	 * Called when a controller is instantiated and its View controls (if
	 * available) are already created. Can be used to modify the View before it
	 * is displayed, to bind event handlers and do other one-time
	 * initialization.
	 * 
	 * @memberOf ereportanalyticsnew.ReportTask.ReportTaskItem
	 */
	// onInit: function() {
	//
	// },
	/**
	 * Similar to onAfterRendering, but this hook is invoked before the
	 * controller's View is re-rendered (NOT before the first rendering!
	 * onInit() is used for that one!).
	 * 
	 * @memberOf ereportanalyticsnew.ReportTask.ReportTaskItem
	 */
	// onBeforeRendering: function() {
	//
	// },
	/**
	 * Called when the View has been rendered (so its HTML is part of the
	 * document). Post-rendering manipulations of the HTML could be done here.
	 * This hook is the same one that SAPUI5 controls get after being rendered.
	 * 
	 * @memberOf ereportanalyticsnew.ReportTask.ReportTaskItem
	 */
	// onAfterRendering: function() {
	//
	// },
	/**
	 * Called when the Controller is destroyed. Use this one to free resources
	 * and finalize activities.
	 * 
	 * @memberOf ereportanalyticsnew.ReportTask.ReportTaskItem
	 */
	// onExit: function() {
	//
	// }
	loadTask : function(dataBindedHandler) {
		var _this = this;
		jQuery.ajax({
			url : 'reportTask/getAllReportTaskItems.do',
			type : 'GET',
			contentType : 'application/xml',
			success : function(response) {
				var dataModel = new sap.ui.model.json.JSONModel();
				dataModel.setData(response);
				_this.getView().setModel(dataModel);
				if (dataBindedHandler !== undefined && dataBindedHandler !== null && typeof (dataBindedHandler) === 'function') {
					dataBindedHandler(_this, {
						'dataModel' : dataModel,
						'response' : response,
					});
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				// TODO improve error handling
				sap.ui.commons.MessageBox.alert("Failed to retrieve data: " + textStatus + "\n" + errorThrown);
			}
		});
	},

	pressUpload : function() {
		alert("pressed");
	}

});