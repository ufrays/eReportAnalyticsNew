sap.ui.controller("ereportanalyticsnew.addTaskSchedule", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf ereportanalyticsnew.addTaskSchedule
*/
	onInit: function() {
		console.log("init....");
		var _this = this;
		var scheduleTask = {};
		var scheduleTaskModel = new sap.ui.model.json.JSONModel();
		scheduleTaskModel.setData(scheduleTask);
		_this.getView().setModel(scheduleTaskModel);
		// 1. get all template
		var releasedReportTemplateModel = new sap.ui.model.json.JSONModel();
		jQuery.ajax({
		    url : "getReleasedReportTemplateList.do",
		    type : 'GET',
		    dataType : "json",
		    success : function(data) {
		    		console.log(data);
		    		releasedReportTemplateModel.setData(data);
		    		_this.getView().byId("tableGroupModel").setModel(releasedReportTemplateModel);
		    },
		    error : function(jqXHR, textStatus, errorThrown) {
				// TODO improve error handling
				sap.ui.commons.MessageBox.alert("Failed to retrieve data: "	+ textStatus + "\n" + errorThrown);
		    }
		});
	},
	
	onEditOrViewInit : function(editOrView, taskID) {
		
	},
	
	create: function() {
		var oModel = this.getView().getModel();
		// Information
		var scheduleTask = oModel.getData();
		// Add Orgs
    	var oView = this.getView().byId("orgnazitionSelect").getContent();
		scheduleTask.orgnazitions = oView[0].getModel().getData();
		jQuery.ajax({
		    url : "createReportTask.do",
		    data : JSON.stringify(scheduleTask),
		    type : 'POST',
		    contentType: "application/json; charset=utf-8",
		    success : function(data) {
				sap.ui.commons.MessageBox.show(data, "SUCCESS", "Save Success");
				sap.ui.getCore().getControl("myShell").destroyContent();
				var oView = sap.ui.view({
				    viewName : 'ereportanalyticsnew.taskSchedule',
				    type : sap.ui.core.mvc.ViewType.JS
				});
				sap.ui.getCore().getControl("myShell").setContent(oView);
				oView.getController().getTaskScheduleList();
		    },
		    error : function(jqXHR, textStatus, errorThrown) {
				// TODO improve error handling
				sap.ui.commons.MessageBox.alert("Could not create report template: " + textStatus);
			}
		});
    },
   
    openOrgnazitionSelect: function(){
    	this.getView().byId("orgnazitionSelect").open();
    	var oView = this.getView().byId("orgnazitionSelect").getContent();
    	oView[0].getController().getOrgList();
    },
    
    selectOrgnazition: function(){
    	var oDialog = this.getView().byId("orgnazitionSelect");
    	var oView = oDialog.getContent();
    	var oModel = new sap.ui.model.json.JSONModel();
    	oModel.setData(oView[0].getModel().getData());
    	this.getView().byId("orgnazitionList").setModel(oModel);
    	oDialog.close();
    },
    
	cancel: function() {
	    
    	},
    release: function() {
    	    
    	},
    	
    	
/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf ereportanalyticsnew.addTaskSchedule
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf ereportanalyticsnew.addTaskSchedule
*/
//	onAfterRendering: function() {
//
//	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf ereportanalyticsnew.addTaskSchedule
*/
//	onExit: function() {
//
//	}

});