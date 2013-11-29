sap.ui.jsview('ereportanalyticsnew.report-assignment.owl', {

	/**
	 * Specifies the Controller belonging to this View. In the case that it is
	 * not implemented, or that 'null' is returned, this View does not have a
	 * Controller.
	 * 
	 * @memberOf ereportanalyticsnew.index
	 */
	getControllerName : function() {
		return 'ereportanalyticsnew.report-assignment.owl';
	},

	/**
	 * Is initially called once after the Controller has been instantiated. It
	 * is the place where the UI is constructed. Since the Controller is given
	 * to this method, its event handlers can be attached right away.
	 * 
	 * @memberOf ereportanalyticsnew.index
	 */
	createContent : function(oController) {
		var _this = this;
		// Owl query table
		var tblReportAssignment = new sap.ui.table.Table(
		// Query all pipeline setting
		{
			// visibleRowCount : 10,
			// firstVisibleRow : 0,
			selectionMode : sap.ui.table.SelectionMode.Single,
			// navigationMode : sap.ui.table.NavigationMode.Paginator,
			fixedColumnCount : 0,
			toolbar : new sap.ui.commons.Toolbar({
				items :
				[
						new sap.ui.commons.Button({
							text : 'New',
							enabled : true,
							press : function() {
								_this.createNewReportAssignment();
							}
						}), new sap.ui.commons.Button({
							text : 'Drop',
							enabled : true,
							press : function() {
								_this.dropNewReportAssignment();
							}
						}),
				]
			}),
			extension :
			// All extension
			[],
			// ***********************************
			// Column definitions
			// ***********************************
			columns :
			[
					// @Column - ID (To be hidden in release)
					(function() {
						var clnPipelineId = new sap.ui.table.Column({
							label : new sap.ui.commons.Label({
								text : 'ID'
							}),
							width : 50 + 'px',
							sorted : true,
							template : new sap.ui.commons.TextField().bindValue('id', function(oriVal) {
								if (oriVal === undefined || oriVal === null) {
									return '';
								}
								oriVal = oriVal.toString();
								var perfixCharCount = 3 - oriVal.length;
								while (perfixCharCount-- > 0) {
									oriVal = '0' + oriVal;
								}
								return oriVal;
							}),
							sortProperty : 'Id',
							filterProperty : 'Id',
						});
						return clnPipelineId;
					})(),
					// @Column - DurationModel
					(function() {
						var clnPipelinePriority = new sap.ui.table.Column({
							label : new sap.ui.commons.Label({
								text : 'Duration Model'
							}),
							width : 200 + 'px',
							template : new sap.ui.commons.TextField().bindValue('durationModel', function(oriVal) {
								return oriVal;
							}),
							sortProperty : 'durationModel',
							filterProperty : 'durationModel',
						});
						return clnPipelinePriority;
					})(),
					// @Column - TableGroupModel
					(function() {
						var clnPipelineCustomerName = new sap.ui.table.Column({
							label : new sap.ui.commons.Label({
								text : 'Table Group Model'
							}),
							width : 150 + 'px',
							template : new sap.ui.commons.TextField().bindValue('tableGroupModel'),
							sortProperty : 'tableGroupModel',
							filterProperty : 'tableGroupModel',
						});
						return clnPipelineCustomerName;
					})(),
					// @Column - Name
					(function() {
						var clnPipelineServiceAdvisor = new sap.ui.table.Column({
							label : new sap.ui.commons.Label({
								text : 'Name'
							}),
							width : 200 + 'px',
							template : new sap.ui.commons.TextField().bindValue('name'),
							sortProperty : 'name',
							filterProperty : 'name',
						});
						return clnPipelineServiceAdvisor;
					})(),
					// @Column - Description
					(function() {
						var clnPipelineRequestTitle = new sap.ui.table.Column({
							label : new sap.ui.commons.Label({
								text : 'Description'
							}),
							width : 200 + 'px',
							template : new sap.ui.commons.TextField().bindValue('description'),
							sortProperty : 'description',
							filterProperty : 'description',
						});
						return clnPipelineRequestTitle;
					})(),
			],
			rowSelectionChange : function(rowContext, rowIndex, rowIndices) {
				// Trigger registered event while row selected change
				oController.events.RowSelected(this, {
					rowContext : rowContext,
					rowIndex : rowIndex,
					rowIndices : rowIndices
				});
				// TODO: Check 'Edit' button by if any row selected
			}
		});
		// Set up control collection
		oController.controls['TableReportAssignment'] = tblReportAssignment;
		// Set up interfaces
		oController.loadAll(function(sender, args) {
			tblReportAssignment.setModel(args.dataModel);
			tblReportAssignment.bindRows('/');
			// Initially sort the table by the first column
			tblReportAssignment.sort(tblReportAssignment.getColumns()[0]);
		});
		return tblReportAssignment;
	},
	createNewReportAssignment : function() {
		var viewCreateReportAssignment = sap.ui.view({
			viewName : 'ereportanalyticsnew.report-assignment.ti',
			type : sap.ui.core.mvc.ViewType.JS
		});
		// TODO: Refactor the getter of shell with global variable
		sap.ui.getCore().getControl('myShell').setContent(viewCreateReportAssignment);
	},
	dropNewReportAssignment : function() {
		// TODO
		alert("TODO!");
	},
});
