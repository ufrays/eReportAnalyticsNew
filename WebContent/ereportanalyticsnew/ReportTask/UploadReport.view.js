sap.ui.jsview("ereportanalyticsnew.ReportTask.UploadReport", {

	/**
	 * Specifies the Controller belonging to this View. In the case that it is
	 * not implemented, or that "null" is returned, this View does not have a
	 * Controller.
	 * 
	 * @memberOf ereportanalyticsnew.ReportTask.UploadReport
	 */
	getControllerName : function() {
		return "ereportanalyticsnew.ReportTask.UploadReport";
	},

	/**
	 * Is initially called once after the Controller has been instantiated. It
	 * is the place where the UI is constructed. Since the Controller is given
	 * to this method, its event handlers can be attached right away.
	 * 
	 * @memberOf ereportanalyticsnew.ReportTask.UploadReport
	 */
	createContent : function(oController) {

		var oFileUploader = new sap.ui.commons.FileUploader({
			name : "reportUploader",
			uploadUrl : "upload"
		});

		var oMatrixLayout = new sap.ui.commons.layout.MatrixLayout({
			layoutFixed : false,
			rows : [ (function() {
				var mtxRow = new sap.ui.commons.layout.MatrixLayoutRow({
					cells : [ (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.Label({
								text : "Orgnization"
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.TextField({
								text : "{orgnazition}"
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.Label({
								text : "Report Duration"
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.TextField({
								text : "{reportTask/durationID}"
							})
						});
						return mtxCell;
					})() ]
				});
				return mtxRow;
			})(), (function() {
				var mtxRow = new sap.ui.commons.layout.MatrixLayoutRow({
					cells : [ (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.Label({
								text : "Valid From"
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.TextField({
								text : "{startDate}"
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.Label({
								text : "Valid To"
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.TextField({
								text : "{endDate}"
							})
						});
						return mtxCell;
					})(), ]
				});
				return mtxRow;
			})(), (function() {
				var mtxRow = new sap.ui.commons.layout.MatrixLayoutRow({
					cells : [ (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.Label({
								text : "Report Name"
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.TextField({
								text : "{reportTask/tableGroupModelName}"
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.Label({
								text : "Report Mode"
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.TextField({
								text : "{reportTask/reportMode}"
							})
						});
						return mtxCell;
					})(),

					]
				});
				return mtxRow;
			})(), (function() {
				var mtxRow = new sap.ui.commons.layout.MatrixLayoutRow({
					cells : [ (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.Label({
								text : "Duration Type"
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.TextField({
								text : "{reportTask/durationFlag}"
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.Label({
								text : "Status"
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.TextField({
								text : "{itemStatus}"
							})
						});
						return mtxCell;
					})(),

					]
				});
				return mtxRow;
			})(), (function() {
				var mtxRow = new sap.ui.commons.layout.MatrixLayoutRow({
					cells : [ (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.Label({
								text : "Report Sheets"
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.ValueHelpField({
								valueHelpRequest : oController.fireValueHelper,
								tooltip : "Select a sheet template"
							})
						});
						return mtxCell;
					})(), ]
				});
				return mtxRow;
			})(), (function() {
				var mtxRow = new sap.ui.commons.layout.MatrixLayoutRow({
					cells : [ (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.Label({
								text : "Report Template"
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.TextField({
								text : "sample report Template"
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.Button({
								text : "Download",
								press : oController.pressTemplateDownload
							})
						});
						return mtxCell;
					})() ]
				});
				return mtxRow;
			})(), (function() {
				var mtxRow = new sap.ui.commons.layout.MatrixLayoutRow({
					cells : [ (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.Label({
								text : "Report Upload"
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : [ oFileUploader, new sap.ui.commons.Button({
								text : "Upload",
								press : oFileUploader.upload
							}) ]
						});
						return mtxCell;
					})() ]
				});
				return mtxRow;
			})(), (function() {
				var mtxRow = new sap.ui.commons.layout.MatrixLayoutRow({
					cells : [ (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.Button({
								text : "Submit",
								press : oController.submitReport
							})
						});
						return mtxCell;
					})(), (function() {
						var mtxCell = new sap.ui.commons.layout.MatrixLayoutCell({
							content : new sap.ui.commons.Button({
								text : "Back",
								press : oController.back
							})
						});
						return mtxCell;
					})() ]
				});
				return mtxRow;
			})(),

			]
		});

		return oMatrixLayout;
	}

});
