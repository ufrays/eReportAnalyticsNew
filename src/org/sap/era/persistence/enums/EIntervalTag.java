package org.sap.era.persistence.enums;

public enum EIntervalTag {
	Current("Current", 0), Next("Next", 1);

	/*
	 * Fields
	 */

	private String name;
	private int index;

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

	/*
	 * Getters & Setters
	 */

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
