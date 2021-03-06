sap.ui.controller("ereportanalyticsnew.report-assignment.owl", {

	/**
	 * The control collection register from view
	 */
	controls : {},

	/**
	 * @author I071053
	 */
	deleteReportAssignment : function(reportAssignmentId) {
		var _this = this;
		jQuery.ajax({
			url : 'report-assignment/delete.do',
			data : {
				'reportAssignmentId' : reportAssignmentId,
			},
			type : 'GET',
			contentType : 'application/xml',
			success : function(response) {
				if (!(response == undefined || response == null)) {
					sap.ui.commons.MessageBox.alert(response);
					_this.loadAll();
				}
			},
			error : function(jqXHR, textStatus, errorThrown) {
				// Customized error handling
				if (errorHandler && typeof (dataBindedHandler) === 'function') {
					errorHandler(jqXHR, textStatus, errorThrown);
				} else {
					// Default error handler
					sap.ui.commons.MessageBox.alert('Failed to get response from server: ' + textStatus + '\n' + errorThrown);
				}
			}
		});
	},

	/**
	 * @author I071053
	 */
	loadAll : function(dataBindedHandler, errorHandler) {
		var _this = this;
		jQuery.ajax({
			url : 'report-assignment/get-all.do',
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
				// Customized error handling
				if (dataBindedHandler !== undefined && dataBindedHandler !== null && typeof (dataBindedHandler) === 'function') {
					errorHandler(jqXHR, textStatus, errorThrown);
				} else {
					// Default error handler
					sap.ui.commons.MessageBox.alert('Failed to get list: ' + textStatus + '\n' + errorThrown);
				}
			}
		});
	},
/**
 * Called when a controller is instantiated and its View controls (if available) are already created. Can be used to modify the View before it is
 * displayed, to bind event handlers and do other one-time initialization.
 * 
 * @memberOf ereportanalyticsnew.index
 */
// onInit: function() {
//
// },
/**
 * Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered (NOT before the first rendering! onInit() is used
 * for that one!).
 * 
 * @memberOf ereportanalyticsnew.index
 */
// onBeforeRendering: function() {
//
// },
/**
 * Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here. This
 * hook is the same one that SAPUI5 controls get after being rendered.
 * 
 * @memberOf ereportanalyticsnew.index
 */
// onAfterRendering: function() {
//
// },
/**
 * Called when the Controller is destroyed. Use this one to free resources and finalize activities.
 * 
 * @memberOf ereportanalyticsnew.index
 */
// onExit: function() {
//
// }
});