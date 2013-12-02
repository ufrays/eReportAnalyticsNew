package org.sap.era.persistence.enums;


public enum EOriginTag {
	Start("Start", 0), End("End", 1);

	/*
	 * Fields
	 */
	private static final String DESCRIPTION_TEMPLATE = "%s day of %s";
	private String name;
	private int index;

	private EPeriodTag periodTag;
	private EIntervalTag intervalTag;

	private EOriginTag(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public static String getName(int index) {
		for (EOriginTag c : EOriginTag.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		if (this.name != null && this.periodTag != null && this.intervalTag != null) {
			return String.format(DESCRIPTION_TEMPLATE, this.name, this.intervalTag.toString());
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

	public EIntervalTag getIntervalTag() {
		return intervalTag;
	}

	public void setIntervalTag(EIntervalTag intervalTag) {
		this.intervalTag = intervalTag;
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
