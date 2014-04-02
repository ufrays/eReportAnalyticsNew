jQuery.sap.require("ereportanalyticsnew.GlobalFunctions");

sap.ui.jsview("ereportanalyticsnew.orgnazition", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf ereportanalyticsnew.orgnazition
	*/ 
	getControllerName : function() {
		return "ereportanalyticsnew.orgnazition";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf ereportanalyticsnew.orgnazition
	*/ 
	createContent : function(oController) {
		
		//Create an instance of the table control
		var oTable = new sap.ui.table.TreeTable("Org_T1",{
			visibleRowCount : 15,
			columns: [
				new sap.ui.table.Column({label: "Name", template: "name"}),
				new sap.ui.table.Column({label: "Report Direct", template: "reportDirect"}),
				new sap.ui.table.Column({label: "Orgnaztion Type", template: "type"}),
				new sap.ui.table.Column({label: "Description", template: "description"})
			],
			selectionMode: sap.ui.table.SelectionMode.Single,
			allowColumnReordering: true,
			expandFirstLevel: true,
			toggleOpenState: function(oEvent) {
				var iRowIndex = oEvent.getParameter("rowIndex");
				var oRowContext = oEvent.getParameter("rowContext");
				var bExpanded = oEvent.getParameter("expanded");
			}
		});

		//Create a model and bind the table rows to this model
		oController.getOrgList();

		//Button to Add Child Org
		var oBtn_New = new sap.ui.commons.Button({text: "New",
			press: function() {
				var iSelectedIndex = oTable.getSelectedIndex();
				console.log(iSelectedIndex);
				oController.createOrg(oTable);
				
			}
		});
		
		//Button to Edit
		var oBtn_Edit = new sap.ui.commons.Button({text: "Edit",
			press: function() {
				var iSelectedIndex = oTable.getSelectedIndex();

			}
		});
		
		//Button to Delete
		var oBtn_Delete = new sap.ui.commons.Button({text: "Delete",
			press: function() {
				var iSelectedIndex = oTable.getSelectedIndex();
				oController.deleteOrg(oTable);

			}
		});
		
		oTable.setToolbar(new sap.ui.commons.Toolbar({items: [oBtn_Edit,oBtn_New,oBtn_Delete]}));
		
		// dialog to create a new orgnazition
		var oOrgPopup = new sap.ui.ux3.ToolPopup("org.orgPopup", {
			title : "New Orgnazition",
			modal : true
		});

		var oOrgPopupForm = new sap.ui.commons.layout.MatrixLayout("org.orgPopupForm");
		oOrgPopupForm.setLayoutFixed(false);		
		
		var oControl = new sap.ui.commons.TextField({editable: false,layoutData: new sap.ui.commons.form.GridElementData({hCells: "2"})}).bindProperty("value", "parentName");
		oOrgPopupForm.addRow(createMatrixLayoutRow("Parent Orgnazition", oControl));
		
		oControl = new sap.ui.commons.TextField({editable: true,required : true,layoutData: new sap.ui.commons.form.GridElementData({hCells: "2"})}).bindProperty("value", "name");
		oOrgPopupForm.addRow(createMatrixLayoutRow("Name", oControl));
		
		oControl = new  sap.ui.commons.ComboBox("org.type", {
			width : "180px",
			required : true,
			items : [ new sap.ui.core.ListItem({
				text : "Reporter",
				key : "Reporter"
			}), new sap.ui.core.ListItem({
				text : "Management",
				key : "Management"
			})]}).bindProperty("value", "type");
		oOrgPopupForm.addRow(createMatrixLayoutRow("Type", oControl));
		
		oControl = new  sap.ui.commons.ComboBox("org.reportDirect", {
			width : "180px",
			required : true,
			items : [ new sap.ui.core.ListItem({
				text : "Direct Parent",
				key : "DirectParent"
			}), new sap.ui.core.ListItem({
				text : "Headquarter",
				key : "Headquarter"
			})]}).bindProperty("value", "reportDirect");
		oOrgPopupForm.addRow(createMatrixLayoutRow("Report Direct", oControl));
		
		oControl = new sap.ui.commons.TextArea("org.description", {
			width : "250px",
			height : "70px",
			editable : true,
			required : false	
		}).bindProperty("value", "description");		
		oOrgPopupForm.addRow(createMatrixLayoutRow("Description", oControl));	
		
		oOrgPopup.addButton(new sap.ui.commons.Button({
			id : "org.Save",
			text : "Save",
			press : function() {
				oController.saveOrg();
			}
		}));

		oOrgPopup.addButton(new sap.ui.commons.Button({
			id : "org.Cancel",
			text : "Cancel",
			press : function() {
				oController.cancelOrg();
			}
		}));		
		
		oOrgPopup.addContent(oOrgPopupForm);
		oOrgPopup.setPosition(sap.ui.core.Popup.Dock.LeftTop, sap.ui.core.Popup.Dock.RightBottom,
				oBtn_New, "0 0", "none");
		
		var oLayout = new sap.ui.commons.layout.VerticalLayout("L_Org", { content : [ oTable ] });
		
		return oLayout;
	}

});
