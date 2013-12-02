package org.sap.era.persistence.enums;


public enum EIntervalTag {
	Current("Current", 0), Next("Next", 1);

	/*
	 * Fields
	 */
	private static final String DESCRIPTION_TEMPLATE = "%s %s";
	private String name;
	private int index;

	private EPeriodTag periodTag;

	private EIntervalTag(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (EIntervalTag c : EIntervalTag.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		if (this.name != null && this.periodTag != null) {
			return String.format(DESCRIPTION_TEMPLATE, this.name, this.periodTag.getUnitName());
		} else {
			return super.toString();
		}
	}

	/*
	 * Getters & Setters
	 */
	public EPeriodTag getPeriodTag() {
		return periodTag;
	}

	public void setPeriodTag(EPeriodTag periodTag) {
		this.periodTag = periodTag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
