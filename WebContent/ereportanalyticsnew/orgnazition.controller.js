sap.ui.controller("ereportanalyticsnew.orgnazition", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf ereportanalyticsnew.orgnazition
*/
	
	getOrgList: function() {
		jQuery.ajax({
			url : "getAllOrgnazition.do",
			type : 'GET',
			dataType:"json",
			success : function(data) {
				var oTable = sap.ui.getCore().getControl("Org_T1");
				var oModel = new sap.ui.model.json.JSONModel();
				oModel.setData({orgDTOs: data});
				oTable.setModel(oModel);
			    oTable.unbindRows().bindRows("/orgDTOs");
			    
			},
			error : function(jqXHR, textStatus, errorThrown) {
				// TODO improve error handling
				sap.ui.commons.MessageBox.alert("Failed to retrieve data: " + textStatus+ "\n" + errorThrown);
			}
		});
		
	},
	
	onInit: function() {
		var model = new sap.ui.model.json.JSONModel();     	   
	    this.getView().setModel(model);	 
	    this.getOrgList();
	},


	
	deleteOrg : function(oTable) {
		var _this = this;
		var oModel = _this.getView().getModel();
		//
		var iSelectedIndex = oTable.getSelectedIndex();
		var oTableModel = oTable.getModel();
		console.log(iSelectedIndex);
		var selRowContext = oTable.getContextByIndex(iSelectedIndex);
		var selectedOrgID = oTableModel.getProperty("id", selRowContext);
		console.log(selectedOrgID);
		jQuery.ajax({
			url : "deleteOrgnazition.do?id="+selectedOrgID,
			type : 'GET',
			success : function(data) {
				sap.ui.commons.MessageBox.alert("The Orgnazition-["+selectedOrgID+"] was deleted!");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				// TODO improve error handling
				sap.ui.commons.MessageBox.alert("Could not create leave request: " + textStatus);
			}
		});
		// needed to refresh the view
		this.getOrgList();
	},
	
	createOrg : function(oTable) {
		var _this = this;
		var oModel = _this.getView().getModel();
		//
		var iSelectedIndex = oTable.getSelectedIndex();
		var oTableModel = oTable.getModel();
		console.log(iSelectedIndex);
		var selRowContext = oTable.getContextByIndex(iSelectedIndex);
		var selectedOrgID = oTableModel.getProperty("id", selRowContext);
		var selectedOrgName = oTableModel.getProperty("name", selRowContext);
		
		// create a new, empty leave request in the model
		oModel.getData().newOrg = {};
		var newOrg = oModel.getProperty("/newOrg");
		newOrg.parentName = selectedOrgName;
		newOrg.parentID = selectedOrgID;
		// open popup
		_this.openOrgPopup("/newOrg");
	},
	
	openOrgPopup : function(sContext) {
		var _this = this;
		var oModel = _this.getView().getModel();

		var orgPopupForm = sap.ui.getCore().byId("org.orgPopupForm");

		// bind to model and selected context
		orgPopupForm.setModel(oModel);
		orgPopupForm.bindElement(sContext);

		// open popup
		sap.ui.getCore().byId("org.orgPopup").open();
	},
	
	saveOrg:function() {
		var _this = this;
		var oModel = _this.getView().getModel();
		// add new org
		var newOrg = oModel.getProperty("/newOrg");
		console.log(JSON.stringify(newOrg));
		if (typeof (newOrg) != "undefined") {
			jQuery.ajax({
				url : "addOrgnazition.do",
				data : newOrg,
				type : 'GET',
				dataType:"text",
				success : function(data) {

					
				},
				error : function(jqXHR, textStatus, errorThrown) {
					// TODO improve error handling
					sap.ui.commons.MessageBox.alert("Could not create leave request: " + textStatus);
				}
			});
			
		}
		// remove new entry
		//delete oModel.getData().newOrg;
		oModel.destroyBindingContext("/newOrg");
		
		// needed to refresh the view
		_this.getOrgList();

		// close popup
		sap.ui.getCore().byId("org.orgPopup").close();
		
	},
	
	cancelOrg:function(){
		//delete oModel.getData().newOrg;
		// close popup
		sap.ui.getCore().byId("org.orgPopup").close();
	}
	
	
/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf ereportanalyticsnew.orgnazition
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf ereportanalyticsnew.orgnazition
*/
//	onAfterRendering: function() {
//
//	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf ereportanalyticsnew.orgnazition
*/
//	onExit: function() {
//
//	}

});