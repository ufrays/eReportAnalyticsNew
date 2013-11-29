package org.sap.era.service.excel;

import org.sap.era.persistence.CellModel;

public class LabelControl extends Control {

	public LabelControl(String text, int colspan, int rowspan, boolean merge, int cellType) {
		super.rowspan = rowspan;
		super.colspan = colspan;
		super.isMerge = merge;
		super.text = text;
		super.cellType = cellType;
	}

	public LabelControl(String text) {
		//
		super.cellType = CellModel.CELL_TYPE_DATA;// super.CONTROL_TYPE_INPUT_TEXT;
		super.text = text;
		super.rowspan = 1;
		super.colspan = 1;
		super.isMerge = false;
	}

	public LabelControl(String text, int type) {
		super.cellType = type;
		super.text = text;
		super.rowspan = 1;
		super.colspan = 1;
		super.isMerge = false;
	}

	public LabelControl() {
		// TODO Auto-generated constructor stub
	}
}
