sap.ui.jsview("ereportanalyticsnew.taskSchedule", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf ereportanalyticsnew.taskSchedule
	*/ 
	getControllerName : function() {
		return "ereportanalyticsnew.taskSchedule";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf ereportanalyticsnew.taskSchedule
	*/ 
	createContent : function(oController) {

		// Create the button
		// Button - Detail
		var oBtn_Detail = new sap.ui.commons.Button("taskSchedule.B0", {
			text : "Edit",
			enabled : false,
			press : function() {
				oController.editTaskSchedule(oTable);
			}
		});
		var oBtn_New = new sap.ui.commons.Button("taskSchedule.B1", {
			text : "New",
			enabled : true,
			press : function() {
				oController.createTaskSchedule();
			}
		});
		var oBtn_Cancel = new sap.ui.commons.Button("taskSchedule.B2", {
			text : "Cancel",
			enabled : false,
			press : function() {
				//oController.getDetail(oTable);
			}
		});
		//
		var oLayout = new sap.ui.commons.layout.VerticalLayout("L_taskSchedule1").bindElement("/scheduleTaskDTO");
		// 1.create a standalone toolbar
		var oToolbar1 = new sap.ui.commons.Toolbar("taskSchedule.tb1");
		oToolbar1.setDesign(sap.ui.commons.ToolbarDesign.Standard);
		// add a combobox as Template
		var oLabel = new sap.ui.commons.Label({text: 'Template:'});
		var oComboBox = new sap.ui.commons.ComboBox({
			 change: function(oEvent){
				var selectedKey =  oEvent.oSource.getSelectedKey();
				console.log("selectedKey:"+selectedKey);
				//this.getModel().getData().scheduleTaskDTO.tableGroupModel = selectedKey;
				this.getModel().getProperty("/scheduleTaskDTO").tableGroupModel = selectedKey;
				oController.getTaskScheduleList();
			 }
		});
		oController.getReleasedReportTemplate(oComboBox); ///
		oComboBox.bindProperty("value", "tableGroupModelName");
		oToolbar1.addItem(oLabel);
		oToolbar1.addItem(oComboBox);
		oToolbar1.addItem(new sap.ui.commons.ToolbarSeparator({
			design: sap.ui.commons.ToolbarSeparatorDesign.FullHeight
		}));
		//add a combobox as Status
		oLabel = new sap.ui.commons.Label({text: 'Status:'});
		oComboBox = new sap.ui.commons.ComboBox("taskSchedule.status",{
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
		oComboBox = new sap.ui.commons.ComboBox("taskSchedule.durationType",{
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
		// 2.Create an instance of the table control
		var oTable = new sap.ui.table.Table("taskSchedule_T1", {
			visibleRowCount : 10,
			firstVisibleRow : 3,
			selectionMode : sap.ui.table.SelectionMode.Single,
			toolbar : new sap.ui.commons.Toolbar({
				items : [ 
				          oBtn_Detail,oBtn_New,oBtn_Cancel
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

		oTable.attachRowSelectionChange(function(oEvent) {
			// oEvent.preventDefault();
			console.log(oTable.getSelectedIndex());
			oController.tableRowSelected(oTable); 
		});
		// Create a model and bind the table rows to this model
		////oController.getTaskScheduleList();
		oLayout.addContent( oToolbar1);
		oLayout.addContent( oTable);
		return oLayout;
	}

});
