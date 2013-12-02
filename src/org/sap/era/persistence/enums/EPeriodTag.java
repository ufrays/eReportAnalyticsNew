package org.sap.era.persistence.enums;


public enum EPeriodTag {

	Yearly("Yearly", "Year", 0), Monthly("Monthly", "Month", 1), Weekly("Weekly", "Week", 2);

	/*
	 * Fields
	 */
	private String name;
	private String unitName;
	private int index;

	private EPeriodTag(String name, String unitName, int index) {
		this.name = name;
		this.unitName = unitName;
		this.index = index;
	}

	public static String getName(int index) {
		for (EPeriodTag c : EPeriodTag.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		if (this.name != null) {
			return this.name;
		} else {
			return super.toString();
		}
	}

	/*
	 * Getters & Setters
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
