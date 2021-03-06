sap.ui.jsview('ereportanalyticsnew.report-assignment.ti', {

	/**
	 * Specifies the Controller belonging to this View. In the case that it is not implemented, or that 'null' is returned, this View does not have a
	 * Controller.
	 * 
	 * @memberOf ereportanalyticsnew.index
	 */
	getControllerName : function() {
		return 'ereportanalyticsnew.report-assignment.ti';
	},

	/**
	 * Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. Since the Controller is given
	 * to this method, its event handlers can be attached right away.
	 * 
	 * @memberOf ereportanalyticsnew.index
	 */
	createContent : function(controller) {
		var _this = this;
		var tbsReportAssignment = new sap.ui.commons.TabStrip({
			tabs : [
				new sap.ui.commons.Tab({
					title : new sap.ui.commons.Title({
						text : 'New/Edit Report Assignment',
					}),
					content :
					// dialog to create a new report assignment
					new sap.ui.commons.layout.MatrixLayout({
						layoutFixed : false,
						rows : [
							// Name
							_this.createRow('Name', new sap.ui.commons.TextField({
								editable : true,
								required : true,
								width : 400 + 'px',
								value : {
									path : 'tableGroupAssignment/name',
								},
							})),
							// Report Template
							_this.createRow('Report Template',
							// get report template list from server
							(function() {
								var cbxReportTemplate = new sap.ui.commons.ComboBox({
									width : '400px',
									required : true,
									maxPopupItems : 30,
									value : {
										path : 'tableGroupAssignment/tableGroupModel/name',
									},
								});
								var itemTemplate = new sap.ui.core.ListItem();
								itemTemplate.bindProperty('text', 'name');
								itemTemplate.bindProperty('key', 'index');
								cbxReportTemplate.bindItems('/allTableGroupModels', itemTemplate);

								return cbxReportTemplate;
							})()),
							// Separation
							new sap.ui.commons.layout.MatrixLayoutRow({
								width : 400 + 'px',
								cells : [
									new sap.ui.commons.layout.MatrixLayoutCell({
										colSpan : 2,
										// separation : sap.ui.commons.layout.Separation.LargeWithLine,
										content : [
											new sap.ui.core.HTML({
												content : '<hr></hr>',
											}),
										],
									}),
								]
							}),
							// Period Mode
							_this.createRow('Period Tag', new sap.ui.commons.layout.HorizontalLayout({
								content : [
									new sap.ui.commons.Label({
										width : 60 + 'px',
										text : 'Every',
									}), (function() {
										var cbxPeriodTag = new sap.ui.commons.ComboBox({
											width : 100 + 'px',
											required : true,
											value : {
												path : 'tableGroupAssignment/periodTag',
											},
										/*
										 * items : [ // TODO: Refactor - Bind the template list fetch from server new sap.ui.core.ListItem({ text :
										 * 'Year', key : '0' }), new sap.ui.core.ListItem({ text : 'Month', key : '1' }), new sap.ui.core.ListItem({
										 * text : 'Week', key : '2' }) ],
										 */
										});
										var itemTemplate = new sap.ui.core.ListItem();
										itemTemplate.bindProperty('text', 'name');
										itemTemplate.bindProperty('key', 'index');
										cbxPeriodTag.bindItems('/periodTagOptions', itemTemplate);
										controller.controls['ComboBoxPeriodTag'] = cbxPeriodTag;
										return cbxPeriodTag;
									})()
								]
							})),
							// Rule for tasks creation
							_this.createTimeCoordinate('Task Generate At', 'createTimeCoordinate', controller),
							// Rule for tasks creation
							_this.createTimeCoordinate('Task Start At', 'startTimeCoordinate', controller),
							// Rule for tasks creation
							_this.createTimeCoordinate('Task End At', 'endTimeCoordinate', controller),
							// Organization Assignment
							_this.createRow('Organization Assignment', new sap.ui.commons.layout.HorizontalLayout({
								content : (function() {
									// All org list
									var lbxAllOrgList = new sap.ui.commons.ListBox({
										width : 200 + 'px',
										visibleItems : 10,
										allowMultiSelect : true,
									});
									lbxAllOrgList.destroyItems();
									// get all org list from server
									controller.getAllOrgList(function(sender, eventArgs) {
										lbxAllOrgList.destroyItems();
										var orgListData = eventArgs.dataModel.getData();
										for (var idx = 0; idx < orgListData.length; idx++) {
											var orgData = orgListData[idx];
											var orgItem = new sap.ui.core.ListItem();
											orgItem.setKey(orgData.id);
											orgItem.setText(orgData.name);
											lbxAllOrgList.addItem(orgItem);
										}
									});
									/*
									 * var orgListData = _this.getModel().getData().allOrgnazitions; for (var idx = 0; idx < orgListData.length;
									 * idx++) { var orgData = orgListData[idx]; var orgItem = new sap.ui.core.ListItem(); orgItem.setKey(orgData.id);
									 * orgItem.setText(orgData.name); lbxAllOrgList.addItem(orgItem); }
									 */

									// Selected org list
									var lbxAssignedOrgList = new sap.ui.commons.ListBox({
										width : 150 + 'px',
										visibleItems : 10,
										allowMultiSelect : true,
									});
									controller.controls['AssignedOrgList'] = lbxAssignedOrgList;
									// Control panel
									var controlPanel = new sap.ui.commons.layout.VerticalLayout({
										width : 50 + 'px',
										content : [
											// Separation
											new sap.ui.core.HTML({
												content : '<hr></hr>',
											}),
											// Button: Selected orgs from all org list into result org list
											(function() {
												var btnAdd = new sap.ui.commons.Button({
													text : '>>',
													width : 50 + 'px',
													enabled : true,
													press : function() {
														jQuery.each(lbxAllOrgList.getSelectedItems(), function(idx, item) {
															// Verify whether the selected item existed in result list
															var isConflicted = false;
															jQuery.each(lbxAssignedOrgList.getItems(), function(idx0, item0) {
																if (item0.getKey() === item.getKey()) {
																	isConflicted = true;
																	return false;
																}
															});
															if (!isConflicted) {
																lbxAssignedOrgList.addItem(new sap.ui.core.ListItem({
																	key : item.getKey(),
																	text : item.getText(),
																}));
																lbxAllOrgList.removeItem(item);
															}
														});
													},
												});
												return btnAdd;
											})(),
											// Separation
											new sap.ui.core.HTML({
												content : '<hr></hr>',
											}),
											// Button: Remove selected orgs from selected org list
											(function() {
												var btnRemove = new sap.ui.commons.Button({
													text : '<<',
													width : 50 + 'px',
													enabled : true,
													press : function() {
														jQuery.each(lbxAssignedOrgList.getSelectedItems(), function(idx, item) {
															lbxAssignedOrgList.removeItem(item);
														});
													},
												});
												return btnRemove;
											})(),
											// Separation
											new sap.ui.core.HTML({
												content : '<hr></hr>',
											}),
										]
									});
									return [
										lbxAllOrgList, controlPanel, lbxAssignedOrgList,
									];
								})(),
							})),
							// Separation
							new sap.ui.commons.layout.MatrixLayoutRow({
								width : 400 + 'px',
								cells : [
									new sap.ui.commons.layout.MatrixLayoutCell({
										colSpan : 2,
										// separation : sap.ui.commons.layout.Separation.LargeWithLine,
										content : [
											new sap.ui.core.HTML({
												content : '<hr></hr>',
											}),
										],
									}),
								]
							}),
							// Buttons row
							new sap.ui.commons.layout.MatrixLayoutRow({
								width : 400 + 'px',
								cells : [
									new sap.ui.commons.layout.MatrixLayoutCell(),
									// Buttons
									new sap.ui.commons.layout.MatrixLayoutCell({
										hAlign : sap.ui.commons.layout.HAlign.End,
										vAlign : sap.ui.commons.layout.VAlign.Top,
										content : [
											// Save and close
											(function() {
												var btnOk = new sap.ui.commons.Button({
													text : 'Save&Close',
													width : 100 + 'px',
													enabled : true,
													press : function() {
														controller.save();
														controller.close();
													},
												});
												return btnOk;
											})(),
											// Word span
											new sap.ui.commons.TextView({
												width : 10 + 'px',
											}),
											// Close
											(function() {
												var btnCancel = new sap.ui.commons.Button({
													text : 'Cancel',
													width : 60 + 'px',
													enabled : true,
													press : function() {
														controller.close();
													},
												});
												return btnCancel;
											})(),
										]
									}),
								]
							})
						],
					}),
				}),
			]
		});
		controller.controls['EditReportAssignment'] = tbsReportAssignment;
		return tbsReportAssignment;
	},

	/**
	 * @author I071053
	 * @param title
	 * @param bindModelPath
	 * @returns
	 */
	createTimeCoordinate : function(title, bindModelPath, controller) {
		var timeCoordinate = this.createRow(title, new sap.ui.commons.layout.HorizontalLayout({
			content : [
				// Set Current/Next
				(function() {
					var cbxPeriodTag = new sap.ui.commons.ComboBox({
						width : '100px',
						required : true,
						value : {
							path : '/tableGroupAssignment/' + bindModelPath + '/intervalTag',
						},
					/*
					 * items : [ // TODO: Refactor - Bind the template list fetch from server new sap.ui.core.ListItem({ text : 'Current', key : '0'
					 * }), new sap.ui.core.ListItem({ text : 'Next', key : '1' }) ]
					 */
					});
					var itemTemplate = new sap.ui.core.ListItem();
					itemTemplate.bindProperty('text', 'name');
					itemTemplate.bindProperty('key', 'index');
					cbxPeriodTag.bindItems('/intervalTagOptions', itemTemplate);
					return cbxPeriodTag;
				})(),
				// Word span
				new sap.ui.commons.TextView({
					width : 10 + 'px',
				}),
				// Display Year/Month/Week etc..
				new sap.ui.commons.TextView({
					text : {
						path : 'tableGroupAssignment/periodTag',
					},
					width : 50 + 'px',
				}),
				// Word span
				new sap.ui.commons.TextView({
					width : 10 + 'px',
				}),
				// Set Last/Begin
				(function() {
					var cbxOriginTag = new sap.ui.commons.ComboBox({
						width : '100px',
						required : true,
						value : {
							path : '/tableGroupAssignment/' + bindModelPath + '/originTag',
						},
					/*
					 * items : [ // TODO: Refactor - Bind the template list fetch from server new sap.ui.core.ListItem({ text : 'Begin', key : '0' }),
					 * new sap.ui.core.ListItem({ text : 'Last', key : '1' }) ]
					 */
					});
					var itemTemplate = new sap.ui.core.ListItem();
					itemTemplate.bindProperty('text', 'name');
					itemTemplate.bindProperty('key', 'index');
					cbxOriginTag.bindItems('/originTagOptions', itemTemplate);
					return cbxOriginTag;
				})(),
				// Word span
				new sap.ui.commons.TextView({
					width : 10 + 'px',
				}),
				// Set Offset Days
				new sap.ui.commons.TextField({
					width : '50px',
					editable : true,
					required : true,
					value : {
						path : '/tableGroupAssignment/' + bindModelPath + '/offsetDays',
					/*
					 * formatter : function(offsetDays) { if (!(offsetDays == undefined || offsetDays == null)) { return Math.abs(offsetDays); } }
					 */
					},
				}),
				// Word span
				new sap.ui.commons.TextView({
					width : 10 + 'px',
				}),
				// Display time span unit
				new sap.ui.commons.TextView({
					text : ' days ',
				}),
			],
		}));
		return timeCoordinate;
	},

	/**
	 * Create new matrix layout row with label text on left and specific control on right
	 * 
	 * @author I071053
	 * @param labelText
	 * @param control
	 * @returns {sap.ui.commons.layout.MatrixLayoutRow}
	 */
	createRow : function(labelText, control) {
		return new sap.ui.commons.layout.MatrixLayoutRow({
			cells : [
				new sap.ui.commons.layout.MatrixLayoutCell({
					hAlign : sap.ui.commons.layout.HAlign.End,
					vAlign : sap.ui.commons.layout.VAlign.Top,
					content : [
						new sap.ui.commons.Label({
							required : true,
							width : 200 + 'px',
							text : (function() {
								if (labelText.lastIndexOf(':', 0) == 0) {
									return labelText;
								} else {
									return labelText + ':';
								}
							})(),
							labelFor : control,
						}),
					],
				}), new sap.ui.commons.layout.MatrixLayoutCell({
					hAlign : sap.ui.commons.layout.HAlign.Begin,
					vAlign : sap.ui.commons.layout.VAlign.Top,
					content : [
						control,
					],
				})
			]
		});
	},
});
