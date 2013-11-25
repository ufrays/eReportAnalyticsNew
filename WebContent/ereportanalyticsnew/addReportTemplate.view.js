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
		oTabStrip1.setHeight("350px");
		oTabStrip1.attachClose( function (oEvent) {
			var oTabStrip = oEvent.oSource;
			oTabStrip.closeTab(oEvent.getParameter("index"));
		});

		// 1. tab: general data (use createTab)
		var oTab1 = new sap.ui.commons.Tab();
		oTab1.setTooltip("New Report Template");
		oTab1.setTitle(new sap.ui.commons.Title({text:"New Report Template",icon:"images/address.jpg"}));
		
		var oLayout1 = new sap.ui.commons.layout.MatrixLayout({columns: 2, width: "100%"});
		oLayout1.bindElement("/newTemplate");
		oLayout1.setWidths(['150px']);
		// 1.1 Template Type
		var  oRBtn = new sap.ui.commons.RadioButtonGroup({
			 columns : 2,
			 selectedIndex : "{flag}",
				items: [new sap.ui.core.Item({text: "Column-Fixed Report"}),
						new sap.ui.core.Item({text: "Fixed Compound Report"})
				]});
		var oLabel = new sap.ui.commons.Label({text: 'Template Type', labelFor: oTF});
		oLayout1.createRow(oLabel, oRBtn);
		// 1.2 Template File
		var oTF = new sap.ui.commons.TextField({editable: true, value: 'modelPath', width: '200px'});
		var oFileUploader = new sap.ui.commons.FileUploader({
			name: "upload",
			uploadOnChange: true,
			uploadUrl: "uploadReportTemplate.do",
			width: "400px",
			uploadComplete: function (oEvent) {
				var sResponse = oEvent.getParameter('response');
				console.log("uploadComplete-response:"+sResponse);
				var oModel = this.getModel();
				var tableGroupModel = oModel.getProperty("/newTemplate");
				tableGroupModel.modelPath = sResponse;
				console.log("uploadComplete-response:"+tableGroupModel);
				this.setEnabled(false);
				
			}
		});
		oLabel = new sap.ui.commons.Label({text: 'Template File', labelFor: oFileUploader});
		oLayout1.createRow(oLabel, oFileUploader);
		// 1.3 Template Name
		oTF = new sap.ui.commons.TextField({tooltip: 'Template Name', editable: true, value: '{name}', width: '400px'});
		oLabel = new sap.ui.commons.Label({text: 'Template Name', labelFor: oTF});
		oLayout1.createRow(oLabel, oTF);
		// 1.4 Template Description
		oInput = new sap.ui.commons.TextArea({value:"{description}",cols : 100, row: 4});
		oInput.setRows(10);
		oLabel = new sap.ui.commons.Label({text: 'Description', labelFor: oInput});
		oLayout1.createRow(oLabel, oInput);
		//
		oCell = new sap.ui.commons.layout.MatrixLayoutCell({content: ["", ""], rowSpan : 1, colSpan : 2});
		oLayout1.createRow(oCell);
		// 1.5 Buttons - Save & Cancel
		var oBtn1 = new sap.ui.commons.Button({text: "Save",
			tooltip: "",
			press: function() {
				//oFileUploader.upload();
				oController.saveTemplate();
			},
			layoutData: new sap.ui.commons.form.GridElementData({hCells: "1"})});
		var oBtn2 = new sap.ui.commons.Button({text: "Cancel",
			tooltip: "",
			press: function() {oController.cancel();}});
		oCell = new sap.ui.commons.layout.MatrixLayoutCell({content: [oBtn1, oBtn2],colSpan : 2});
		oLayout1.createRow(oCell);
		
		//oTabStrip1.createTab("New Report Template",oLayout1);
		oTab1.addContent(oLayout1);
		oTabStrip1.addTab(oTab1);
		
		// 2. tab: address data (use separate tab element)
		oTab2 = new sap.ui.commons.Tab();
		oTab2.setTooltip("Template Detail");
		oTab2.setTitle(new sap.ui.commons.Title({text:"Template Preview",icon:"images/doc_excel_table.png"}));
		var oLayout2 = new sap.ui.commons.layout.MatrixLayout({columns: 2, width: "100%"});
		oLayout2.setWidths(['150px']);
		
	    // 2.1 create the HTML control for tab2
        var html1 = new sap.ui.core.HTML({
        	    content:"<iframe src='/eReportAnalyticsNew/pages/Login.jsp' width='100%'></iframe>",
                preferDOM : false,                      
                // use the afterRendering event for 2 purposes
                afterRendering : function(e) {
                	  
                }
        });
		oLayout2.createRow(html1);
		
		oTab2.addContent(oLayout2);
		oTabStrip1.addTab(oTab2);
		
		//
		var oModel = this.getModel();
		//oTab1.setModel(oModel, "newTemplate");
		return oTabStrip1;
		
	}

});
