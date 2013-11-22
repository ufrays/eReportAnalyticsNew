sap.ui.jsview("ereportanalyticsnew.addReportTemplate", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf ereportanalyticsnew.addReportTemplate
	*/ 
	getControllerName : function() {
		return "ereportanalyticsnew.addReportTemplate";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf ereportanalyticsnew.addReportTemplate
	*/ 
	createContent : function(oController) {
		// Create a TabStrip instance
		var oTabStrip1 = new sap.ui.commons.TabStrip();
		oTabStrip1.setHeight("200px");
		oTabStrip1.attachClose( function (oEvent) {
			var oTabStrip = oEvent.oSource;
			oTabStrip.closeTab(oEvent.getParameter("index"));
		});

		// 1. tab: general data (use createTab)
		var oLayout1 = new sap.ui.commons.layout.MatrixLayout({columns: 2, width: "100%"});
		oLayout1.setWidths(['150px']);

		var oTF = new sap.ui.commons.TextField({editable: true, value: 'path', width: '200px'});
		var oFileUploader = new sap.ui.commons.FileUploader({
			name: "upload",
			uploadOnChange: false,
			uploadUrl: "uploadReportTemplate.do",
			width: "500px",
			uploadComplete: function (oEvent) {
				//console.log("uploadComplete-oEvent:"+oEvent);
				var sResponse = oEvent.getParameter('response');
				console.log("uploadComplete-response:"+sResponse);
				var sResource = oEvent.getSource();
				console.log("uploadComplete-getSource:"+sResource);
				
				oTabStrip1.fireSelect(2);
			}
		});
		var oLabel = new sap.ui.commons.Label({text: 'Template File', labelFor: oFileUploader});
		oLayout1.createRow(oLabel, oFileUploader);
		
		//oCell = new sap.ui.commons.layout.MatrixLayoutCell({rowSpan : 5, colSpan : 2});
		//oLayout1.createRow(oCell);
		
		var oBtn1 = new sap.ui.commons.Button({text: "Upload Template",
			tooltip: "",
			press: function() {
				oFileUploader.upload();
				//oController.uploadTemplate();
			},
			layoutData: new sap.ui.commons.form.GridElementData({hCells: "1"})});
		var oBtn2 = new sap.ui.commons.Button({text: "Cancel",
			tooltip: "",
			press: function() {oController.cancel();}});
		oCell = new sap.ui.commons.layout.MatrixLayoutCell({content: [oBtn1, oBtn2],colSpan : 2});
		oLayout1.createRow(oCell);
		
		oTabStrip1.createTab("Template File",oLayout1);

		// 2. tab: address data (use separate tab element)
		oTab2 = new sap.ui.commons.Tab();
		oTab2.setTooltip("Template Info");
		oTab2.setTitle(new sap.ui.commons.Title({text:"Template Info",icon:"images/address.jpg"}));
		var oLayout2 = new sap.ui.commons.layout.MatrixLayout({columns: 2, width: "100%"});
		oLayout2.setWidths(['150px']);

		oTF = new sap.ui.commons.TextField({tooltip: 'Template Name', editable: true, value: 'name', width: '200px'});
		oLabel = new sap.ui.commons.Label({text: 'Template Name', labelFor: oTF});
		oLayout2.createRow(oLabel, oTF);

		oTF = new sap.ui.commons.TextField({tooltip: 'Created By', editable: false, value: 'createdBy', width: '200px'});
		oLabel = new sap.ui.commons.Label({text: 'Created By', labelFor: oTF});
		oLayout2.createRow(oLabel, oTF);
		
		oTF = new sap.ui.commons.TextField({tooltip: 'Created On', editable: false, value: 'createdOn', width: '200px'});
		oLabel = new sap.ui.commons.Label({text: 'Created On', labelFor: oTF});
		oLayout2.createRow(oLabel, oTF);
		
		oCell = new sap.ui.commons.layout.MatrixLayoutCell({content: ["", ""], rowSpan : 1, colSpan : 2});
		oLayout2.createRow(oCell);

		var oBtn3 = new sap.ui.commons.Button({text: "Save",
			tooltip: "",
			press: function() {oController.saveTemplateFile();},
			layoutData: new sap.ui.commons.form.GridElementData({hCells: "1"})});
		var oBtn4 = new sap.ui.commons.Button({text: "Cancel",
			tooltip: "",
			press: function() {oController.cancel();}});
		oCell = new sap.ui.commons.layout.MatrixLayoutCell({content: [oBtn3, oBtn4],colSpan : 2});
		oLayout2.createRow(oCell);
		
		
		oTab2.addContent(oLayout2);
		oTabStrip1.addTab(oTab2);

		return oTabStrip1;
		
	}

});
