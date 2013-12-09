sap.ui.controller("ereportanalyticsnew.addTaskSchedule", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf ereportanalyticsnew.addTaskSchedule
*/
	onInit: function() {
		console.log("init....");
		var model = new sap.ui.model.json.JSONModel();
		this.getView().setModel(model);
		var oModel = this.getView().getModel();
		oModel.getData().scheduleTask = {};
		//get all template
		jQuery.ajax({
		    url : "getReleasedReportTemplateList.do",
		    type : 'GET',
		    dataType : "json",
		    success : function(data) {
		    	console.log(data);
		    	oModel.setData({"scheduleTask/releasedReportTemplate":data});
		    },
		    error : function(jqXHR, textStatus, errorThrown) {
				// TODO improve error handling
				sap.ui.commons.MessageBox.alert("Failed to retrieve data: "	+ textStatus + "\n" + errorThrown);
		    }
		});
		// get all orgnaztion
		//get all template
		jQuery.ajax({
		    url : "getOrgnazitionList.do",
		    type : 'GET',
		    dataType : "json",
		    success : function(data) {
		    	console.log(data);
		    	oModel.setData({"scheduleTask/orgnazitionList":data});
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
		var scheduleTask = oModel.getProperty("/scheduleTask");
		jQuery.ajax({
		    url : "createReportTask.do",
		    data : scheduleTask,
		    type : 'GET',
		    dataType : "text",
		    success : function(data) {
				sap.ui.commons.MessageBox.show(data, "SUCCESS",
					"Save Success");
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