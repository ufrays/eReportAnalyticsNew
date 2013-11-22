jQuery.sap.declare("ereportanalyticsnew.GlobalFunctions");

// helper function for forms in a matrix layout
function createMatrixLayoutRow(sLabel, oControl) {
	var oLabel = new sap.ui.commons.Label({
		text : sLabel,
		labelFor : oControl
	});

	var oMLCell1 = new sap.ui.commons.layout.MatrixLayoutCell({
		hAlign : sap.ui.commons.layout.HAlign.End,
		vAlign : sap.ui.commons.layout.VAlign.Top,
		content : [ oLabel ]
	});
	var oMLCell2 = new sap.ui.commons.layout.MatrixLayoutCell({
		hAlign : sap.ui.commons.layout.HAlign.Begin,
		vAlign : sap.ui.commons.layout.VAlign.Top,
		content : [ oControl ]
	});

	return new sap.ui.commons.layout.MatrixLayoutRow({
		cells : [ oMLCell1, oMLCell2 ]
	});
}