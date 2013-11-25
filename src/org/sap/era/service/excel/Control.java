package org.sap.era.service.excel;



public abstract class Control {
	
//	public static int CONTROL_TYPE_EMPTY = -1;
//	public static int CONTROL_TYPE_LABEL = 0;
//	public static int CONTROL_TYPE_INPUT_NUMERIC = 1;
//	public static int CONTROL_TYPE_INPUT_TEXT = 3;
//	public static int CONTROL_TYPE_NO_INPUT =2;
			
	protected String name;
	protected int size;
	protected String text;
	protected int orientation;
	protected int rowspan;
	protected int colspan;
	protected boolean isMerge = false;
	protected CellAlignment alignment;
	protected int cellType;
	protected int cellDataType;
	protected String property;//VO  Property

	protected CellFormat format = new CellFormat();
	  
	 
	public CellFormat getFormat() {
		return format;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public boolean getIsMerge() {
		return isMerge;
	}

	public  void  setIsMerge(boolean isMerge) {
		this.isMerge = isMerge;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public CellAlignment getAlignment() {
		return alignment;
	}

	public void setAlignment(CellAlignment alignment) {
		this.alignment = alignment;
	}

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setFormat(CellFormat format) {
		this.format = format;
	}

	public void setMerge(boolean isMerge) {
		this.isMerge = isMerge;
	}

	public int getCellType() {
		return cellType;
	}

	public void setCellType(int cellType) {
		this.cellType = cellType;
	}

	public int getCellDataType() {
		return cellDataType;
	}

	public void setCellDataType(int cellDataType) {
		this.cellDataType = cellDataType;
	}
	
	
}
