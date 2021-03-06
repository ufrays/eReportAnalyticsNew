package org.sap.era.persistence.enums;

public enum EPeriodTag {

	Year("Yearly", 0), Month("Monthly", 1), Week("Weekly", 2);

	/*
	 * Fields
	 */
	private String unitName;
	private int index;

	private EPeriodTag(String unitName, int index) {

		this.unitName = unitName;
		this.index = index;
	}

	public static String getUnitName(int index) {
		for (EPeriodTag c : EPeriodTag.values()) {
			if (c.getIndex() == index) {
				return c.unitName;
			}
		}
		return null;
	}

	/*
	 * Getters & Setters
	 */

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
