package org.sap.era.service.excel;

public class CellAlignment {
	
	private int value;
	public static final int CENTER = 2;
	public static final int RIGHT = 3;
	public static final int LEFT = 1;
	
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public String getDescription(){
		if(CENTER == value)
			return "Center";
		if(RIGHT == value)
			return "right";
		if(LEFT == value)
			return "left";
		return "Center";
	}

}
