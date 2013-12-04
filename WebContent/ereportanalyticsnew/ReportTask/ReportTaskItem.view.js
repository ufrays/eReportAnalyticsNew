sap.ui.jsview("ereportanalyticsnew.ReportTask.ReportTaskItem", {

	/**
	 * Specifies the Controller belonging to this View. In the case that it is
	 * not implemented, or that "null" is returned, this View does not have a
	 * Controller.
	 * 
	 * @memberOf ereportanalyticsnew.ReportTask.ReportTaskItem
	 */
	getControllerName : function() {
		return "ereportanalyticsnew.ReportTask.ReportTaskItem";
	},

	/**
	 * Is initially called once after the Controller has been instantiated. It
	 * is the place where the UI is constructed. Since the Controller is given
	 * to this method, its event handlers can be attached right away.
	 * 
	 * @memberOf ereportanalyticsnew.ReportTask.ReportTaskItem
	 */
	createContent : function(oController) {
		var oTaskTable = new sap.ui.table.Table({
			title : "Pending Reports",
			visibleRowCount : 7,
			firstVisibleRow : 3,
			selectionMode : sap.ui.table.SelectionMode.Single,
			columns : [ (function() {
				var clnDurationID = new sap.ui.table.Column({
					label : new sap.ui.commons.Label({
						text : "Duration"
					}),
					width : 150 + 'px',
					template : new sap.ui.commons.TextField().bindValue('reportTask.durationID'),
					sortProperty : "reportTask.durationID",
					filterProperty : 'filterProperty'
				});
				return clnDurationID;
			})(), (function() {
				var clnTableGroupModel = new sap.ui.table.Column({
					label : new sap.ui.commons.Label({
						text : 'ID'
					}),
					width : 50 + 'px',
					sorted : true,
					template : new sap.ui.commons.TextField().bindValue('reportTask.tableGroupModel'),
					sortProperty : 'reportTask.tableGroupModel',
					filterProperty : 'reportTask.tableGroupModel',
				});
				return clnTableGroupModel;
			})(), (function() {
				var clnTableGroupModelName = new sap.ui.table.Column({
					label : new sap.ui.commons.Label({
						text : 'Report Name'
					}),
					width : 200 + 'px',
					template : new sap.ui.commons.TextField().bindValue('reportTask.tableGroupModelName'),
					sortProperty : 'reportTask.tableGroupModelName',
					filterProperty : 'reportTask.tableGroupModelName',
				});

				return clnTableGroupModelName;
			})(), ]
		});

	}

});