sap.ui.controller("ereportanalyticsnew.login", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf ereportanalyticsnew.login
*/
//	onInit: function() {
//
//	},

    
    login:function(){
	var username = sap.ui.getCore().getControl("login.name").getValue();
	var password = sap.ui.getCore().getControl("login.password").getValue();
	
	var query = "&username="+username+"&password="+password;
	jQuery.ajax({
		url : "login.do?"+query,
		type : 'GET',
		//contentType : 'application/json',
		dataType:"json",
		success : function(data) {
		    	var obj =data;
		    	//alert(obj);
		    	console.log(obj);
			if(obj !=null){
				sap.ui.getCore().getControl("loginShell").destroy();
				var index = sap.ui.view({viewName : "ereportanalyticsnew.index",type : sap.ui.core.mvc.ViewType.JS});
				index.placeAt("content");
			}else{
				sap.ui.commons.MessageBox.alert("wrong userID or password");
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			// TODO improve error handling
			sap.ui.commons.MessageBox.alert("Failed to retrieve data: " + textStatus+ "\n" + errorThrown);
		}
	});
    }
/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf ereportanalyticsnew.login
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf ereportanalyticsnew.login
*/
//	onAfterRendering: function() {
//
//	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf ereportanalyticsnew.login
*/
//	onExit: function() {
//
//	}

});