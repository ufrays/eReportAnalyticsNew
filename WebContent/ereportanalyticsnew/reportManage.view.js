sap.ui.jsview("ereportanalyticsnew.reportManage", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf ereportanalyticsnew.reportManage
	*/ 
	getControllerName : function() {
		return "ereportanalyticsnew.reportManage";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf ereportanalyticsnew.reportManage
	*/ 
	createContent : function(oController) {
	    
		// Create the button
		// Button - Detail
		var oBtn_Detail = new sap.ui.commons.Button("reportTemplate.B0", {
			text : "Edit",
			enabled : false,
			press : function() {
				oController.getDetail(oTable);
			}
		});
		// Create an instance of the table control
		var oTable = new sap.ui.table.Table("ReportManage_T1", {
			visibleRowCount : 10,
			firstVisibleRow : 3,
			selectionMode : sap.ui.table.SelectionMode.Single,
			toolbar : new sap.ui.commons.Toolbar({
				items : [ 
				          oBtn_Detail 
				          ]
			}),
			extension: [oBtn_Detail]
		});
		// oBtn_New.attachPress(function(oEvent){oController.detailApprove(oTable.getModel());});

		// Define the columns and the control templates to be used
		var oColumn = new sap.ui.table.Column({
			label : new sap.ui.commons.Label({
				text : "Report Duration"
			}),
			template : new sap.ui.commons.TextView().bindProperty("text", "durationID"),
			sortProperty : "durationID",
			filterProperty : "durationID"
		});
		oTable.addColumn(oColumn);
		oTable.addColumn(new sap.ui.table.Column({
			label : new sap.ui.commons.Label({
				text : "Template Name"
			}),
			template : new sap.ui.commons.TextField().bindProperty("value", "tableGroupModelName"),
			sortProperty : "tableGroupModelName",
			filterProperty : "tableGroupModelName"
		}));
		oTable.addColumn(new sap.ui.table.Column({
			label : new sap.ui.commons.Label({
				text : "Duration Type"
			}),
			template : new sap.ui.commons.TextField().bindProperty("value", "durationFlag"),
			sortProperty : "durationFlag",
			filterProperty : "durationFlag"
		}));
		oTable.addColumn(new sap.ui.table.Column({
			label : new sap.ui.commons.Label({
				text : "Validity From"
			}),
			template : new sap.ui.commons.TextField().bindProperty("value", "startDate"),
			sortProperty : "startDate",
			filterProperty : "startDate"
		}));
		oTable.addColumn(new sap.ui.table.Column({
			label : new sap.ui.commons.Label({
				text : "Validity To"
			}),
			template : new sap.ui.commons.TextField().bindProperty("value", "endDate"),
		}));
		oTable.addColumn(new sap.ui.table.Column({
			label : new sap.ui.commons.Label({
				text : "Status"
			}),
			template : new sap.ui.commons.TextField().bindProperty("value", "status")
		}));
		oTable.addColumn(new sap.ui.table.Column({
			label : new sap.ui.commons.Label({
				text : "Handed|Rejected|All"
			}),
			template : new sap.ui.commons.TextField().bindProperty("value", "counter")
		}));
		oTable.attachRowSelectionChange(function(oEvent) {
			// oEvent.preventDefault();
			console.log(oTable.getSelectedIndex());
			oController.tableRowSelected(oTable);
		});
		// Create a model and bind the table rows to this model
		oController.getReportTaskList();

		var oLayout = new sap.ui.commons.layout.VerticalLayout("L_ReportTemplate1", {
			content : [ oTable ]
		});

		return oLayout;
	}

	    
});
