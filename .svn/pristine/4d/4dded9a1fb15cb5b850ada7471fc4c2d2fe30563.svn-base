sap.ui.controller("ereportanalyticsnew.taskSchedule", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf ereportanalyticsnew.taskSchedule
*/
	onInit: function() {
		this.getView().setModel(new sap.ui.model.json.JSONModel());
		var oModel = this.getView().getModel();
		oModel.getData().scheduleTaskDTO = {};
		this.getTaskScheduleList();
	},

	//get all template
	getReleasedReportTemplate: function(oComboBox){
		jQuery.ajax({
		    url : "getReleasedReportTemplateList.do",
		    type : 'GET',
		    dataType : "json",
		    success : function(data) {
		    	console.log(data);
		    	var model = new sap.ui.model.json.JSONModel();
		    	model.setData({taskSchedule_releasedReportTemplate:data});
		    	sap.ui.getCore().setModel(model);
		    	oComboBox.setModel(model);
		    	oComboBox.bindItems("/taskSchedule_releasedReportTemplate", new sap.ui.core.ListItem({key:"{id}",text:"{name}"}));
		    },
		    error : function(jqXHR, textStatus, errorThrown) {
				sap.ui.commons.MessageBox.alert("Failed to retrieve data: "	+ textStatus + "\n" + errorThrown);
		    }
		});
	},
	
	//
	createTaskSchedule : function() {
		sap.ui.getCore().getControl("myShell").destroyContent();
		var oView = sap.ui.view({
			viewName : "ereportanalyticsnew.addTaskSchedule",
			type : sap.ui.core.mvc.ViewType.JS
		});
		sap.ui.getCore().getControl("myShell").setContent(oView);
	},
	
	//
	editTaskSchedule : function(oTable) {
		var oModel = oTable.getModel();
		var selRowContext = oTable.getContextByIndex(oTable.getSelectedIndex());
		var selectedID = oModel.getProperty("id", selRowContext);
		sap.ui.getCore().getControl("myShell").destroyContent();
		var oView = sap.ui.view({
			viewName : "ereportanalyticsnew.addTaskSchedule",
			type : sap.ui.core.mvc.ViewType.JS
		});
		sap.ui.getCore().getControl("myShell").setContent(oView);
		//oReportTemplate.getController().onEditOrViewInit(selectedStatus, selectedID);
	},
	
	//
	getTaskScheduleList : function() {
		var oModel = this.getView().getModel();
		var scheduleTask = oModel.getProperty("/scheduleTaskDTO");
		console.log(scheduleTask);
		jQuery.ajax({
		    url : "taskSchedule/getReportTaskListByDTO.do",
		    data : scheduleTask,
		    type : 'GET',
		    dataType : "json",
		    success : function(data) {
		    	console.log(data);
		    	var model = new sap.ui.model.json.JSONModel();
		    	model.setData( {"taskSchedule.scheduleTaskList" : data});
		    	var oTable = sap.ui.getCore().getControl("taskSchedule_T1");
				oTable.setModel(model);
			    oTable.unbindRows().bindRows("/taskSchedule.scheduleTaskList");
		    },
		    error : function(jqXHR, textStatus, errorThrown) {
				// TODO improve error handling
				sap.ui.commons.MessageBox.alert("Failed to retrieve data: "	+ textStatus + "\n" + errorThrown);
		    }
		});
	},
	
	//
	cancelTaskSchedule : function(oTable) {
	    
	},
	
	tableRowSelected : function(oTable) {
		var oModel = oTable.getModel();
		var selRowContext = oTable.getContextByIndex(oTable.getSelectedIndex());
//		var selectedStatus = oModel.getProperty("status", selRowContext);
//		console.log(selectedStatus);
//		if (oTable.getSelectedIndex() != -1) {
//			if (selectedStatus == "New") {
//				sap.ui.getCore().getControl("reportTemplate.B0").setEnabled(true);
//				sap.ui.getCore().getControl("reportTemplate.B1").setEnabled(true);
//				sap.ui.getCore().getControl("reportTemplate.B3").setEnabled(true);
//			} else {
//				sap.ui.getCore().getControl("reportTemplate.B0").setEnabled(true);
//				sap.ui.getCore().getControl("reportTemplate.B1").setEnabled(false);
//				sap.ui.getCore().getControl("reportTemplate.B3").setEnabled(false);
//			}
//		} else {
//			sap.ui.getCore().getControl("reportTemplate.B0").setEnabled(true);
//			sap.ui.getCore().getControl("reportTemplate.B1").setEnabled(false);
//			sap.ui.getCore().getControl("reportTemplate.B3").setEnabled(false);
//		}

	},
/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf ereportanalyticsnew.taskSchedule
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf ereportanalyticsnew.taskSchedule
*/
//	onAfterRendering: function() {
//
//	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf ereportanalyticsnew.taskSchedule
*/
//	onExit: function() {
//
//	}

});