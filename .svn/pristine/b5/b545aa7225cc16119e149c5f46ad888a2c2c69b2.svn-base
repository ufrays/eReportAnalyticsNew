sap.ui.jsview("ereportanalyticsnew.businessuser", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf ereportanalyticsnew.businessuser
	*/ 
	getControllerName : function() {
		return "ereportanalyticsnew.businessuser";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf ereportanalyticsnew.businessuser
	*/ 
	createContent : function(oController) {

		// Create the button 
		// Button - Edit
		var oBtn_Edit =new sap.ui.commons.Button({
			text : "Edit",enabled:false,
			press : function() {
				
			}
		});
		// Button - New
		var oBtn_New =new sap.ui.commons.Button({
			text : "New",enabled:false,
			press : function() {
				
			}
		});
		// Button - Delete
		var oBtn_Delete =new sap.ui.commons.Button({
			text : "Delete",enabled:false,
			press : function() {
				
			}
		});
		
		var oStatusListBox = new sap.ui.commons.DropdownBox({
			items : [ new sap.ui.core.ListItem({
				text : "All Users"
			}) ]
		});
		// Create an instance of the table control
		var oTable = new sap.ui.table.Table("BusinessUser_T1", {
			visibleRowCount : 15,
			firstVisibleRow : 3,
			selectionMode : sap.ui.table.SelectionMode.Single,
			
			toolbar: new sap.ui.commons.Toolbar({
				items : [oBtn_Edit, oBtn_New, oBtn_Delete]
			}) 
		});
		//oBtn_New.attachPress(function(oEvent){oController.detailApprove(oTable.getModel());});

		// Define the columns and the control templates to be used
		var oColumn = new sap.ui.table.Column(
				{
					label : new sap.ui.commons.Label({
						text : "User Name"
					}),
					template : new sap.ui.commons.TextView().bindProperty("text", "name"),
					sortProperty : "name",
					filterProperty : "name"
				});
		oTable.addColumn(oColumn);
		oTable.addColumn(new sap.ui.table.Column({
			label : new sap.ui.commons.Label({
				text : "First Name"
			}),
			template : new sap.ui.commons.TextField().bindProperty("value","firstName"),
			sortProperty : "firstName",
			filterProperty : "firstName"
		}));
		oTable.addColumn(new sap.ui.table.Column({
			label : new sap.ui.commons.Label({
				text : "Last Name"
			}),
			template : new sap.ui.commons.TextField().bindProperty("value","lastName"),
			sortProperty : "lastName",
			filterProperty : "lastName"
		}));
		oTable.addColumn(new sap.ui.table.Column({
			label : new sap.ui.commons.Label({
				text : "Orgnazition"
			}),
			template : new sap.ui.commons.TextField().bindProperty("value","orgnazition.name")
		}));
		oTable.attachRowSelectionChange(function(oEvent){
  			//oEvent.preventDefault();
  			console.log(oTable.getSelectedIndex());
  			//oController.requestSelected(oTable.getModel());
  		});
		// Create a model and bind the table rows to this model
		oController.getBusinessUserList();

		var oLayout = new sap.ui.commons.layout.VerticalLayout("L_BusinessUser1", {
			content : [ oTable ]
		});

		return oLayout;
		
		
	}

});
