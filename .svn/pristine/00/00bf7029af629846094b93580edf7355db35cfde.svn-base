sap.ui.jsview("ereportanalyticsnew.login", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf ereportanalyticsnew.login
	*/ 
	getControllerName : function() {
		return "ereportanalyticsnew.login";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf ereportanalyticsnew.login
	*/ 
	createContent : function(oController) {
	    oLogin = new sap.ui.commons.Panel();
	    	  var oLayout = new sap.ui.commons.form.GridLayout();
	    	  
	    	  var loginSubmit = new sap.ui.commons.Button("loginSubmit",{width:"96px",text: "Login",layoutData: new sap.ui.core.VariantLayoutData({
					multipleLayoutData: [new sap.ui.commons.form.GridElementData({hCells: "7"})]}),press: function() { oController.login()}});
	    	  var oLoginForm = new sap.ui.commons.form.Form("loginForm",{
	    	      		//title: new sap.ui.core.Title({text: "E-Report Collection & Analytics",icon: "images/SAP-Logo.png"}),
	  			layout: oLayout,
	  			width:"400px",
	  			formContainers: [
	  				new sap.ui.commons.form.FormContainer({
	  				  //title: new sap.ui.core.Title({text: "E-Report Collection & Analytics",icon: "images/SAP-Logo.png"}),
	  					formElements: [
							new sap.ui.commons.form.FormElement({
							    label: new sap.ui.commons.Label({text: "User ID :"}),
							    fields: [new sap.ui.commons.TextField("login.name")]
							}),
							new sap.ui.commons.form.FormElement({
							    label: new sap.ui.commons.Label({text: "Password :"}),
							    fields: [new sap.ui.commons.PasswordField("login.password")]
							})
							]
	  				}),
	  				new sap.ui.commons.form.FormContainer({
						formElements: [
							new sap.ui.commons.form.FormElement({
								label: new sap.ui.commons.Label({text: ""}),
							    fields: [loginSubmit]
							})
						]
	  				})
	  				]});
	    	  oLoginForm.addStyleClass("alignCenter");
	    	  loginSubmit.addStyleClass("loginSubmit");
	    	  	  var oHelp = new sap.ui.commons.MenuItem({text:"Help"});
			  var oShell = new sap.ui.ux3.Shell("loginShell", {
				appTitle: "E-Report Collection & Analytics",
				appIcon: "images/SAP-Logo.png",
				appIconTooltip: "SAP logo",
				showLogoutButton: false,
				showSearchTool: false,
				showInspectorTool: false,
				showFeederTool: false,
				content:oLoginForm,
				worksetItems: [ new sap.ui.ux3.NavigationItem("login",{key:"wi_sq",text:"LOGIN"})],
				headerItems: [
								new sap.ui.commons.MenuButton({
									text: "Help",
									tooltip: "Help Menu",
									menu: new sap.ui.commons.Menu({items:[
										oHelp,
										new sap.ui.commons.MenuItem({text:"Report Incident"}),
										new sap.ui.commons.MenuItem({text:"About"})]})
								})],
				worksetItemSelected: function(oEvent){
					var sId = oEvent.getParameter("id");
					var oShell = oEvent.oSource;
					switch (sId) {
					case "login":
						oShell.setContent(oLoginForm);
						break;
					default:
						break;
					}
				}

				});
			    	oHelp.attachSelect(function(oEvent){
        			    	sap.ui.getCore().getControl("login.name").setValue("000_Test Person");
        			    	sap.ui.getCore().getControl("login.password").setValue("INIT69");
			    	});
			  return oShell;
	}

});
