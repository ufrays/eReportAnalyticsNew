sap.ui.controller("ereportanalyticsnew.addReportTemplate", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf ereportanalyticsnew.addReportTemplate
*/
	onInit: function() {
		var model = new sap.ui.model.json.JSONModel();     	   
	    this.getView().setModel(model);	
	    
	    
	    
	},

	refreshForm: function() {
		
		
		
	},
	
	uploadTemplate:function(){
		var _this = this;
		var oModel = _this.getView().getModel();
		jQuery.ajax({
			url : "uploadReportTemplate.do",
			type : 'POST',
			dataType:"json",
			success : function(data) {
				oModel.getData().newTemplate = data;
			    console.log(data);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				// TODO improve error handling
				sap.ui.commons.MessageBox.alert("Failed to retrieve data: " + textStatus+ "\n" + errorThrown);
			}
		});
		
		
		
	}
	
/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf ereportanalyticsnew.addReportTemplate
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf ereportanalyticsnew.addReportTemplate
*/
//	onAfterRendering: function() {
//
//	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf ereportanalyticsnew.addReportTemplate
*/
//	onExit: function() {
//
//	}

});