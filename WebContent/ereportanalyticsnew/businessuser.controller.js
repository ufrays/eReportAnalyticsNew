sap.ui.controller("ereportanalyticsnew.businessuser", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf ereportanalyticsnew.businessuser
*/
	onInit: function() {
		this.getBusinessUserList();
	},

	getBusinessUserList: function() {
		jQuery.ajax({
			url : "getAllPersons.do",
			type : 'GET',
			dataType:"json",
			success : function(data) {
				var oTable = sap.ui.getCore().getControl("BusinessUser_T1");
				var oModel = new sap.ui.model.json.JSONModel();
				oModel.setData({"businessUsers": data});
				oTable.setModel(oModel);
			    oTable.unbindRows().bindRows("/businessUsers");
			    
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
* @memberOf ereportanalyticsnew.businessuser
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf ereportanalyticsnew.businessuser
*/
//	onAfterRendering: function() {
//
//	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf ereportanalyticsnew.businessuser
*/
//	onExit: function() {
//
//	}

});