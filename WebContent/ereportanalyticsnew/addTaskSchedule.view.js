sap.ui.jsview("ereportanalyticsnew.addTaskSchedule", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf ereportanalyticsnew.addTaskSchedule
	*/ 
	getControllerName : function() {
		return "ereportanalyticsnew.addTaskSchedule";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf ereportanalyticsnew.addTaskSchedule
	*/ 
	createContent : function(oController) {
		var _this = this;
		// Create a TabStrip instance
		var oTabStrip1 = new sap.ui.commons.TabStrip();
		oTabStrip1.setHeight("550px");
		// 1. tab: New Schedule Task
		var oTab1 = new sap.ui.commons.Tab();
		oTab1.setTooltip("New Schedule Task");
		oTab1.setTitle(new sap.ui.commons.Title({text:"New Schedule Task"}));
		
		var oLayout1 = new sap.ui.commons.layout.MatrixLayout({columns: 2, width: "100%", widths : [ "10%", "90%" ]});
		oLayout1.bindElement("/");
		//1.1 Duration ID
		var oTF = new sap.ui.commons.TextField({tooltip: 'Duration ID', editable: true, value: '{durationID}'});
		var oLabel = new sap.ui.commons.Label({text: 'Duration ID', labelFor: oTF});
		oLayout1.createRow(oLabel, oTF); 
		//1.2.Template
		var oItemTemplate = new sap.ui.core.ListItem({key:"{id}",text:"{name}"});
		oTF = new sap.ui.commons.DropdownBox(_this.createId("tableGroupModel"),{
				value:"{tableGroupModelName}",
				selectedKey:"{tableGroupModel}",
				items: {
			        path: "/", 
			        template: oItemTemplate
			    }
			});
		oLabel = new sap.ui.commons.Label({text: 'Template', labelFor: oTF});
		oLayout1.createRow(oLabel, oTF);
		//1.3.Duration Type
		oTF = new sap.ui.commons.DropdownBox({
			value:"{durationDepict}",
			selectedKey:"{durationFlag}",
			items: [ new sap.ui.core.ListItem({
				text : "", key:""
			}),new sap.ui.core.ListItem({
				text : "Weekly", key:"WEEKLY"
			}), new sap.ui.core.ListItem({
				text : "Monthly", key:"MONTHLY"
			}), new sap.ui.core.ListItem({
				text : "Quarterly", key:"QUARTERLY"
			}), new sap.ui.core.ListItem({
				text : "Yearly", key:"YEARLY"
			})]
			});
		oLabel = new sap.ui.commons.Label({text: 'Duration Type', labelFor: oTF});
		oLayout1.createRow(oLabel, oTF);
		//1.4.Validity From
		oTF = new sap.ui.commons.DatePicker({
			width: "10em",
			value: {
				path: "startDate",
				type: new sap.ui.model.type.Date({pattern: "yyyy-MM-dd"})
        		}
        		});
		oLabel = new sap.ui.commons.Label({text: 'Validity From', labelFor: oTF});
		oLayout1.createRow(oLabel, oTF); 
		//1.5.Validity To
		oTF = new sap.ui.commons.DatePicker({
			width: "10em",
			value: {
				path: "endDate",
				type: new sap.ui.model.type.Date({pattern: "yyyy-MM-dd"})
    		}
    		});
		oLabel = new sap.ui.commons.Label({text: 'Validity To', labelFor: oTF});
		oLayout1.createRow(oLabel, oTF); 
		//1.6 Assigned Orgnazition
		var oLayout1_1 = new sap.ui.commons.layout.MatrixLayout({columns : 2,width : '400px',widths : ['70%', '30%'] });
		oLabel = new sap.ui.commons.Label({text: 'Assigned Orgnazition', labelFor: oTF});
		// Create Dialog
		var oDialog = new  sap.ui.commons.Dialog(_this.createId("orgnazitionSelect"),{title: "Orgnazition Select", modal: true, width:"800px"});
    	oDialog.addContent(new sap.ui.jsview("ereportanalyticsnew.commons.orgnazitionSelect"));
		oDialog.addButton(new sap.ui.commons.Button(_this.createId("OK"),{text: "OK", enabled:true, press:function(){
			oController.selectOrgnazition();
			}}));
		oDialog.addButton(new sap.ui.commons.Button({text: "Cancel", press:function(){oDialog.close();}}));
		//1.6.1 - orgnazitionList
		oTF = new sap.ui.commons.ListBox(_this.createId("orgnazitionList"),{
			items: {
		        path: "/", 
		        template: new sap.ui.core.ListItem({key:"{id}",text:"{name}"})
		    },
			minWidth : "280px",
			visibleItems : 15 
		});
		oCell = new sap.ui.commons.layout.MatrixLayoutCell({rowSpan: 12 });
		oCell.addContent(oTF);
		oLayout1_1.createRow(oCell);
		oLayout1.createRow(oLabel, oLayout1_1); 
		//1.6.2.Orgnazition Select
		oTF = new sap.ui.commons.Button({text:"Select Orgnazition", press : function() {oController.openOrgnazitionSelect()}});
		var oCell = new sap.ui.commons.layout.MatrixLayoutCell({ height: '25px' });
		oCell.addContent(oTF);
		oLayout1_1.createRow(oCell);
		// 1.7 Buttons - Save & Cancel
		var oBtn1 = new sap.ui.commons.Button({text: "Create",
			press: function() {
				//oFileUploader.upload();
				oController.create();
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
		
		// 2. tab: Assigned Orgnazition 
		oTab2 = new sap.ui.commons.Tab("addTaskSchedule.tab2");
		oTab2.setTooltip("Assigned Orgnazition");
		oTab2.setTitle(new sap.ui.commons.Title({text:"Assigned Orgnazition"}));
		var oLayout2 = new sap.ui.commons.layout.MatrixLayout({columns: 2});
		
		oTab2.addContent(oLayout2);
		
		oTabStrip1.addTab(oTab2);
		oTabStrip1.attachSelect(function(oEvent){
			console.log(oTabStrip1.getSelectedIndex());
			if (oTabStrip1.getSelectedIndex() == 0){
				oTabStrip1.setHeight("500px");
			}else{
				oTabStrip1.setHeight("200px");
				//set content for html1
				oController.setHTMLContent(html1);
			}
		});
		//
		//oTab1.setModel(oModel, "newTemplate");
		return oTabStrip1;
	}

});