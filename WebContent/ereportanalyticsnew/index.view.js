sap.ui.jsview("ereportanalyticsnew.index", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf ereportanalyticsnew.index
	*/ 
	getControllerName : function() {
		return "ereportanalyticsnew.index";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf ereportanalyticsnew.index
	*/ 
	createContent : function(oController) {

		var oOrgnazition = sap.ui.view({id:"idOrgnazition", viewName:"ereportanalyticsnew.orgnazition", type:sap.ui.core.mvc.ViewType.JS});
		var oBusinessUser = sap.ui.view({id:"idBusinessUser", viewName:"ereportanalyticsnew.businessuser", type:sap.ui.core.mvc.ViewType.JS});
		//Report Template
		var oReportTemplate = sap.ui.view({id:"idReportTemplate", viewName:"ereportanalyticsnew.reportTemplate", type:sap.ui.core.mvc.ViewType.JS});
		//var oDisplayMyReport = sap.ui.view({id:"idDisplayMyReport", viewName:"ereportanalyticsnew.displayMyReport", type:sap.ui.core.mvc.ViewType.JS});

		var oShell = new sap.ui.ux3.Shell("myShell", {
			appTitle: "E-Report Collection & Analytics",
			appIcon: "http://www.sap.com/global/images/SAPLogo.gif",
			appIconTooltip: "",
			showLogoutButton: true,
			showSearchTool: false,
			showInspectorTool: false,
			showFeederTool: false,
			worksetItems: [new sap.ui.ux3.NavigationItem("WI_admin",{key:"wi_admin",text:"Admin",subItems:[
			                  new sap.ui.ux3.NavigationItem("WI_admin_1",{key:"wi_admin_1",text:"Orgnazition Structure"}),
			                  new sap.ui.ux3.NavigationItem("WI_admin_2",{key:"wi_admin_2",text:"Business Users"})]}),
			               new sap.ui.ux3.NavigationItem("WI_1",{key:"wi_1",text:"Report Collect", subItems:[
			                  new sap.ui.ux3.NavigationItem("WI_1_1",{key:"wi_1_1",text:"Pending Reports"}),
			                  new sap.ui.ux3.NavigationItem("WI_1_2",{key:"wi_1_2",text:"Finished Reports"})]}),
				            new sap.ui.ux3.NavigationItem("WI_2",{key:"wi_2",text:"Management&Analytics", subItems:[
				              new sap.ui.ux3.NavigationItem("WI_2_1",{key:"wi_2_1",text:"Report Management"}),
				              new sap.ui.ux3.NavigationItem("WI_2_2",{key:"wi_2_2",text:"Report Analytics"}),
				              new sap.ui.ux3.NavigationItem("WI_2_3",{key:"wi_2_3",text:"Custom Analytics"})]}),	
				            new sap.ui.ux3.NavigationItem("WI_3",{key:"wi_3",text:"Report Admin", subItems:[
			               	  new sap.ui.ux3.NavigationItem("WI_3_1",{key:"wi_3_1",text:"Report Template"}),
			                  new sap.ui.ux3.NavigationItem("WI_3_2",{key:"wi_3_2",text:"Report Assignment"}),
			                  new sap.ui.ux3.NavigationItem("WI_3_3",{key:"wi_3_3",text:"Job Schedule"})]})
			],
			worksetItemSelected: function(oEvent){
				var sId = oEvent.getParameter("id");
				var oShell = oEvent.oSource;
				switch (sId) {
				case "WI_admin":
					break;
				case "WI_admin_1":
					oShell.setContent(oOrgnazition);
					break;
				case "WI_admin_2":
					oShell.setContent(oBusinessUser);
					break;
				case "WI_3_1":
					oShell.setContent(oReportTemplate);
					break;
				case "WI_3_2":
					break;
				case "WI_3_3":
					break;
				case "WI_1_1" :
					oShell.setContent(oDisplayMyReport);
					break;
				default:
					break;
				}
			}
		});
		return oShell;
		
	}

});
