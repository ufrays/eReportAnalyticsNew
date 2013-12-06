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
			text : "Detail",
			enabled : false,
			press : function() {
				oController.getDetail(oTable);
			}
		});
		// 1.create a standalone toolbar
		var oToolbar1 = new sap.ui.commons.Toolbar("reportTemplate.tb1");
		oToolbar1.setDesign(sap.ui.commons.ToolbarDesign.Standard);
		// add a combobox as Template
		var oLabel = new sap.ui.commons.Label({text: 'Template:'});
		var oComboBox = new sap.ui.commons.ComboBox("reportTemplate.template",{
		    items: {path:"/", template:new sap.ui.core.ListItem({text:"{name}"})}
		});
		oToolbar1.addItem(oLabel);
		oToolbar1.addItem(oComboBox);
		oToolbar1.addItem(new sap.ui.commons.ToolbarSeparator({
			design: sap.ui.commons.ToolbarSeparatorDesign.FullHeight
		}));
		//add a combobox as Status
		oLabel = new sap.ui.commons.Label({text: 'Status:'});
		oComboBox = new sap.ui.commons.ComboBox("reportTemplate.status",{
			items : [ new sap.ui.core.ListItem({
				text : ""
			}),new sap.ui.core.ListItem({
				text : "In Progress"
			}), new sap.ui.core.ListItem({
				text : "Expired"
			}), new sap.ui.core.ListItem({
				text : "Finished"
			}) ]
		});
		oToolbar1.addItem(oLabel);
		oToolbar1.addItem(oComboBox);
		oToolbar1.addItem(new sap.ui.commons.ToolbarSeparator({
			design: sap.ui.commons.ToolbarSeparatorDesign.FullHeight
		}));
		// add a combobox as Duration Type & Duration ID
		oLabel = new sap.ui.commons.Label({text: 'Duration: '});
		oComboBox = new sap.ui.commons.ComboBox("reportTemplate.durationType",{
			items : [ new sap.ui.core.ListItem({
				text : "", key:""
			}), new sap.ui.core.ListItem({
				text : "Weekly", key:"WEEKLY"
			}), new sap.ui.core.ListItem({
				text : "Monthly", key:"MONTHLY"
			}), new sap.ui.core.ListItem({
				text : "Quarterly", key:"QUARTERLY"
			}), new sap.ui.core.ListItem({
				text : "Yearly", key:"YEARLY"
			})]
		});
		oToolbar1.addItem(oLabel);
		oToolbar1.addItem(oComboBox);
		oComboBox = new sap.ui.commons.ComboBox("reportTemplate.durationID",{
			items : [ new sap.ui.core.ListItem({
				text : ""
			}),new sap.ui.core.ListItem({
				text : "#1"
			}), new sap.ui.core.ListItem({
				text : "Do it!"
			}), new sap.ui.core.ListItem({
				text : "Hello world!"
			}), new sap.ui.core.ListItem({
				text : "Yet another stupid button text"
			}) ]
		});
		oToolbar1.addItem(oComboBox);
		// 2.Create an instance of the table control
		var oTable = new sap.ui.table.Table("ReportManage_T1", {
			visibleRowCount : 10,
			firstVisibleRow : 3,
			selectionMode : sap.ui.table.SelectionMode.Single,
			toolbar : new sap.ui.commons.Toolbar({
				items : [ 
				          oBtn_Detail 
				          ]
			})
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
		var oProgressIndicator1 = new sap.ui.commons.ProgressIndicator({
			width: "80%", 
			percentValue: 33, 
			displayValue: "33%"
			});
		oTable.addColumn(new sap.ui.table.Column({
			label : new sap.ui.commons.Label({
				text : "Completion Rate"
			}),
			template : oProgressIndicator1
		}));
		oTable.attachRowSelectionChange(function(oEvent) {
			// oEvent.preventDefault();
			console.log(oTable.getSelectedIndex());
			oController.tableRowSelected(oTable);
		});
		// Create a model and bind the table rows to this model
		oController.getReportTaskList();

		var oLayout = new sap.ui.commons.layout.VerticalLayout("L_ReportManage1", {
			content : [ oToolbar1,oTable ]
		});
		

		return oLayout;
	}

	    
});
