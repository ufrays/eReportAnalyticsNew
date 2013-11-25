sap.ui.jsview("ereportanalyticsnew.reportTemplate", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf ereportanalyticsnew.reportTemplate
	*/ 
	getControllerName : function() {
		return "ereportanalyticsnew.reportTemplate";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf ereportanalyticsnew.reportTemplate
	*/ 
	createContent : function(oController) {

		// Create the button 
		// Button - Detail
		var oBtn_Detail =new sap.ui.commons.Button({
			text : "Detail",enabled:true,
			press : function() {
				 //oController.ReportTemplateDetail(); 
			}
		});
		// Button - New
		var oBtn_New =new sap.ui.commons.Button({
			text : "New",enabled:true,
			press : function() {
				oController.createReportTemplate(); 
			}
		});
		// Button - Delete
		var oBtn_Delete =new sap.ui.commons.Button({
			text : "Delete",enabled:false,
			press : function() {
				
			}
		});
		// Create an instance of the table control
		var oTable = new sap.ui.table.Table("ReportTemplate_T1", {
			visibleRowCount : 10,
			firstVisibleRow : 3,
			selectionMode : sap.ui.table.SelectionMode.Single,
			toolbar: new sap.ui.commons.Toolbar({
							items : [oBtn_Detail, oBtn_New, oBtn_Delete]
					}) 
		}); 
		//oBtn_New.attachPress(function(oEvent){oController.detailApprove(oTable.getModel());});

		// Define the columns and the control templates to be used
		var oColumn = new sap.ui.table.Column(
				{
					label : new sap.ui.commons.Label({
						text : "ID"
					}),
					template : new sap.ui.commons.TextView().bindProperty("text", "id"),
					sortProperty : "id",
					filterProperty : "id"
				});
		oTable.addColumn(oColumn);
		oTable.addColumn(new sap.ui.table.Column({
			label : new sap.ui.commons.Label({
				text : "Template Name"
			}),
			template : new sap.ui.commons.TextField().bindProperty("value","name"),
			sortProperty : "name",
			filterProperty : "name"
		}));
		oTable.addColumn(new sap.ui.table.Column({
			label : new sap.ui.commons.Label({
				text : "Created By"
			}),
			template : new sap.ui.commons.TextField().bindProperty("value","createdBy"),
			sortProperty : "createdBy",
			filterProperty : "createdBy"
		}));
		oTable.addColumn(new sap.ui.table.Column({
			label : new sap.ui.commons.Label({
				text : "Created On"
			}),
			template : new sap.ui.commons.TextField().bindProperty("value","createdOn")
		}));
		oTable.attachRowSelectionChange(function(oEvent){
  			//oEvent.preventDefault();
  			console.log(oTable.getSelectedIndex());
  			//oController.requestSelected(oTable.getModel());
  		});
		// Create a model and bind the table rows to this model
		oController.getReportTemplateList();

		var oLayout = new sap.ui.commons.layout.VerticalLayout("L_ReportTemplate1", {
			content : [ oTable ]
		});

		return oLayout;
	}

});
